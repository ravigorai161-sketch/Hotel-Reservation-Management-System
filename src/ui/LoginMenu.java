package ui;

import service.AuthService;

import java.util.Scanner;

public class LoginMenu {

    private final Scanner scanner;
    private final AuthService authService;

    public LoginMenu() {
        scanner = new Scanner(System.in);
        authService = new AuthService();
    }

    public void start() {

        while (true) {

            System.out.println("\n======================================");
            System.out.println("     HOTEL RESERVATION SYSTEM");
            System.out.println("            ADMIN LOGIN");
            System.out.println("======================================");

            System.out.print("Username: ");
            String username = scanner.nextLine();

            System.out.print("Password: ");
            String password = scanner.nextLine();

            if (authService.login(username, password)) {

                System.out.println("\nLogin Successful!");
                Dashboard dashboard = new Dashboard();
                dashboard.start();
                break;

            } else {

                System.out.println("\nInvalid username or password.");
                System.out.println("Please try again.\n");

            }
        }
    }
}
