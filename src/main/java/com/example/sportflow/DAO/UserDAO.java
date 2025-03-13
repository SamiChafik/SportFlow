package com.example.sportflow.DAO;

import com.example.sportflow.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public User checkLogin(String email, String password) {
        User user = null;
        String sql = "SELECT * FROM user WHERE email = ? AND password = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, email);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("user_id");
                String dbLast_name = resultSet.getString("last_name");
                String dbFirst_name = resultSet.getString("first_name");
                String dbEmail = resultSet.getString("email");
                String dbPassword = resultSet.getString("password");
                String dbRole = resultSet.getString("role");

                user = new User();
                user.setId(id);
                user.setLast_name(dbLast_name);
                user.setFirst_name(dbFirst_name);
                user.setEmail(dbEmail);
                user.setPassword(dbPassword);
                user.setRole(dbRole);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
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

    public List<User> selectAllUsers() {
        List<User> users = new ArrayList<>();

        String sql = "SELECT u.user_id, u.first_name, u.last_name, u.birth_date, u.email, u.password, u.role, " +
                "m.sport, e.speciality " +
                "FROM user u " +
                "LEFT JOIN member m ON u.user_id = m.member_id " +
                "LEFT JOIN entraineur e ON u.user_id = e.entraineur_id";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("user_id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String birthDate = resultSet.getString("birth_date");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String role = resultSet.getString("role");
                String sport = resultSet.getString("sport");
                String speciality = resultSet.getString("speciality");


                User user = new User(id, lastName, firstName, birthDate, email, password, role);
                user.setSport(sport);
                user.setSpeciality(speciality);
                users.add(user);
            }
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
        }
        return users;
    }

    public boolean deleteUser(int userId) {
        String sql = "DELETE FROM user WHERE user_id = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);

            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
            return false;
        }
    }

}