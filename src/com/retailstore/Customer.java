package com.retailstore;

import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Customer {

    // Private fields for encapsulation
    private int customerId;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;

    // Getters and Setters
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // Methods for Customer Management with Exception Handling
    public void addCustomer() {
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(
                     "INSERT INTO Customer(name, email, phone_number, address) VALUES (?, ?, ?, ?)")) {
            Scanner scanner = new Scanner(System.in);

            try {
                System.out.print("Enter customer name: ");
                setName(scanner.nextLine());

                System.out.print("Enter customer email: ");
                setEmail(scanner.nextLine());

                System.out.print("Enter customer phone number: ");
                setPhoneNumber(scanner.nextLine());

                System.out.print("Enter customer address: ");
                setAddress(scanner.nextLine());

                pstmt.setString(1, getName());
                pstmt.setString(2, getEmail());
                pstmt.setString(3, getPhoneNumber());
                pstmt.setString(4, getAddress());

                pstmt.executeUpdate();
                System.out.println("Customer added successfully!");

            } catch (InputMismatchException e) {
                System.out.println("Invalid input type. Please enter the correct data type.");
                scanner.nextLine(); // Clear the buffer
            }

        } catch (SQLException e) {
            System.out.println("Database error: Unable to add customer. Please check the database connection.");
            e.printStackTrace();
        }
    }

    public void viewCustomer() {
        try (Connection conn = DataBaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Customer")) {

            while (rs.next()) {
                setCustomerId(rs.getInt("customer_id"));
                setName(rs.getString("name"));
                setEmail(rs.getString("email"));
                setPhoneNumber(rs.getString("phone_number"));
                setAddress(rs.getString("address"));

                System.out.println("Customer ID: " + getCustomerId());
                System.out.println("Name: " + getName());
                System.out.println("Email: " + getEmail());
                System.out.println("Phone: " + getPhoneNumber());
                System.out.println("Address: " + getAddress());
                System.out.println("-----------------------------------");
            }

        } catch (SQLException e) {
            System.out.println("Database error: Unable to retrieve customers. Please try again later.");
            e.printStackTrace();
        }
    }

    public void updateCustomer() {
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(
                     "UPDATE Customer SET name = ?, email = ?, phone_number = ?, address = ? WHERE customer_id = ?")) {
            Scanner scanner = new Scanner(System.in);

            try {
                System.out.print("Enter customer ID to update: ");
                setCustomerId(scanner.nextInt());
                scanner.nextLine(); // consume newline

                System.out.print("Enter new customer name: ");
                setName(scanner.nextLine());

                System.out.print("Enter new customer email: ");
                setEmail(scanner.nextLine());

                System.out.print("Enter new customer phone number: ");
                setPhoneNumber(scanner.nextLine());

                System.out.print("Enter new customer address: ");
                setAddress(scanner.nextLine());

                pstmt.setString(1, getName());
                pstmt.setString(2, getEmail());
                pstmt.setString(3, getPhoneNumber());
                pstmt.setString(4, getAddress());
                pstmt.setInt(5, getCustomerId());

                pstmt.executeUpdate();
                System.out.println("Customer updated successfully!");

            } catch (InputMismatchException e) {
                System.out.println("Invalid input type. Please enter the correct data type.");
                scanner.nextLine(); // Clear the buffer
            }

        } catch (SQLException e) {
            System.out.println("Database error: Unable to update customer. Please try again later.");
            e.printStackTrace();
        }
    }

    public void deleteCustomer() {
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("DELETE FROM Customer WHERE customer_id = ?")) {
            Scanner scanner = new Scanner(System.in);

            try {
                System.out.print("Enter customer ID to delete: ");
                setCustomerId(scanner.nextInt());

                pstmt.setInt(1, getCustomerId());
                int rowsAffected = pstmt.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Customer deleted successfully!");
                } else {
                    System.out.println("Customer not found. Please check the ID and try again.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input type. Please enter the correct data type.");
                scanner.nextLine(); // Clear the buffer
            }

        } catch (SQLException e) {
            System.out.println("Database error: Unable to delete customer. Please try again later.");
            e.printStackTrace();
        }
    }
}
