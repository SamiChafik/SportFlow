package com.example.sportflow.DAO;

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
}
