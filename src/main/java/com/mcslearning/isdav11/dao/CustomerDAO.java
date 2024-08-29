package com.mcslearning.isdav11.dao;

import com.mcslearning.isdav11.model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDAO {
    private Connection connection;

    public CustomerDAO(Connection connection) {
        this.connection = connection;
    }

    public Customer getCustomerByUsername(String username) throws SQLException {
        String query = "SELECT * FROM customers WHERE username = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, username);
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            return new Customer(rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("email"),
                    rs.getString("phone"));
        }
        return null;
    }

    public Customer getCustomerByEmail(String email) throws SQLException {
        String query = "SELECT id, username, password, email, phone FROM customers WHERE email = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return new Customer(
                            rs.getInt("id"),
                            rs.getString("username"),
                            rs.getString("password"),
                            rs.getString("email"),
                            rs.getString("phone")
                    );
                }
            }
        }
        return null;
    }


    public void updateCustomerPassword(int customerId, String newPassword) throws SQLException {
        String query = "UPDATE customers SET password = ? WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, newPassword);
        statement.setInt(2, customerId);
        statement.executeUpdate();
    }
}
