package ui;

import java.util.Scanner;

public class ReportMenu {

    private Scanner scanner;
    private ReportDashboard reportDashboard;

    public ReportMenu() {
        scanner = new Scanner(System.in);
        reportDashboard = new ReportDashboard();
    }

    public void start() {

        while (true) {

            System.out.println("\n========== REPORTS ==========");
            System.out.println("1. Total Revenue");
            System.out.println("2. Today's Revenue");
            System.out.println("3. Monthly Revenue");
            System.out.println("4. Paid Payments");
            System.out.println("5. Pending Payments");
            System.out.println("6. Available Rooms");
            System.out.println("7. Active Reservations");
            System.out.println("8. Dashboard Summary");
            System.out.println("9. Back");

            System.out.print("Enter Choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    reportDashboard.totalRevenue();
                    break;

                case 2:
                    reportDashboard.todayRevenue();
                    break;

                case 3:
                    reportDashboard.monthlyRevenue();
                    break;

                case 4:
                    reportDashboard.paidPayments();
                    break;

                case 5:
                    reportDashboard.pendingPayments();
                    break;

                case 6:
                    reportDashboard.availableRooms();
                    break;

                case 7:
                    reportDashboard.activeReservations();
                    break;

                case 8:
                    reportDashboard.dashboardSummary();
                    break;

                case 9:
                    return;

                default:
                    System.out.println("Invalid Choice");
            }
        }
    }
}