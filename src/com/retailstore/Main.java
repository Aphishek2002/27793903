package com.retailstore;

import com.retailstore.model.Product;
import com.retailstore.model.Customer;
import com.retailstore.model.Review;
import com.retailstore.service.ProductService;
import com.retailstore.service.CustomerService;
import com.retailstore.service.ReviewService;

import java.sql.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ProductService productService = new ProductService();
        CustomerService customerService = new CustomerService();
        ReviewService reviewService = new ReviewService();

        while (true) {
            System.out.println("Welcome to the Online Retail Store Management System");
            System.out.println("Please select an option:");
            System.out.println("1. Product Management");
            System.out.println("2. Customer Management");
            System.out.println("3. Review Management");
            System.out.println("4. Exit");
            System.out.print("> ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    productMenu(scanner, productService);
                    break;
                case 2:
                    customerMenu(scanner, customerService);
                    break;
                case 3:
                    reviewMenu(scanner, reviewService);
                    break;
                case 4:
                    System.out.println("Thank you for using the Online Retail Store Management System. Goodbye!");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void productMenu(Scanner scanner, ProductService productService) {
        while (true) {
            System.out.println("Product Management:");
            System.out.println("1. Add Product");
            System.out.println("2. View Products");
            System.out.println("3. Update Product");
            System.out.println("4. Delete Product");
            System.out.println("5. Back to Main Menu");
            System.out.print("> ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    Product product = new Product();
                    scanner.nextLine(); // consume newline

                    System.out.print("Enter product name: ");
                    product.setName(scanner.nextLine());

                    System.out.print("Enter product description: ");
                    product.setDescription(scanner.nextLine());

                    System.out.print("Enter product price: ");
                    product.setPrice(scanner.nextDouble());

                    System.out.print("Enter stock quantity: ");
                    product.setStockQuantity(scanner.nextInt());

                    productService.addProduct(product);
                    break;
                case 2:
                    productService.viewProducts();
                    break;
                case 3:
                    Product updateProduct = new Product();

                    System.out.print("Enter product ID to update: ");
                    updateProduct.setProductId(scanner.nextInt());
                    scanner.nextLine(); // consume newline

                    System.out.print("Enter new product name: ");
                    updateProduct.setName(scanner.nextLine());

                    System.out.print("Enter new product description: ");
                    updateProduct.setDescription(scanner.nextLine());

                    System.out.print("Enter new product price: ");
                    updateProduct.setPrice(scanner.nextDouble());

                    System.out.print("Enter new stock quantity: ");
                    updateProduct.setStockQuantity(scanner.nextInt());

                    productService.updateProduct(updateProduct);
                    break;
                case 4:
                    System.out.print("Enter product ID to delete: ");
                    int productId = scanner.nextInt();
                    productService.deleteProduct(productId);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void customerMenu(Scanner scanner, CustomerService customerService) {
        while (true) {
            System.out.println("Customer Management:");
            System.out.println("1. Add Customer");
            System.out.println("2. View Customers");
            System.out.println("3. Update Customer");
            System.out.println("4. Delete Customer");
            System.out.println("5. Back to Main Menu");
            System.out.print("> ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    Customer customer = new Customer();
                    scanner.nextLine(); // consume newline

                    System.out.print("Enter customer name: ");
                    customer.setName(scanner.nextLine());

                    System.out.print("Enter customer email: ");
                    customer.setEmail(scanner.nextLine());

                    System.out.print("Enter customer phone number: ");
                    customer.setPhoneNumber(scanner.nextLine());

                    System.out.print("Enter customer address: ");
                    customer.setAddress(scanner.nextLine());

                    customerService.addCustomer(customer);
                    break;
                case 2:
                    customerService.viewCustomers();
                    break;
                case 3:
                    Customer updateCustomer = new Customer();

                    System.out.print("Enter customer ID to update: ");
                    updateCustomer.setCustomerId(scanner.nextInt());
                    scanner.nextLine(); // consume newline

                    System.out.print("Enter new customer name: ");
                    updateCustomer.setName(scanner.nextLine());

                    System.out.print("Enter new customer email: ");
                    updateCustomer.setEmail(scanner.nextLine());

                    System.out.print("Enter new customer phone number: ");
                    updateCustomer.setPhoneNumber(scanner.nextLine());

                    System.out.print("Enter new customer address: ");
                    updateCustomer.setAddress(scanner.nextLine());

                    customerService.updateCustomer(updateCustomer);
                    break;
                case 4:
                    System.out.print("Enter customer ID to delete: ");
                    int customerId = scanner.nextInt();
                    customerService.deleteCustomer(customerId);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void reviewMenu(Scanner scanner, ReviewService reviewService) {
        while (true) {
            System.out.println("Review Management:");
            System.out.println("1. Add Review");
            System.out.println("2. View Reviews");
            System.out.println("3. Delete Review");
            System.out.println("4. Back to Main Menu");
            System.out.print("> ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    Review review = new Review();
                    scanner.nextLine(); // consume newline

                    System.out.print("Enter product ID: ");
                    review.setProductId(scanner.nextInt());

                    System.out.print("Enter customer ID: ");
                    review.setCustomerId(scanner.nextInt());

                    System.out.print("Enter rating (1-5): ");
                    review.setRating(scanner.nextInt());
                    scanner.nextLine(); // consume newline

                    System.out.print("Enter comment: ");
                    review.setComment(scanner.nextLine());

                    review.setReviewDate(new Date(System.currentTimeMillis())); // Set current date as review date

                    reviewService.addReview(review);
                    break;
                case 2:
                    System.out.print("Enter product ID to view reviews: ");
                    int productId = scanner.nextInt();
                    reviewService.viewReviews(productId);
                    break;
                case 3:
                    System.out.print("Enter review ID to delete: ");
                    int reviewId = scanner.nextInt();
                    reviewService.deleteReview(reviewId);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
