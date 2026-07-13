package gui;

import model.Customer;
import service.CustomerService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class CustomerFrame extends JFrame {

    private final CustomerService customerService;
    private final DashboardFrame dashboardFrame;

    private JTable table;
    private DefaultTableModel model;

    private JTextField nameField;
    private JTextField ageField;
    private JTextField phoneField;
    private JTextField emailField;

    private JTextField searchField;

    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton refreshButton;
    private JButton backButton;
    private JButton searchButton;
    private JButton clearButton;

    public CustomerFrame(DashboardFrame dashboardFrame) {

        this.dashboardFrame = dashboardFrame;
        this.customerService = new CustomerService();

        initializeUI();

        loadCustomers();

        setVisible(true);
    }

    private void initializeUI() {

        setTitle("Customer Management");
        setSize(900,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        addWindowListener(new java.awt.event.WindowAdapter() {

            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {

                int option = JOptionPane.showConfirmDialog(
                        CustomerFrame.this,
                        "Return to Dashboard?",
                        "Exit",
                        JOptionPane.YES_NO_OPTION);

                if(option == JOptionPane.YES_OPTION){

                    dispose();

                    dashboardFrame.setVisible(true);

                }

            }

        });

        setLayout(new BorderLayout(10,10));

        JPanel northPanel = new JPanel(new BorderLayout());

        JLabel title = new JLabel(
                "CUSTOMER MANAGEMENT",
                SwingConstants.CENTER);

        title.setFont(new Font("Arial",Font.BOLD,24));

        northPanel.add(title,BorderLayout.NORTH);

        JPanel searchPanel = new JPanel();

        searchPanel.add(new JLabel("Search"));

        searchField = new JTextField(20);

        searchPanel.add(searchField);

        searchButton = new JButton("Search");

        clearButton = new JButton("Clear");

        searchPanel.add(searchButton);

        searchPanel.add(clearButton);

        northPanel.add(searchPanel,BorderLayout.SOUTH);

        add(northPanel,BorderLayout.NORTH);

        model = new DefaultTableModel(
                new Object[]{
                        "Customer_ID",
                        "Name",
                        "Age",
                        "Phone",
                        "Email"
                },0){

            @Override
            public boolean isCellEditable(int row,int column){

                return false;

            }

        };

        table = new JTable(model);

        table.setRowHeight(28);

        table.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION);

        table.getTableHeader().setFont(
                new Font("Arial",Font.BOLD,14));

        table.setAutoResizeMode(
                JTable.AUTO_RESIZE_ALL_COLUMNS);

        JScrollPane scrollPane =
                new JScrollPane(table);

        add(scrollPane,BorderLayout.CENTER);

        JPanel bottomPanel =
                new JPanel(new BorderLayout());

        JPanel formPanel =
                new JPanel(new GridLayout(4,2,10,10));

        formPanel.add(new JLabel("Name"));
        nameField = new JTextField();
        formPanel.add(nameField);

        formPanel.add(new JLabel("Age"));
        ageField = new JTextField();
        formPanel.add(ageField);

        formPanel.add(new JLabel("Phone"));
        phoneField = new JTextField();
        formPanel.add(phoneField);

        formPanel.add(new JLabel("Email"));
        emailField = new JTextField();
        formPanel.add(emailField);

        bottomPanel.add(formPanel,BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();

        addButton = new JButton("Add");
        updateButton = new JButton("Update");
        deleteButton = new JButton("Delete");
        refreshButton = new JButton("Refresh");
        backButton = new JButton("Back");

        Font buttonFont =
                new Font("Arial",Font.BOLD,14);

        addButton.setFont(buttonFont);
        updateButton.setFont(buttonFont);
        deleteButton.setFont(buttonFont);
        refreshButton.setFont(buttonFont);
        backButton.setFont(buttonFont);
        searchButton.setFont(buttonFont);
        clearButton.setFont(buttonFont);

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(refreshButton);
        buttonPanel.add(backButton);

        bottomPanel.add(buttonPanel,BorderLayout.SOUTH);

        add(bottomPanel,BorderLayout.SOUTH);

        refreshButton.addActionListener(e -> loadCustomers());

        addButton.addActionListener(e -> addCustomer());

        updateButton.addActionListener(e -> updateCustomer());

        deleteButton.addActionListener(e -> deleteCustomer());

        searchButton.addActionListener(e -> searchCustomer());

        clearButton.addActionListener(e -> {

            clearFields();

            searchField.setText("");

            loadCustomers();

        });

        backButton.addActionListener(e -> {

            dispose();

            dashboardFrame.setVisible(true);

        });

        table.getSelectionModel().addListSelectionListener(e -> {

            if(!e.getValueIsAdjusting()){

                int row = table.getSelectedRow();

                if(row!=-1){

                    nameField.setText(
                            model.getValueAt(row,1).toString());

                    ageField.setText(
                            model.getValueAt(row,2).toString());

                    phoneField.setText(
                            model.getValueAt(row,3).toString());

                    emailField.setText(
                            model.getValueAt(row,4).toString());

                }

            }

        });

    }

    private void loadCustomers() {

        model.setRowCount(0);

        List<Customer> customers = customerService.getAllCustomers();

        for (Customer customer : customers) {

            model.addRow(new Object[]{

                    customer.getCustomerId(),
                    customer.getName(),
                    customer.getAge(),
                    customer.getPhone(),
                    customer.getEmail()

            });

        }

        if (model.getRowCount() > 0) {

            table.setRowSelectionInterval(0, 0);

        }

    }

    private void addCustomer() {

        try {

            Customer customer = new Customer();

            customer.setName(nameField.getText().trim());
            customer.setAge(Integer.parseInt(ageField.getText().trim()));
            customer.setPhone(phoneField.getText().trim());
            customer.setEmail(emailField.getText().trim());

            if (customerService.addCustomer(customer)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Customer Added Successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);

                clearFields();

                loadCustomers();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Failed to Add Customer.");

            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter valid details.");

        }

    }

    private void updateCustomer() {

        int row = table.getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a customer.");

            return;

        }

        try {

            Customer customer = new Customer();

            customer.setCustomerId(
                    Integer.parseInt(
                            model.getValueAt(row, 0).toString()));

            customer.setName(nameField.getText().trim());

            customer.setAge(
                    Integer.parseInt(ageField.getText().trim()));

            customer.setPhone(phoneField.getText().trim());

            customer.setEmail(emailField.getText().trim());

            if (customerService.updateCustomer(customer)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Customer Updated Successfully.");

                clearFields();

                loadCustomers();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Update Failed.");

            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter valid details.");

        }

    }

    private void deleteCustomer() {

        int row = table.getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a customer.");

            return;

        }

        int customerId = Integer.parseInt(
                model.getValueAt(row, 0).toString());

        int option = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to delete this customer?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION);

        if (option == JOptionPane.YES_OPTION) {

            if (customerService.deleteCustomer(customerId)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Customer Deleted Successfully.");

                clearFields();

                loadCustomers();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Delete Failed.");

            }

        }

    }

    private void searchCustomer() {

        String keyword = searchField.getText().trim().toLowerCase();

        model.setRowCount(0);

        List<Customer> customers =
                customerService.getAllCustomers();

        for (Customer customer : customers) {

            if (customer.getName().toLowerCase().contains(keyword)
                    || customer.getPhone().contains(keyword)
                    || customer.getEmail().toLowerCase().contains(keyword)) {

                model.addRow(new Object[]{

                        customer.getCustomerId(),
                        customer.getName(),
                        customer.getAge(),
                        customer.getPhone(),
                        customer.getEmail()

                });

            }

        }

    }

    private void clearFields() {

        nameField.setText("");

        ageField.setText("");

        phoneField.setText("");

        emailField.setText("");

        table.clearSelection();

    }

}
