package gui;

import javax.swing.*;
import java.awt.*;import service.CustomerService;
import service.RoomService;
import service.ReservationService;
import service.PaymentService;

public class DashboardFrame extends JFrame {

    public DashboardFrame() {

        setTitle("Hotel Reservation System");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        Color primaryColor = new Color(41, 128, 185);
        Color backgroundColor = new Color(245, 247, 250);
        CustomerService customerService = new CustomerService();
        RoomService roomService = new RoomService();
        ReservationService reservationService = new ReservationService();
        PaymentService paymentService = new PaymentService();

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(backgroundColor);

        JLabel title = new JLabel("HOTEL RESERVATION SYSTEM");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 30));
        title.setForeground(primaryColor);
        title.setBorder(BorderFactory.createEmptyBorder(30,0,10,0));

        JLabel subtitle = new JLabel("Management Dashboard");
        subtitle.setHorizontalAlignment(SwingConstants.CENTER);
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN,16));
        subtitle.setForeground(Color.DARK_GRAY);

        JPanel titlePanel = new JPanel(new GridLayout(2,1));
        titlePanel.setBackground(backgroundColor);
        titlePanel.add(title);
        titlePanel.add(subtitle);

        mainPanel.add(titlePanel, BorderLayout.NORTH);
        JPanel statsPanel = new JPanel(new GridLayout(2,2,20,20));
        statsPanel.setBackground(backgroundColor);
        statsPanel.setBorder(
                BorderFactory.createEmptyBorder(20,60,20,60));

        JLabel customerCard = new JLabel(
                "👤 Customers : " + customerService.getCustomerCount(),
                SwingConstants.CENTER);

        JLabel roomCard = new JLabel(
                "🛏 Rooms : " + roomService.getRoomCount(),
                SwingConstants.CENTER);

        JLabel reservationCard = new JLabel(
                "📅 Reservations : " + reservationService.getReservationCount(),
                SwingConstants.CENTER);

        JLabel revenueCard = new JLabel(
                "💰 Revenue : ₹" + paymentService.getTotalRevenue(),
                SwingConstants.CENTER);

        JLabel[] cards = {
                customerCard,
                roomCard,
                reservationCard,
                revenueCard
        };

        for (JLabel card : cards) {

            card.setOpaque(true);

            card.setBackground(Color.WHITE);

            card.setFont(new Font("Segoe UI", Font.BOLD, 18));

            card.setBorder(
                    BorderFactory.createLineBorder(
                            new Color(41,128,185),2));

        }

        statsPanel.add(customerCard);
        statsPanel.add(roomCard);
        statsPanel.add(reservationCard);
        statsPanel.add(revenueCard);
        JPanel buttonPanel = new JPanel(new GridLayout(3,2,25,25));
        buttonPanel.setBackground(backgroundColor);
        buttonPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        40,
                        80,
                        60,
                        80));

        JButton customerBtn = new JButton("👤 Customers");
        JButton roomBtn = new JButton("🛏 Rooms");
        JButton reservationBtn = new JButton("📅 Reservations");
        JButton paymentBtn = new JButton("💳 Payments");
        JButton reportBtn = new JButton("📊 Reports");
        JButton logoutBtn = new JButton("🚪 Logout");

        JButton[] buttons = {

                customerBtn,
                roomBtn,
                reservationBtn,
                paymentBtn,
                reportBtn,
                logoutBtn

        };

        for (JButton button : buttons) {

            button.setBackground(primaryColor);
            button.setForeground(Color.WHITE);

            button.setFont(
                    new Font("Segoe UI",
                            Font.BOLD,
                            17));

            button.setFocusPainted(false);

            button.setCursor(
                    new Cursor(Cursor.HAND_CURSOR));

            button.setPreferredSize(
                    new Dimension(220,70));

        }

        Font btnFont = new Font("Arial", Font.BOLD, 16);

        customerBtn.setFont(btnFont);
        roomBtn.setFont(btnFont);
        reservationBtn.setFont(btnFont);
        paymentBtn.setFont(btnFont);
        reportBtn.setFont(btnFont);
        logoutBtn.setFont(btnFont);

        buttonPanel.add(customerBtn);
        buttonPanel.add(roomBtn);
        buttonPanel.add(reservationBtn);
        buttonPanel.add(paymentBtn);
        buttonPanel.add(reportBtn);
        buttonPanel.add(logoutBtn);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(backgroundColor);

        centerPanel.add(statsPanel, BorderLayout.NORTH);
        centerPanel.add(buttonPanel, BorderLayout.CENTER);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        customerBtn.addActionListener(e -> {

            setVisible(false);

            new CustomerFrame(this);

        });

        roomBtn.addActionListener(e -> {

            setVisible(false);

            new RoomFrame(this);

        });
        reservationBtn.addActionListener(e -> {

            setVisible(false);

            new ReservationFrame(this);

        });
        paymentBtn.addActionListener(e -> {

            setVisible(false);

            new PaymentFrame(this);

        });
        reportBtn.addActionListener(e -> {

            setVisible(false);

            new ReportFrame(this);

        });


        logoutBtn.addActionListener(e -> {

            dispose();

            new LoginFrame();

        });

        JLabel footer = new JLabel(
                "© 2026 Hotel Reservation System | Developed in Java",
                SwingConstants.CENTER);

        footer.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        footer.setForeground(Color.GRAY);

        footer.setBorder(
                BorderFactory.createEmptyBorder(10,10,15,10));

        mainPanel.add(footer, BorderLayout.SOUTH);

        add(mainPanel);

        setVisible(true);
    }
}