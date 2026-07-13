package service;

import dao.DashboardDAO;

public class DashboardService {

    private DashboardDAO dashboardDAO;

    public DashboardService() {
        dashboardDAO = new DashboardDAO();
    }

    // Rooms
    public int getTotalRooms() {
        return dashboardDAO.getTotalRooms();
    }

    public int getAvailableRooms() {
        return dashboardDAO.getAvailableRooms();
    }

    public int getBookedRooms() {
        return dashboardDAO.getBookedRooms();
    }

    public int getOccupiedRooms() {
        return dashboardDAO.getOccupiedRooms();
    }

    // Customers
    public int getTotalCustomers() {
        return dashboardDAO.getTotalCustomers();
    }

    // Reservations
    public int getTotalReservations() {
        return dashboardDAO.getTotalReservations();
    }

    public int getActiveReservations() {
        return dashboardDAO.getActiveReservations();
    }

    // Payments
    public int getTotalPayments() {
        return dashboardDAO.getTotalPayments();
    }

    public double getTodayRevenue() {
        return dashboardDAO.getTodayRevenue();
    }

    public double getTotalRevenue() {
        return dashboardDAO.getTotalRevenue();
    }
}
