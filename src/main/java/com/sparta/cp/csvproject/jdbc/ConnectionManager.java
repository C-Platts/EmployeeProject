package com.sparta.cp.csvproject.jdbc;

import com.sparta.cp.csvproject.util.PropertiesLoader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    private static Connection connection;

    public static Connection connectToDatabase() {
        String url = PropertiesLoader.getProperties().getProperty("url");
        String username = PropertiesLoader.getProperties().getProperty("userName");
        String password = PropertiesLoader.getProperties().getProperty("password");

        try {
           return DriverManager.getConnection(url, username, password);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;

    }

    public static void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
