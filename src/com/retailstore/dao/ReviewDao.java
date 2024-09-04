package com.retailstore.dao;

import com.retailstore.model.Review;
import com.retailstore.util.DataBaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class ReviewDao {
    public void addReview(Review review) throws SQLException {
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(
                     "INSERT INTO Review(product_id, customer_id, rating, comment, review_date) VALUES (?, ?, ?, ?, ?)")) {

            pstmt.setInt(1, review.getProductId());
            pstmt.setInt(2, review.getCustomerId());
            pstmt.setInt(3, review.getRating());
            pstmt.setString(4, review.getComment());
            pstmt.setDate(5, review.getReviewDate());

            pstmt.executeUpdate();
        }
    }

    public List<Review> getReviewsByProductId(int productId) throws SQLException {
        List<Review> reviews = new ArrayList<>();

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(
                     "SELECT * FROM Review WHERE product_id = ?")) {

            pstmt.setInt(1, productId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Review review = new Review();
                review.setReviewId(rs.getInt("review_id"));
                review.setProductId(rs.getInt("product_id"));
                review.setCustomerId(rs.getInt("customer_id"));
                review.setRating(rs.getInt("rating"));
                review.setComment(rs.getString("comment"));
                review.setReviewDate(rs.getDate("review_date"));
                reviews.add(review);
            }
        }

        return reviews;
    }

    public void deleteReview(int reviewId) throws SQLException {
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("DELETE FROM Review WHERE review_id = ?")) {

            pstmt.setInt(1, reviewId);
            pstmt.executeUpdate();
        }
    }
}
