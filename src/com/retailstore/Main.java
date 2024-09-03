package com.retailstore;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Product productManager = new Product();
        Customer customerManager = new Customer();
        Review reviewManager = new Review();

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
                    productMenu(productManager);
                    break;
                case 2:
                    customerMenu(customerManager);
                    break;
                case 3:
                    reviewMenu(reviewManager);
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

    private static void productMenu(Product productManager) {
        Scanner scanner = new Scanner(System.in);

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
                    productManager.addProduct();
                    break;
                case 2:
                    productManager.viewProduct();
                    break;
                case 3:
                    productManager.updateProduct();
                    break;
                case 4:
                    productManager.deleteProduct();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void customerMenu(Customer customerManager) {
        Scanner scanner = new Scanner(System.in);

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
                    customerManager.addCustomer();
                    break;
                case 2:
                    customerManager.viewCustomer();
                    break;
                case 3:
                    customerManager.updateCustomer();
                    break;
                case 4:
                    customerManager.deleteCustomer();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void reviewMenu(Review reviewManager) {
        Scanner scanner = new Scanner(System.in);

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
                    reviewManager.addReview();
                    break;
                case 2:
                    reviewManager.viewReviews();
                    break;
                case 3:
                    reviewManager.deleteReview();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
