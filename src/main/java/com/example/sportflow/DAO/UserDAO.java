package com.example.sportflow.DAO;

import com.example.sportflow.model.User;

import java.sql.*;

public class UserDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/sportflow";
    private String jdbcUsername = "root";
    private String jdbcPassword = "123456789";
    private String jdbcDriver = "com.mysql.cj.jdbc.Driver";

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(jdbcDriver);
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("JDBC Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            throw new RuntimeException("Failed to connect to the database: " + e.getMessage());
        }
        return connection;
    }

    public void createUserTable() {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {

            String sqlQuery1 = "CREATE TABLE IF NOT EXISTS user (" +
                    "user_id INT PRIMARY KEY AUTO_INCREMENT, " +
                    "first_name VARCHAR(100) NOT NULL, " +
                    "last_name VARCHAR(100) NOT NULL, " +
                    "birth_date DATE NOT NULL, " +
                    "email VARCHAR(100) UNIQUE NOT NULL, " +
                    "password VARCHAR(100) NOT NULL, " +
                    "role VARCHAR(15) NOT NULL" +
                    ")";

            String sqlQuery2 = "CREATE TABLE IF NOT EXISTS member (" +
                    "member_id INT PRIMARY KEY, " +
                    "sport VARCHAR(100) NOT NULL, " +
                    "CONSTRAINT fk_member_user FOREIGN KEY (member_id) REFERENCES user(user_id) ON DELETE CASCADE" +
                    ")";

            String sqlQuery3 = "CREATE TABLE IF NOT EXISTS entraineur (" +
                    "entraineur_id INT PRIMARY KEY, " +
                    "speciality VARCHAR(100) NOT NULL, " +
                    "CONSTRAINT fk_entraineur_user FOREIGN KEY (entraineur_id) REFERENCES user(user_id) ON DELETE CASCADE" +
                    ")";

            statement.executeUpdate(sqlQuery1);
            statement.executeUpdate(sqlQuery2);
            statement.executeUpdate(sqlQuery3);
            System.out.println("Tables 'user', 'member', and 'entraineur' created successfully!");

        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
        }
    }

    public void addUser(User user) {
        Connection connection = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false); // Start transaction

            String insertUserQuery = "INSERT INTO user (first_name, last_name, birth_date, email, password, role) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement userStatement = connection.prepareStatement(insertUserQuery, Statement.RETURN_GENERATED_KEYS)) {
                userStatement.setString(1, user.getFirst_name());
                userStatement.setString(2, user.getLast_name());
                userStatement.setDate(3, java.sql.Date.valueOf(user.getBirth_date()));
                userStatement.setString(4, user.getEmail());
                userStatement.setString(5, user.getPassword());
                userStatement.setString(6, user.getRole());
                userStatement.executeUpdate();

                ResultSet generatedKeys = userStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int userId = generatedKeys.getInt(1);

                    if ("member".equalsIgnoreCase(user.getRole())) {
                        String insertMemberQuery = "INSERT INTO member (member_id, sport) VALUES (?, ?)";
                        try (PreparedStatement memberStatement = connection.prepareStatement(insertMemberQuery)) {
                            memberStatement.setInt(1, userId);
                            memberStatement.setString(2, user.getSport());
                            memberStatement.executeUpdate();
                        }
                    } else if ("entraineur".equalsIgnoreCase(user.getRole())) {
                        String insertEntraineurQuery = "INSERT INTO entraineur (entraineur_id, speciality) VALUES (?, ?)";
                        try (PreparedStatement entraineurStatement = connection.prepareStatement(insertEntraineurQuery)) {
                            entraineurStatement.setInt(1, userId);
                            entraineurStatement.setString(2, user.getSpeciality());
                            entraineurStatement.executeUpdate();
                        }
                    } else {
                        throw new SQLException("Invalid role: " + user.getRole());
                    }
                } else {
                    throw new SQLException("Failed to retrieve user_id.");
                }
            }

            connection.commit();
            System.out.println("User added successfully!");

        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    System.err.println("Rollback failed: " + ex.getMessage());
                }
            }
            System.err.println("SQL Error: " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException e) {
                    System.err.println("Failed to close connection: " + e.getMessage());
                }
            }
        }
    }
}