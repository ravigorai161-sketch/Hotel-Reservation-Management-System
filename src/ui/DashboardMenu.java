package ui;

import service.DashboardService;

public class DashboardMenu {

    private DashboardService dashboardService;

    public DashboardMenu() {
        dashboardService = new DashboardService();
    }

    public void showDashboard() {

        System.out.println("\n==========================================");
        System.out.println("           HOTEL DASHBOARD");
        System.out.println("==========================================");

        System.out.println("\nROOMS");
        System.out.println("------------------------------------------");
        System.out.println("Total Rooms        : " + dashboardService.getTotalRooms());
        System.out.println("Available Rooms    : " + dashboardService.getAvailableRooms());
        System.out.println("Booked Rooms       : " + dashboardService.getBookedRooms());
        System.out.println("Occupied Rooms     : " + dashboardService.getOccupiedRooms());

        System.out.println("\nCUSTOMERS");
        System.out.println("------------------------------------------");
        System.out.println("Total Customers    : " + dashboardService.getTotalCustomers());

        System.out.println("\nRESERVATIONS");
        System.out.println("------------------------------------------");
        System.out.println("Total Reservations : " + dashboardService.getTotalReservations());
        System.out.println("Active Reservations: " + dashboardService.getActiveReservations());

        System.out.println("\nPAYMENTS");
        System.out.println("------------------------------------------");
        System.out.println("Total Payments     : " + dashboardService.getTotalPayments());
        System.out.printf("Today's Revenue    : ₹%.2f%n", dashboardService.getTodayRevenue());
        System.out.printf("Total Revenue      : ₹%.2f%n", dashboardService.getTotalRevenue());

        System.out.println("==========================================");
    }
}
