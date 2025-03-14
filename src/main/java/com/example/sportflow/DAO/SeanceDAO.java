package com.example.sportflow.DAO;

import com.example.sportflow.model.Seance;
import com.example.sportflow.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class SeanceDAO {
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

    public void createSeanceTable() {

        String createSeanceTable = "CREATE TABLE IF NOT EXISTS seance (" +
                "seance_id INT AUTO_INCREMENT PRIMARY KEY, " +
                "seance_name VARCHAR(50), " +
                "date_heure DATETIME NOT NULL, " +
                "member_id INT, " +
                "entraineur_id INT, " +
                "FOREIGN KEY (member_id) REFERENCES member(member_id), " +
                "FOREIGN KEY (entraineur_id) REFERENCES entraineur(entraineur_id)" +
                ")";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {

            statement.executeUpdate(createSeanceTable);
            System.out.println("Table 'seance' created successfully (if it did not exist already).");

        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<User> selectAllMembers() {
        List<User> members = new ArrayList<>();
        String sql = "SELECT user_id, first_name, last_name FROM user WHERE role = 'member'";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("user_id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");

                User member = new User(id, lastName, firstName);
                members.add(member);
            }
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
        }
        return members;
    }

    public List<User> selectAllEntraineurs() {
        List<User> entraineurs = new ArrayList<>();
        String sql = "SELECT user_id, first_name, last_name FROM user WHERE role = 'entraineur'";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("user_id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");

                User entraineur = new User(id, lastName, firstName);
                entraineurs.add(entraineur);
            }
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
        }
        return entraineurs;
    }

    public boolean addSeance(Seance seance) {
        String sql = "INSERT INTO seance (seance_name, date_heure, member_id, entraineur_id) VALUES (?, ?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, seance.getSeance_name());
            statement.setString(2, seance.getSeance_date());
            statement.setInt(3, seance.getMember_id());
            statement.setInt(4, seance.getEntraineur_id());

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
            return false;
        }
    }
}
