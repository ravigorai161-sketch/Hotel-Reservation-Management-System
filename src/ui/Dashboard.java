
package ui;

import java.util.Scanner;

import service.AuthService;
import service.DashboardService;

public class Dashboard {

    private Scanner scanner;
    private DashboardService dashboardService;

    public Dashboard() {
        scanner = new Scanner(System.in);
        dashboardService = new DashboardService();
    }

    public void start() {

        while (true) {

            System.out.println("\n=========================================");
            System.out.println("     HOTEL RESERVATION SYSTEM");
            System.out.println("=========================================");
            System.out.println("Total Rooms         : " + dashboardService.getTotalRooms());
            System.out.println("Available Rooms     : " + dashboardService.getAvailableRooms());
            System.out.println("Booked Rooms        : " + dashboardService.getBookedRooms());
            System.out.println("Occupied Rooms      : " + dashboardService.getOccupiedRooms());

            System.out.println("-----------------------------------------");

            System.out.println("Total Customers     : " + dashboardService.getTotalCustomers());
            System.out.println("Total Reservations  : " + dashboardService.getTotalReservations());
            System.out.println("Active Reservations : " + dashboardService.getActiveReservations());

            System.out.println("-----------------------------------------");

            System.out.println("Total Payments      : " + dashboardService.getTotalPayments());
            System.out.println("Today's Revenue     : ₹" + dashboardService.getTodayRevenue());
            System.out.println("Total Revenue       : ₹" + dashboardService.getTotalRevenue());

            System.out.println("=========================================");
            System.out.println("1. Customer Management");
            System.out.println("2. Room Management");
            System.out.println("3. Reservation Management");
            System.out.println("4. Payment Management");
            System.out.println("5. Reports");
            System.out.println("6. Change Password");
            System.out.println("7. Logout");
            System.out.println("=========================================");

            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    CustomerMenu customerMenu = new CustomerMenu();
                    customerMenu.start();
                    break;

                case 2:
                    RoomMenu roomMenu = new RoomMenu();
                    roomMenu.start();
                    break;

                case 3:
                    ReservationMenu reservationMenu = new ReservationMenu();
                    reservationMenu.start();
                    break;

                case 4:
                    PaymentMenu paymentMenu = new PaymentMenu();
                    paymentMenu.start();
                    break;

                case 5:
                    ReportMenu reportMenu = new ReportMenu();
                    reportMenu.start();
                    break;

                case 6:
                    changePassword();
                    break;

                case 7:
                    System.out.println("Logging out...");
                    return;

                default:
                    System.out.println("Invalid Choice!");
            }

        }

    }
    private void changePassword() {

        System.out.print("Enter Current Password: ");
        String currentPassword = scanner.nextLine();

        System.out.print("Enter New Password: ");
        String newPassword = scanner.nextLine();

        System.out.print("Confirm New Password: ");
        String confirmPassword = scanner.nextLine();

        AuthService authService = new AuthService();

        if (authService.changePassword(currentPassword, newPassword, confirmPassword)) {
            System.out.println("Password Changed Successfully.");
        } else {
            System.out.println("Password Change Failed.");
        }
    }

}
