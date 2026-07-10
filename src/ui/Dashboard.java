
package ui;

import java.util.Scanner;

public class Dashboard {

    private Scanner scanner;

    public Dashboard() {
        scanner = new Scanner(System.in);
    }

    public void start() {

        while (true) {

            System.out.println("\n=========================================");
            System.out.println("     HOTEL RESERVATION SYSTEM");
            System.out.println("=========================================");
            System.out.println("1. Customer Management");
            System.out.println("2. Room Management");
            System.out.println("3. Reservation Management");
            System.out.println("4. Payment Management");
            System.out.println("5. Reports");
            System.out.println("6. Logout");
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
                    System.out.println("Logging out...");
                    return;

                default:
                    System.out.println("Invalid Choice!");
            }

        }

    }

}
