package com.retailstore.service;

import com.retailstore.dao.CustomerDao;
import com.retailstore.model.Customer;

import java.sql.SQLException;
import java.util.List;
public class CustomerService {
    private final CustomerDao customerDao = new CustomerDao();

    public void addCustomer(Customer customer) {
        try {
            customerDao.addCustomer(customer);
            System.out.println("Customer added successfully!");
        } catch (SQLException e) {
            System.out.println("Database error: Unable to add customer.");
            e.printStackTrace();
        }
    }

    public void viewCustomers() {
        try {
            List<Customer> customers = customerDao.getAllCustomers();
            for (Customer customer : customers) {
                System.out.println("Customer ID: " + customer.getCustomerId());
                System.out.println("Name: " + customer.getName());
                System.out.println("Email: " + customer.getEmail());
                System.out.println("Phone: " + customer.getPhoneNumber());
                System.out.println("Address: " + customer.getAddress());
                System.out.println("-----------------------------------");
            }
        } catch (SQLException e) {
            System.out.println("Database error: Unable to retrieve customers.");
            e.printStackTrace();
        }
    }

    public void updateCustomer(Customer customer) {
        try {
            customerDao.updateCustomer(customer);
            System.out.println("Customer updated successfully!");
        } catch (SQLException e) {
            System.out.println("Database error: Unable to update customer.");
            e.printStackTrace();
        }
    }

    public void deleteCustomer(int customerId) {
        try {
            customerDao.deleteCustomer(customerId);
            System.out.println("Customer deleted successfully!");
        } catch (SQLException e) {
            System.out.println("Database error: Unable to delete customer.");
            e.printStackTrace();
        }
    }
}
