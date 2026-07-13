package ui;

import model.Payment;
import model.Reservation;
import java.util.Scanner;
import model.Room;

import service.PaymentService;
import service.ReservationService;
import service.RoomService;
import service.DashboardService;

import java.util.List;

public class ReportDashboard {
    private PaymentService paymentService = new PaymentService();
    private RoomService roomService = new RoomService();
    private ReservationService reservationService = new ReservationService();
    private DashboardService dashboardService = new DashboardService();

    private Scanner scanner = new Scanner(System.in);

    public void totalRevenue() {

        double revenue = paymentService.getTotalRevenue();

        System.out.println("\n========== TOTAL REVENUE ==========");
        System.out.println("Total Revenue : ₹" + revenue);
    }
    public void todayRevenue() {

        double revenue = paymentService.getTodayRevenue();

        System.out.println("\n========== TODAY'S REVENUE ==========");
        System.out.println("Today's Revenue : ₹" + revenue);
    }
    public void monthlyRevenue() {

        System.out.print("Enter Month (1-12): ");
        int month = scanner.nextInt();

        System.out.print("Enter Year: ");
        int year = scanner.nextInt();
        scanner.nextLine();

        double revenue = paymentService.getMonthlyRevenue(month, year);

        System.out.println("\n========== MONTHLY REVENUE ==========");
        System.out.println("Revenue : ₹" + revenue);
    }
    public void paidPayments() {

        List<Payment> payments = paymentService.getPaidPayments();

        if (payments.isEmpty()) {
            System.out.println("No Paid Payments Found.");
            return;
        }

        System.out.println("\n========== PAID PAYMENTS ==========");

        for (Payment payment : payments) {

            System.out.println("----------------------------------");
            System.out.println("Payment ID     : " + payment.getPaymentId());
            System.out.println("Reservation ID : " + payment.getReservationId());
            System.out.println("Amount         : ₹" + payment.getTotalAmount());
            System.out.println("Method         : " + payment.getPaymentMethod());
            System.out.println("Status         : " + payment.getPaymentStatus());
        }
    }
    public void pendingPayments() {

        List<Payment> payments = paymentService.getPendingPayments();

        if (payments.isEmpty()) {
            System.out.println("No Pending Payments Found.");
            return;
        }

        System.out.println("\n========== PENDING PAYMENTS ==========");

        for (Payment payment : payments) {

            System.out.println("----------------------------------");
            System.out.println("Payment ID     : " + payment.getPaymentId());
            System.out.println("Reservation ID : " + payment.getReservationId());
            System.out.println("Amount         : ₹" + payment.getTotalAmount());
            System.out.println("Method         : " + payment.getPaymentMethod());
            System.out.println("Status         : " + payment.getPaymentStatus());
        }
    }
    public void availableRooms() {

        List<Room> rooms = roomService.getAvailableRooms();

        if (rooms.isEmpty()) {
            System.out.println("No Available Rooms.");
            return;
        }

        System.out.println("\n========== AVAILABLE ROOMS ==========");

        for (Room room : rooms) {

            System.out.println("----------------------------------");
            System.out.println("Room ID     : " + room.getRoomId());
            System.out.println("Room Number : " + room.getRoomNumber());
            System.out.println("Room Type   : " + room.getRoomType());
            System.out.println("Price       : ₹" + room.getPrice());
            System.out.println("Status      : " + room.getStatus());
        }
    }
    public void activeReservations() {

        List<Reservation> reservations =
                reservationService.getActiveReservations();

        if (reservations.isEmpty()) {
            System.out.println("No Active Reservations.");
            return;
        }

        System.out.println("\n========== ACTIVE RESERVATIONS ==========");

        for (Reservation reservation : reservations) {

            System.out.println("----------------------------------");
            System.out.println("Reservation ID : " + reservation.getReservationId());
            System.out.println("Customer ID    : " + reservation.getCustomerId());
            System.out.println("Room ID        : " + reservation.getRoomId());
            System.out.println("Check-In       : " + reservation.getCheckIn());
            System.out.println("Check-Out      : " + reservation.getCheckOut());
        }
    }
    public void dashboardSummary() {

        System.out.println("\n========== DASHBOARD SUMMARY ==========");

        System.out.println("Total Rooms          : " +
                dashboardService.getTotalRooms());

        System.out.println("Available Rooms      : " +
                dashboardService.getAvailableRooms());

        System.out.println("Booked Rooms         : " +
                dashboardService.getBookedRooms());

        System.out.println("Occupied Rooms       : " +
                dashboardService.getOccupiedRooms());

        System.out.println("Total Reservations   : " +
                dashboardService.getTotalReservations());

        System.out.println("Active Reservations  : " +
                dashboardService.getActiveReservations());

        System.out.println("Total Customers      : " +
                dashboardService.getTotalCustomers());

        System.out.println("Total Revenue        : ₹" +
                paymentService.getTotalRevenue());

        System.out.println("Today's Revenue      : ₹" +
                paymentService.getTodayRevenue());

        System.out.println("=======================================");
    }
}
