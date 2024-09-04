package com.retailstore.dao;

import com.retailstore.model.Customer;
import com.retailstore.util.DataBaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class CustomerDao {
    public void addCustomer(Customer customer) throws SQLException {
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(
                     "INSERT INTO Customer(name, email, phone_number, address) VALUES (?, ?, ?, ?)")) {

            pstmt.setString(1, customer.getName());
            pstmt.setString(2, customer.getEmail());
            pstmt.setString(3, customer.getPhoneNumber());
            pstmt.setString(4, customer.getAddress());

            pstmt.executeUpdate();
        }
    }

    public List<Customer> getAllCustomers() throws SQLException {
        List<Customer> customers = new ArrayList<>();

        try (Connection conn = DataBaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Customer")) {

            while (rs.next()) {
                Customer customer = new Customer();
                customer.setCustomerId(rs.getInt("customer_id"));
                customer.setName(rs.getString("name"));
                customer.setEmail(rs.getString("email"));
                customer.setPhoneNumber(rs.getString("phone_number"));
                customer.setAddress(rs.getString("address"));
                customers.add(customer);
            }
        }

        return customers;
    }

    public void updateCustomer(Customer customer) throws SQLException {
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(
                     "UPDATE Customer SET name = ?, email = ?, phone_number = ?, address = ? WHERE customer_id = ?")) {

            pstmt.setString(1, customer.getName());
            pstmt.setString(2, customer.getEmail());
            pstmt.setString(3, customer.getPhoneNumber());
            pstmt.setString(4, customer.getAddress());
            pstmt.setInt(5, customer.getCustomerId());

            pstmt.executeUpdate();
        }
    }

    public void deleteCustomer(int customerId) throws SQLException {
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("DELETE FROM Customer WHERE customer_id = ?")) {

            pstmt.setInt(1, customerId);
            pstmt.executeUpdate();
        }
    }
}
