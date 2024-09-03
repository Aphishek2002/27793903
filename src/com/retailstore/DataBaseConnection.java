package com.retailstore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DataBaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/retail_store";
    private static final String USER = "root";
    private static final String PASSWORD = "@phisheK1&";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
