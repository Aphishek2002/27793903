package com.retailstore;

import com.retailstore.exceptions.ProductNotFoundException;

import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Product {

    // Private fields for encapsulation
    private int productId;
    private String name;
    private String description;
    private double price;
    private int stockQuantity;

    // Getters and Setters
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    // Methods for Product Management with Exception Handling
    public void addProduct() {
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(
                     "INSERT INTO Product(name, description, price, stock_quantity) VALUES (?, ?, ?, ?)")) {
            Scanner scanner = new Scanner(System.in);

            try {
                System.out.print("Enter product name: ");
                setName(scanner.nextLine());

                System.out.print("Enter product description: ");
                setDescription(scanner.nextLine());

                System.out.print("Enter product price: ");
                setPrice(scanner.nextDouble());

                System.out.print("Enter stock quantity: ");
                setStockQuantity(scanner.nextInt());

                pstmt.setString(1, getName());
                pstmt.setString(2, getDescription());
                pstmt.setDouble(3, getPrice());
                pstmt.setInt(4, getStockQuantity());

                pstmt.executeUpdate();
                System.out.println("Product added successfully!");

            } catch (InputMismatchException e) {
                System.out.println("Invalid input type. Please enter the correct data type.");
                scanner.nextLine(); // Clear the buffer
            }

        } catch (SQLException e) {
            System.out.println("Database error: Unable to add product. Please check the database connection.");
            e.printStackTrace();
        }
    }

    public void viewProduct() {
        try (Connection conn = DataBaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Product")) {

            while (rs.next()) {
                setProductId(rs.getInt("product_id"));
                setName(rs.getString("name"));
                setDescription(rs.getString("description"));
                setPrice(rs.getDouble("price"));
                setStockQuantity(rs.getInt("stock_quantity"));

                System.out.println("Product ID: " + getProductId());
                System.out.println("Name: " + getName());
                System.out.println("Description: " + getDescription());
                System.out.println("Price: " + getPrice());
                System.out.println("Stock Quantity: " + getStockQuantity());
                System.out.println("-----------------------------------");
            }

        } catch (SQLException e) {
            System.out.println("Database error: Unable to retrieve products. Please try again later.");
            e.printStackTrace();
        }
    }

    public void updateProduct() {
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(
                     "UPDATE Product SET name = ?, description = ?, price = ?, stock_quantity = ? WHERE product_id = ?")) {
            Scanner scanner = new Scanner(System.in);

            try {
                System.out.print("Enter product ID to update: ");
                setProductId(scanner.nextInt());
                scanner.nextLine(); // consume newline

                // Check if product exists
                if (!productExists(conn, getProductId())) {
                    throw new ProductNotFoundException("Product with ID " + getProductId() + " not found.");
                }

                System.out.print("Enter new product name: ");
                setName(scanner.nextLine());

                System.out.print("Enter new product description: ");
                setDescription(scanner.nextLine());

                System.out.print("Enter new product price: ");
                setPrice(scanner.nextDouble());

                System.out.print("Enter new stock quantity: ");
                setStockQuantity(scanner.nextInt());

                pstmt.setString(1, getName());
                pstmt.setString(2, getDescription());
                pstmt.setDouble(3, getPrice());
                pstmt.setInt(4, getStockQuantity());
                pstmt.setInt(5, getProductId());

                pstmt.executeUpdate();
                System.out.println("Product updated successfully!");

            } catch (InputMismatchException e) {
                System.out.println("Invalid input type. Please enter the correct data type.");
                scanner.nextLine(); // Clear the buffer
            } catch (ProductNotFoundException e) {
                System.out.println(e.getMessage());
            }

        } catch (SQLException e) {
            System.out.println("Database error: Unable to update product. Please try again later.");
            e.printStackTrace();
        }
    }

    public void deleteProduct() {
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("DELETE FROM Product WHERE product_id = ?")) {
            Scanner scanner = new Scanner(System.in);

            try {
                System.out.print("Enter product ID to delete: ");
                setProductId(scanner.nextInt());

                // Check if product exists
                if (!productExists(conn, getProductId())) {
                    throw new ProductNotFoundException("Product with ID " + getProductId() + " not found.");
                }

                pstmt.setInt(1, getProductId());
                int rowsAffected = pstmt.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Product deleted successfully!");
                } else {
                    System.out.println("Product not found. Please check the ID and try again.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input type. Please enter the correct data type.");
                scanner.nextLine(); // Clear the buffer
            } catch (ProductNotFoundException e) {
                System.out.println(e.getMessage());
            }

        } catch (SQLException e) {
            System.out.println("Database error: Unable to delete product. Please try again later.");
            e.printStackTrace();
        }
    }

    // Utility method to check if a product exists
    private boolean productExists(Connection conn, int productId) throws SQLException {
        String query = "SELECT COUNT(*) FROM Product WHERE product_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, productId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }
}
