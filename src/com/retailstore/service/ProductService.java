package com.retailstore.service;

import com.retailstore.dao.ProductDao;
import com.retailstore.model.Product;
import com.retailstore.exceptions.ProductNotFoundException;

import java.sql.SQLException;
import java.util.List;
public class ProductService {
    private final ProductDao productDao = new ProductDao();

    public void addProduct(Product product) {
        try {
            productDao.addProduct(product);
            System.out.println("Product added successfully!");
        } catch (SQLException e) {
            System.out.println("Database error: Unable to add product.");
            e.printStackTrace();
        }
    }

    public void viewProducts() {
        try {
            List<Product> products = productDao.getAllProducts();
            for (Product product : products) {
                System.out.println("Product ID: " + product.getProductId());
                System.out.println("Name: " + product.getName());
                System.out.println("Description: " + product.getDescription());
                System.out.println("Price: " + product.getPrice());
                System.out.println("Stock Quantity: " + product.getStockQuantity());
                System.out.println("-----------------------------------");
            }
        } catch (SQLException e) {
            System.out.println("Database error: Unable to retrieve products.");
            e.printStackTrace();
        }
    }

    public void updateProduct(Product product) {
        try {
            productDao.updateProduct(product);
            System.out.println("Product updated successfully!");
        } catch (SQLException | ProductNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteProduct(int productId) {
        try {
            productDao.deleteProduct(productId);
            System.out.println("Product deleted successfully!");
        } catch (SQLException | ProductNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
