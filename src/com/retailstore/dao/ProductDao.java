package com.retailstore.dao;

import com.retailstore.model.Product;
import com.retailstore.util.DataBaseConnection;
import com.retailstore.exceptions.ProductNotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class ProductDao {
    public void addProduct(Product product) throws SQLException {
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(
                     "INSERT INTO Product(name, description, price, stock_quantity) VALUES (?, ?, ?, ?)")) {

            pstmt.setString(1, product.getName());
            pstmt.setString(2, product.getDescription());
            pstmt.setDouble(3, product.getPrice());
            pstmt.setInt(4, product.getStockQuantity());

            pstmt.executeUpdate();
        }
    }

    public List<Product> getAllProducts() throws SQLException {
        List<Product> products = new ArrayList<>();

        try (Connection conn = DataBaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Product")) {

            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("product_id"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
                product.setPrice(rs.getDouble("price"));
                product.setStockQuantity(rs.getInt("stock_quantity"));
                products.add(product);
            }
        }

        return products;
    }

    public void updateProduct(Product product) throws SQLException, ProductNotFoundException {
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(
                     "UPDATE Product SET name = ?, description = ?, price = ?, stock_quantity = ? WHERE product_id = ?")) {

            if (!productExists(conn, product.getProductId())) {
                throw new ProductNotFoundException("Product with ID " + product.getProductId() + " not found.");
            }

            pstmt.setString(1, product.getName());
            pstmt.setString(2, product.getDescription());
            pstmt.setDouble(3, product.getPrice());
            pstmt.setInt(4, product.getStockQuantity());
            pstmt.setInt(5, product.getProductId());

            pstmt.executeUpdate();
        }
    }

    public void deleteProduct(int productId) throws SQLException, ProductNotFoundException {
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("DELETE FROM Product WHERE product_id = ?")) {

            if (!productExists(conn, productId)) {
                throw new ProductNotFoundException("Product with ID " + productId + " not found.");
            }

            pstmt.setInt(1, productId);
            pstmt.executeUpdate();
        }
    }

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
