package com.mcslearning.isdav11.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseUtil {
    public static Connection getConnection() throws Exception {
        String url = "jdbc:mysql://172.17.0.2:3306/isda";
        String username = "root";
        String password = "rootpass";
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url, username, password);
    }
}
