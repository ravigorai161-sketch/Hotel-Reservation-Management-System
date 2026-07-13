package gui;

import model.Payment;
import service.PaymentService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PaymentFrame extends JFrame {

    private final PaymentService paymentService;
    private final DashboardFrame dashboardFrame;

    private JTable table;
    private DefaultTableModel model;

    private JTextField paymentIdField;
    private JTextField reservationIdField;
    private JTextField amountField;
    private JTextField methodField;
    private JTextField statusField;
    private JTextField searchField;

    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton refreshButton;
    private JButton searchButton;
    private JButton clearButton;
    private JButton backButton;

    public PaymentFrame(DashboardFrame dashboardFrame) {

        this.dashboardFrame = dashboardFrame;
        this.paymentService = new PaymentService();

        initializeUI();

        loadPayments();

        setVisible(true);
    }

    private void initializeUI() {

        setTitle("Payment Management");

        setSize(1000,650);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        addWindowListener(new java.awt.event.WindowAdapter() {

            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {

                int option = JOptionPane.showConfirmDialog(

                        PaymentFrame.this,

                        "Return to Dashboard?",

                        "Exit",

                        JOptionPane.YES_NO_OPTION);

                if(option==JOptionPane.YES_OPTION){

                    dispose();

                    dashboardFrame.setVisible(true);

                }

            }

        });

        setLayout(new BorderLayout(10,10));

        JPanel northPanel = new JPanel(new BorderLayout());

        JLabel title = new JLabel(
                "PAYMENT MANAGEMENT",
                SwingConstants.CENTER);

        title.setFont(new Font("Arial",Font.BOLD,24));

        northPanel.add(title,BorderLayout.NORTH);

        JPanel searchPanel = new JPanel();

        searchPanel.add(new JLabel("Search"));

        searchField = new JTextField(20);

        searchButton = new JButton("Search");

        clearButton = new JButton("Clear");

        searchPanel.add(searchField);

        searchPanel.add(searchButton);

        searchPanel.add(clearButton);

        northPanel.add(searchPanel,BorderLayout.SOUTH);

        add(northPanel,BorderLayout.NORTH);

        model = new DefaultTableModel(

                new Object[]{

                        "Payment ID",
                        "Reservation ID",
                        "Amount",
                        "Method",
                        "Status"

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

        JScrollPane scrollPane =
                new JScrollPane(table);

        add(scrollPane,BorderLayout.CENTER);

        JPanel bottomPanel =
                new JPanel(new BorderLayout());

        JPanel formPanel =
                new JPanel(new GridLayout(5,2,10,10));

        formPanel.add(new JLabel("Payment ID"));

        paymentIdField = new JTextField();

        formPanel.add(paymentIdField);

        formPanel.add(new JLabel("Reservation ID"));

        reservationIdField = new JTextField();

        formPanel.add(reservationIdField);

        formPanel.add(new JLabel("Amount"));

        amountField = new JTextField();

        formPanel.add(amountField);

        formPanel.add(new JLabel("Payment Method"));

        methodField = new JTextField();

        formPanel.add(methodField);

        formPanel.add(new JLabel("Payment Status"));

        statusField = new JTextField();

        formPanel.add(statusField);

        bottomPanel.add(formPanel,BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();

        addButton = new JButton("Add");

        updateButton = new JButton("Update Status");

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

        refreshButton.addActionListener(e -> loadPayments());

        addButton.addActionListener(e -> addPayment());

        updateButton.addActionListener(e -> updatePaymentStatus());

        deleteButton.addActionListener(e -> deletePayment());

        searchButton.addActionListener(e -> searchPayment());

        clearButton.addActionListener(e -> {

            clearFields();

            searchField.setText("");

            loadPayments();

        });

        backButton.addActionListener(e -> {

            dispose();

            dashboardFrame.setVisible(true);

        });

        table.getSelectionModel().addListSelectionListener(e -> {

            if(!e.getValueIsAdjusting()){

                int row = table.getSelectedRow();

                if(row!=-1){

                    paymentIdField.setText(
                            model.getValueAt(row,0).toString());

                    reservationIdField.setText(
                            model.getValueAt(row,1).toString());

                    amountField.setText(
                            model.getValueAt(row,2).toString());

                    methodField.setText(
                            model.getValueAt(row,3).toString());

                    statusField.setText(
                            model.getValueAt(row,4).toString());

                }

            }

        });

    }private void loadPayments() {

        model.setRowCount(0);

        List<Payment> payments = paymentService.getAllPayments();

        for (Payment payment : payments) {

            model.addRow(new Object[]{

                    payment.getPaymentId(),
                    payment.getReservationId(),
                    payment.getTotalAmount(),
                    payment.getPaymentMethod(),
                    payment.getPaymentStatus()

            });

        }

    }

    private void addPayment() {

        try {

            Payment payment = new Payment();

            payment.setReservationId(
                    Integer.parseInt(reservationIdField.getText().trim()));

            payment.setTotalAmount(
                    Double.parseDouble(amountField.getText().trim()));

            payment.setPaymentMethod(
                    methodField.getText().trim());

            payment.setPaymentStatus(
                    statusField.getText().trim());

            if (paymentService.addPayment(payment)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Payment Added Successfully.");

                clearFields();

                loadPayments();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Failed to Add Payment.");

            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter valid Input.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);

        }

    }

    private void updatePaymentStatus() {

        try {

            int paymentId =
                    Integer.parseInt(paymentIdField.getText().trim());

            String status =
                    statusField.getText().trim();

            if (paymentService.updatePaymentStatus(paymentId, status)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Payment Status Updated.");

                loadPayments();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Update Failed.");

            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter valid Input",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);

        }

    }

    private void deletePayment() {

        int row = table.getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a payment.");

            return;

        }

        int paymentId =
                Integer.parseInt(
                        model.getValueAt(row,0).toString());

        int option =
                JOptionPane.showConfirmDialog(
                        this,
                        "Delete this payment?",
                        "Confirm",
                        JOptionPane.YES_NO_OPTION);

        if (option == JOptionPane.YES_OPTION) {

            if (paymentService.deletePayment(paymentId)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Payment Deleted Successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);

                clearFields();

                loadPayments();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Delete Failed.");

            }

        }

    }

    private void searchPayment() {

        String keyword =
                searchField.getText().trim().toLowerCase();

        model.setRowCount(0);

        List<Payment> payments =
                paymentService.getAllPayments();

        for (Payment payment : payments) {

            if (String.valueOf(payment.getPaymentId()).contains(keyword)
                    ||
                    String.valueOf(payment.getReservationId()).contains(keyword)
                    ||
                    payment.getPaymentMethod().toLowerCase().contains(keyword)
                    ||
                    payment.getPaymentStatus().toLowerCase().contains(keyword)) {

                model.addRow(new Object[]{

                        payment.getPaymentId(),
                        payment.getReservationId(),
                        payment.getTotalAmount(),
                        payment.getPaymentMethod(),
                        payment.getPaymentStatus()

                });

            }

        }

    }

    private void clearFields() {

        paymentIdField.setText("");

        reservationIdField.setText("");

        amountField.setText("");

        methodField.setText("");

        statusField.setText("");

        table.clearSelection();

    }
}