package gui;

import service.CustomerService;
import service.RoomService;
import service.ReservationService;
import service.PaymentService;

import javax.swing.*;
import java.awt.*;

public class ReportFrame extends JFrame {

    private final DashboardFrame dashboardFrame;

    private final CustomerService customerService;
    private final RoomService roomService;
    private final ReservationService reservationService;
    private final PaymentService paymentService;

    private JLabel totalCustomersLabel;
    private JLabel totalRoomsLabel;
    private JLabel availableRoomsLabel;
    private JLabel bookedRoomsLabel;
    private JLabel totalReservationsLabel;
    private JLabel activeReservationsLabel;
    private JLabel totalPaymentsLabel;
    private JLabel paidPaymentsLabel;
    private JLabel pendingPaymentsLabel;
    private JLabel totalRevenueLabel;

    private JButton refreshButton;
    private JButton backButton;

    public ReportFrame(DashboardFrame dashboardFrame) {

        this.dashboardFrame = dashboardFrame;

        customerService = new CustomerService();
        roomService = new RoomService();
        reservationService = new ReservationService();
        paymentService = new PaymentService();

        initializeUI();

        loadReports();

        setVisible(true);
    }

    private void initializeUI() {

        setTitle("Reports Dashboard");

        setSize(700,600);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        addWindowListener(new java.awt.event.WindowAdapter() {

            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {

                dispose();

                dashboardFrame.setVisible(true);

            }

        });

        setLayout(new BorderLayout(10,10));

        JLabel title = new JLabel(
                "REPORTS DASHBOARD",
                SwingConstants.CENTER);

        title.setFont(new Font("Arial",Font.BOLD,24));

        add(title,BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridLayout(10,2,10,10));

        totalCustomersLabel = new JLabel();

        totalRoomsLabel = new JLabel();

        availableRoomsLabel = new JLabel();

        bookedRoomsLabel = new JLabel();

        totalReservationsLabel = new JLabel();

        activeReservationsLabel = new JLabel();

        totalPaymentsLabel = new JLabel();

        paidPaymentsLabel = new JLabel();

        pendingPaymentsLabel = new JLabel();

        totalRevenueLabel = new JLabel();

        centerPanel.add(new JLabel("Total Customers"));
        centerPanel.add(totalCustomersLabel);

        centerPanel.add(new JLabel("Total Rooms"));
        centerPanel.add(totalRoomsLabel);

        centerPanel.add(new JLabel("Available Rooms"));
        centerPanel.add(availableRoomsLabel);

        centerPanel.add(new JLabel("Booked Rooms"));
        centerPanel.add(bookedRoomsLabel);

        centerPanel.add(new JLabel("Total Reservations"));
        centerPanel.add(totalReservationsLabel);

        centerPanel.add(new JLabel("Active Reservations"));
        centerPanel.add(activeReservationsLabel);

        centerPanel.add(new JLabel("Total Payments"));
        centerPanel.add(totalPaymentsLabel);

        centerPanel.add(new JLabel("Paid Payments"));
        centerPanel.add(paidPaymentsLabel);

        centerPanel.add(new JLabel("Pending Payments"));
        centerPanel.add(pendingPaymentsLabel);

        centerPanel.add(new JLabel("Total Revenue"));
        centerPanel.add(totalRevenueLabel);

        add(centerPanel,BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();

        refreshButton = new JButton("Refresh");

        backButton = new JButton("Back");

        bottomPanel.add(refreshButton);

        bottomPanel.add(backButton);

        add(bottomPanel,BorderLayout.SOUTH);

        refreshButton.addActionListener(e -> loadReports());

        backButton.addActionListener(e -> {

            dispose();

            dashboardFrame.setVisible(true);

        });

    }    private void loadReports() {

        totalCustomersLabel.setText(
                String.valueOf(customerService.getCustomerCount()));

        totalRoomsLabel.setText(
                String.valueOf(roomService.getRoomCount()));

        availableRoomsLabel.setText(
                String.valueOf(roomService.getAvailableRoomCount()));

        bookedRoomsLabel.setText(
                String.valueOf(roomService.getBookedRoomCount()));

        totalReservationsLabel.setText(
                String.valueOf(reservationService.getReservationCount()));

        activeReservationsLabel.setText(
                String.valueOf(reservationService.getActiveReservationCount()));

        totalPaymentsLabel.setText(
                String.valueOf(paymentService.getPaymentCount()));

        paidPaymentsLabel.setText(
                String.valueOf(paymentService.getPaidPaymentCount()));

        pendingPaymentsLabel.setText(
                String.valueOf(paymentService.getPendingPaymentCount()));

        totalRevenueLabel.setText(
                "₹ " + String.format("%.2f",
                        paymentService.getTotalRevenue()));

    }

}