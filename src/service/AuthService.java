package service;

import dao.AdminDAO;

public class AuthService {

    private AdminDAO adminDAO;

    public AuthService() {
        adminDAO = new AdminDAO();
    }

    public boolean login(String username, String password) {

        if (username == null || username.trim().isEmpty()) {
            System.out.println("Username cannot be empty.");
            return false;
        }

        if (password == null || password.trim().isEmpty()) {
            System.out.println("Password cannot be empty.");
            return false;
        }

        return adminDAO.login(username, password);
    }

    public void logout() {
        System.out.println("Logged out successfully.");
    }
}


