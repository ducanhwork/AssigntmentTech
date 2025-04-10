package com.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
public class DatabaseConnectionUtil {


    private static final String DB_USER;
    private static final String DB_PASSWORD;
    private static final String DB_URL;
    static {
        ResourceBundle rsc = ResourceBundle.getBundle("application");
        DB_USER = rsc.getString("datasource.username");
        DB_PASSWORD = rsc.getString("datasource.password");
        DB_URL = rsc.getString("datasource.url");
    }
    private static Connection connection;


    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException("Không tìm thấy JDBC Driver", e);
            }

            connection = DriverManager.getConnection(
                    DB_URL,
                    DB_USER,
                    DB_PASSWORD);
        }
        return connection;
    }


    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void rollback() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        try {
            getConnection();
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
    }

}