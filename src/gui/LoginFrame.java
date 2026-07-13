package gui;

import service.AuthService;
import javax.swing.ImageIcon;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    private final AuthService authService;

    public LoginFrame() {

        authService = new AuthService();

        setTitle("Hotel Reservation System");
        setSize(500,420);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        ImageIcon logo = new ImageIcon(
                getClass().getResource("/resources/icons/hotel.png"));

        JLabel logoLabel = new JLabel(logo);
        logoLabel.setBounds(210, 10, 64, 64);
        panel.add(logoLabel);

        JLabel title = new JLabel("HOTEL RESERVATION SYSTEM");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBounds(40, 80, 400, 30);
        panel.add(title);

        JLabel userLabel = new JLabel("Username");
        userLabel.setBounds(70,130,100,25);
        panel.add(userLabel);

        usernameField = new JTextField();
        usernameField.setBounds(180,130,220,30);
        panel.add(usernameField);

        JLabel passLabel = new JLabel("Password");
        passLabel.setBounds(70,185,100,25);
        panel.add(passLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(180,185,220,30);
        panel.add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setBounds(180,250,120,40);
        panel.add(loginButton);

        loginButton.addActionListener(e -> login());

        add(panel);

        setVisible(true);
    }

    private void login() {

        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (authService.login(username, password)) {

            JOptionPane.showMessageDialog(this,
                    "Login Successful!");

            dispose();

            new DashboardFrame();

        } else {

            JOptionPane.showMessageDialog(this,
                    "Invalid Username or Password");

        }
    }
}