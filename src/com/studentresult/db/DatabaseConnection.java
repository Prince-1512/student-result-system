package com.studentresult.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DatabaseConnection - Singleton class for managing MySQL connection
 * Uses JDBC to connect to the student_result_db database
 */
public class DatabaseConnection {

    private static final String URL      = "jdbc:mysql://localhost:3306/student_result_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USERNAME = "javauser";
    private static final String PASSWORD = "java123"; // change this

    private static Connection connection = null;

    // Private constructor — singleton pattern
    private DatabaseConnection() {}

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                System.out.println("Database connected successfully.");
            } catch (ClassNotFoundException e) {
                throw new SQLException("MySQL JDBC Driver not found: " + e.getMessage());
            }
        }
        return connection;
    }

    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            System.err.println("Error closing connection: " + e.getMessage());
        }
    }
}
