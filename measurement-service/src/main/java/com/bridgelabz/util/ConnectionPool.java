package com.bridgelabz.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionPool {

    static {

        try {

            Class.forName(
                    ApplicationConfig.getProperty("db.driver")
            );

        } catch (ClassNotFoundException e) {

            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection()
            throws SQLException {

        return DriverManager.getConnection(
                ApplicationConfig.getProperty("db.url"),
                ApplicationConfig.getProperty("db.username"),
                ApplicationConfig.getProperty("db.password")
        );
    }
}