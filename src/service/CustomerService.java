package service;

import dao.CustomerDAO;
import model.Customer;

import java.util.List;

public class CustomerService {

    private CustomerDAO customerDAO;

    public CustomerService() {
        customerDAO = new CustomerDAO();
    }

    // Add Customer
    public boolean addCustomer(Customer customer) {

        if (customer == null) {
            System.out.println("Customer cannot be null.");
            return false;
        }

        if (customer.getName() == null || customer.getName().trim().isEmpty()) {
            System.out.println("Customer name is required.");
            return false;
        }

        if (customer.getAge() <= 0) {
            System.out.println("Invalid age.");
            return false;
        }

        if (customer.getPhone() == null || customer.getPhone().length() != 10) {
            System.out.println("Phone number must contain 10 digits.");
            return false;
        }

        if (customer.getEmail() == null || !customer.getEmail().contains("@")) {
            System.out.println("Invalid email.");
            return false;
        }

        return customerDAO.addCustomer(customer);
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
