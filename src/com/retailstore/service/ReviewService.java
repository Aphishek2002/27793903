package com.retailstore.service;

import com.retailstore.dao.ReviewDao;
import com.retailstore.model.Review;

import java.sql.SQLException;
import java.util.List;
public class ReviewService {
    private final ReviewDao reviewDao = new ReviewDao();

    public void addReview(Review review) {
        try {
            reviewDao.addReview(review);
            System.out.println("Review added successfully!");
        } catch (SQLException e) {
            System.out.println("Database error: Unable to add review.");
            e.printStackTrace();
        }
    }

    public void viewReviews(int productId) {
        try {
            List<Review> reviews = reviewDao.getReviewsByProductId(productId);
            for (Review review : reviews) {
                System.out.println("Review ID: " + review.getReviewId());
                System.out.println("Rating: " + review.getRating());
                System.out.println("Comment: " + review.getComment());
                System.out.println("Review Date: " + review.getReviewDate());
                System.out.println("-----------------------------------");
            }
        } catch (SQLException e) {
            System.out.println("Database error: Unable to retrieve reviews.");
            e.printStackTrace();
        }
    }

    public void deleteReview(int reviewId) {
        try {
            reviewDao.deleteReview(reviewId);
            System.out.println("Review deleted successfully!");
        } catch (SQLException e) {
            System.out.println("Database error: Unable to delete review.");
            e.printStackTrace();
        }
    }
}
