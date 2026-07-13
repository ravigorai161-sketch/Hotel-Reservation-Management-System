package service;

import dao.CustomerDAO;

import model.Customer;
import util.LoggerUtil;
import java.util.logging.Logger;

import java.util.List;

public class CustomerService {
    private static final Logger logger = LoggerUtil.getLogger();

    private CustomerDAO customerDAO;

    public CustomerService() {
        customerDAO = new CustomerDAO();
    }

    // Add Customer
    public boolean addCustomer(Customer customer)  {

        if (customer == null) {
            logger.warning("Failed to add customer: Customer object is null.");
            System.out.println("Customer cannot be null.");
            logger.warning("Failed to add customer: Customer object is null.");
            return false;
        }

        if (customer.getName() == null || customer.getName().trim().isEmpty()) {
            System.out.println("Customer name is required.");
            logger.warning("Failed to add customer: Name is empty.");
            return false;
        }

        if (customer.getAge() <= 0) {
            System.out.println("Invalid age.");
            logger.warning("Failed to add customer: Invalid age.");
            return false;
        }

        if (customer.getPhone() == null || customer.getPhone().length() != 10) {
            System.out.println("Phone number must contain 10 digits.");
            logger.warning("Failed to add customer: Invalid phone number.");
            return false;
        }

        if (customer.getEmail() == null || !customer.getEmail().contains("@")) {
            System.out.println("Invalid email.");
            logger.warning("Failed to add customer: Invalid email.");
            return false;
        }

        boolean added = customerDAO.addCustomer(customer);

        if (added) {
            logger.info("Customer added successfully. Name: " + customer.getName());
        } else {
            logger.warning("Database failed to add customer. Name: " + customer.getName());
        }

        return added;
    }

    // Update Customer
    public boolean updateCustomer(Customer customer) {

        if (customer == null) {
            return false;
        }

        if (!customerDAO.customerExists(customer.getCustomerId())) {
            System.out.println("Customer not found.");
            return false;
        }

        return customerDAO.updateCustomer(customer);
    }

    // Delete Customer
    public boolean deleteCustomer(int customerId) {

        if (!customerDAO.customerExists(customerId)) {
            System.out.println("Customer not found.");
            return false;
        }

        return customerDAO.deleteCustomer(customerId);
    }

    // Get Customer By ID
    public Customer getCustomerById(int customerId) {

        return customerDAO.getCustomerById(customerId);
    }

    // View All Customers
    public List<Customer> getAllCustomers() {

        return customerDAO.getAllCustomers();
    }

    // Search Customer By Name
    public List<Customer> searchCustomerByName(String name) {

        return customerDAO.searchCustomerByName(name);
    }

    // Search Customer By Phone
    public Customer searchCustomerByPhone(String phone) {

        return customerDAO.searchCustomerByPhone(phone);
    }

    // Check Customer Exists
    public boolean customerExists(int customerId) {

        return customerDAO.customerExists(customerId);
    }

    // Total Customers
    public int getCustomerCount() {

        return customerDAO.getCustomerCount();
    }
}
