package ui;

import validation.Validator;
import model.Customer;
import service.CustomerService;

import java.util.List;
import java.util.Scanner;

public class CustomerMenu {

    private final Scanner scanner;
    private final CustomerService customerService;

    public CustomerMenu() {
        scanner = new Scanner(System.in);
        customerService = new CustomerService();
    }

    public void start() {

        while (true) {

            System.out.println("\n========== CUSTOMER MANAGEMENT ==========");
            System.out.println("1. Add Customer");
            System.out.println("2. View All Customers");
            System.out.println("3. Search Customer");
            System.out.println("4. Delete Customer");
            System.out.println("5. Back");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    addCustomer();
                    break;

                case 2:
                    viewCustomers();
                    break;

                case 3:
                    searchCustomer();
                    break;

                case 4:
                    deleteCustomer();
                    break;

                case 5:
                    return;

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }

    private void addCustomer() {

        Customer customer = new Customer();

        System.out.print("Name: ");
        String name = scanner.nextLine();

        if (!Validator.isValidName(name)) {

            System.out.println("Invalid Name.");
            return;
        }

        customer.setName(name);

        System.out.print("Age: ");
        int age = scanner.nextInt();
        scanner.nextLine();

        if (!Validator.isValidAge(age)) {

            System.out.println("Age must be between 18 and 120.");
            return;
        }

        customer.setAge(age);

        System.out.print("Gender: ");
        customer.setGender(scanner.nextLine());

        System.out.print("Phone: ");
        String phone = scanner.nextLine();

        if (!Validator.isValidPhone(phone)) {

            System.out.println("Invalid Phone Number.");
            return;
        }

        customer.setPhone(phone);

        System.out.print("Email: ");
        String email = scanner.nextLine();

        if (!Validator.isValidEmail(email)) {

            System.out.println("Invalid Email.");
            return;
        }

        customer.setEmail(email);

        System.out.print("Address: ");
        customer.setAddress(scanner.nextLine());

        if (customerService.addCustomer(customer)) {
            System.out.println("Customer Added Successfully.");
        } else {
            System.out.println("Failed to Add Customer.");
        }
    }

    private void viewCustomers() {

        List<Customer> customers = customerService.getAllCustomers();

        if (customers.isEmpty()) {
            System.out.println("No customers found.");
            return;
        }

        System.out.println("\n============= CUSTOMER LIST =============");

        for (Customer customer : customers) {

            System.out.println("--------------------------------------");
            System.out.println("ID      : " + customer.getCustomerId());
            System.out.println("Name    : " + customer.getName());
            System.out.println("Age     : " + customer.getAge());
            System.out.println("Gender  : " + customer.getGender());
            System.out.println("Phone   : " + customer.getPhone());
            System.out.println("Email   : " + customer.getEmail());

        }

        System.out.println("--------------------------------------");
    }

    private void searchCustomer() {

        System.out.print("Enter Customer ID: ");

        int id = scanner.nextInt();
        scanner.nextLine();

        Customer customer = customerService.getCustomerById(id);

        if (customer == null) {

            System.out.println("Customer Not Found.");
            return;

        }

        System.out.println("\nCustomer Details");
        System.out.println("-------------------------");
        System.out.println("ID      : " + customer.getCustomerId());
        System.out.println("Name    : " + customer.getName());
        System.out.println("Age     : " + customer.getAge());
        System.out.println("Gender  : " + customer.getGender());
        System.out.println("Phone   : " + customer.getPhone());
        System.out.println("Email   : " + customer.getEmail());
    }

    private void deleteCustomer() {

        System.out.print("Enter Customer ID: ");

        int id = scanner.nextInt();
        scanner.nextLine();

        if (customerService.deleteCustomer(id)) {

            System.out.println("Customer Deleted Successfully.");

        } else {

            System.out.println("Customer Not Found.");

        }
    }
}