package ui;

import service.CustomerService;
import service.PaymentService;
import service.ReservationService;
import service.RoomService;

import java.util.Scanner;

public class ReportMenu {

    private Scanner scanner;
    private CustomerService customerService;
    private RoomService roomService;
    private ReservationService reservationService;
    private PaymentService paymentService;

    public ReportMenu() {
        scanner = new Scanner(System.in);

        customerService = new CustomerService();
        roomService = new RoomService();
        reservationService = new ReservationService();
        paymentService = new PaymentService();
    }

    public void start() {

        while (true) {

            System.out.println("\n========== REPORTS ==========");
            System.out.println("1. Total Customers");
            System.out.println("2. Total Rooms");
            System.out.println("3. Available Rooms");
            System.out.println("4. Booked Rooms");
            System.out.println("5. Total Reservations");
            System.out.println("6. Active Reservations");
            System.out.println("7. Total Revenue");
            System.out.println("8. Today's Revenue");
            System.out.println("9. Total Payments");
            System.out.println("10. Paid Payments");
            System.out.println("11. Pending Payments");
            System.out.println("12. Back");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    System.out.println("Total Customers : "
                            + customerService.getCustomerCount());
                    break;

                case 2:
                    System.out.println("Total Rooms : "
                            + roomService.getRoomCount());
                    break;

                case 3:
                    System.out.println("Available Rooms : "
                            + roomService.getAvailableRoomCount());
                    break;

                case 4:
                    System.out.println("Booked Rooms : "
                            + roomService.getBookedRoomCount());
                    break;

                case 5:
                    System.out.println("Total Reservations : "
                            + reservationService.getReservationCount());
                    break;

                case 6:
                    System.out.println("Active Reservations : "
                            + reservationService.getActiveReservationCount());
                    break;

                case 7:
                    System.out.println("Total Revenue : ₹"
                            + paymentService.getTotalRevenue());
                    break;

                case 8:
                    System.out.println("Today's Revenue : ₹"
                            + paymentService.getTodayRevenue());
                    break;

                case 9:
                    System.out.println("Total Payments : "
                            + paymentService.getPaymentCount());
                    break;

                case 10:
                    System.out.println("Paid Payments : "
                            + paymentService.getPaidPaymentCount());
                    break;

                case 11:
                    System.out.println("Pending Payments : "
                            + paymentService.getPendingPaymentCount());
                    break;

                case 12:
                    return;

                default:
                    System.out.println("Invalid Choice!");

            }

        }

    }

}