package service;

import dao.AdminDAO;
import util.LoggerUtil;
import java.util.logging.Logger;

public class AuthService {
    private static final Logger logger = LoggerUtil.getLogger();

    private AdminDAO adminDAO;

    public AuthService() {
        adminDAO = new AdminDAO();
    }

    public boolean login(String username, String password) {

        if (username == null || username.trim().isEmpty()) {
            System.out.println("Username cannot be empty.");
            logger.warning("Login failed: Username is empty.");
            return false;
        }

        if (password == null || password.trim().isEmpty()) {
            System.out.println("Password cannot be empty.");
            logger.warning("Login failed: Password is empty.");
            return false;
        }

        boolean loggedIn = adminDAO.login(username, password);

        if (loggedIn) {
            logger.info("Admin logged in successfully. Username: " + username);
        } else {
            logger.warning("Failed login attempt. Username: " + username);
        }

        return loggedIn;
    }

    public void logout() {
        System.out.println("Logged out successfully.");
    }public boolean changePassword(String currentPassword,
                                   String newPassword,
                                   String confirmPassword) {

        if (currentPassword == null || currentPassword.isBlank()) {
            System.out.println("Current password is required.");
            return false;
        }

        if (newPassword == null || newPassword.isBlank()) {
            System.out.println("New password is required.");
            return false;
        }

        if (!newPassword.equals(confirmPassword)) {
            System.out.println("Passwords do not match.");
            return false;
        }

        return adminDAO.changePassword(currentPassword, newPassword);
    }
}


