package com.retailstore;

import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Review {

    // Private fields for encapsulation
    private int reviewId;
    private int productId;
    private int customerId;
    private int rating;
    private String comment;
    private Date reviewDate;

    // Getters and Setters
    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }

    // Methods for Review Management with Exception Handling
    public void addReview() {
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(
                     "INSERT INTO Review(product_id, customer_id, rating, comment, review_date) VALUES (?, ?, ?, ?, ?)")) {

            Scanner scanner = new Scanner(System.in);

            try {
                System.out.print("Enter product ID: ");
                setProductId(scanner.nextInt());

                System.out.print("Enter customer ID: ");
                setCustomerId(scanner.nextInt());

                System.out.print("Enter rating (1-5): ");
                setRating(scanner.nextInt());
                scanner.nextLine(); // consume newline

                System.out.print("Enter comment: ");
                setComment(scanner.nextLine());

                setReviewDate(new Date(System.currentTimeMillis())); // Set current date as review date

                pstmt.setInt(1, getProductId());
                pstmt.setInt(2, getCustomerId());
                pstmt.setInt(3, getRating());
                pstmt.setString(4, getComment());
                pstmt.setDate(5, getReviewDate());

                pstmt.executeUpdate();
                System.out.println("Review added successfully!");

            } catch (InputMismatchException e) {
                System.out.println("Invalid input type. Please enter the correct data type.");
                scanner.nextLine(); // Clear the buffer
            }

        } catch (SQLException e) {
            System.out.println("Database error: Unable to add review. Please check the database connection.");
            e.printStackTrace();
        }
    }

    public void viewReviews() {
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(
                     "SELECT r.review_id, p.name AS product_name, c.name AS customer_name, r.rating, r.comment, r.review_date " +
                             "FROM Review r " +
                             "JOIN Product p ON r.product_id = p.product_id " +
                             "JOIN Customer c ON r.customer_id = c.customer_id " +
                             "WHERE p.product_id = ?")) {

            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter product ID to view reviews: ");
            int productId = scanner.nextInt();

            pstmt.setInt(1, productId);

            ResultSet rs = pstmt.executeQuery();

            if (!rs.isBeforeFirst()) { // Check if there are no reviews
                System.out.println("No reviews found for this product.");
                return;
            }

            while (rs.next()) {
                System.out.println("Review ID: " + rs.getInt("review_id"));
                System.out.println("Product Name: " + rs.getString("product_name"));
                System.out.println("Customer Name: " + rs.getString("customer_name"));
                System.out.println("Rating: " + rs.getInt("rating"));
                System.out.println("Comment: " + rs.getString("comment"));
                System.out.println("Review Date: " + rs.getDate("review_date"));
                System.out.println("-----------------------------------");
            }

        } catch (SQLException e) {
            System.out.println("Database error: Unable to retrieve reviews. Please try again later.");
            e.printStackTrace();
        }
    }

    public void deleteReview() {
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("DELETE FROM Review WHERE review_id = ?")) {

            Scanner scanner = new Scanner(System.in);

            try {
                System.out.print("Enter review ID to delete: ");
                setReviewId(scanner.nextInt());

                pstmt.setInt(1, getReviewId());
                int rowsAffected = pstmt.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Review deleted successfully!");
                } else {
                    System.out.println("Review not found. Please check the ID and try again.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input type. Please enter the correct data type.");
                scanner.nextLine(); // Clear the buffer
            }

        } catch (SQLException e) {
            System.out.println("Database error: Unable to delete review. Please check the database connection.");
            e.printStackTrace();
        }
    }
}
