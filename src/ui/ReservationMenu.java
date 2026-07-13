package ui;

import model.Reservation;
import validation.Validator;
import service.ReservationService;

import java.sql.Date;
import java.util.Scanner;

public class ReservationMenu {

    private Scanner scanner;
    private ReservationService reservationService;

    public ReservationMenu() {
        scanner = new Scanner(System.in);
        reservationService = new ReservationService();
    }

    public void start() {

        while (true) {

            System.out.println("\n========== RESERVATION MANAGEMENT ==========");
            System.out.println("1. Book Room");
            System.out.println("2. View All Reservations");
            System.out.println("3. Search Reservation");
            System.out.println("4. Update Reservation");
            System.out.println("5. Cancel Reservation");
            System.out.println("6. Check In");
            System.out.println("7. Check Out");
            System.out.println("8. Back");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    bookRoom();
                    break;

                case 2:
                    viewReservations();
                    break;

                case 3:
                    searchReservation();
                    break;

                case 4:
                    updateReservation();
                    break;

                case 5:
                    cancelReservation();
                    break;

                case 6:
                    checkIn();
                    break;

                case 7:
                    checkOut();
                    break;

                case 8:
                    return;

                default:
                    System.out.println("Invalid Choice!");

            }

        }

    }

    // ==========================
    // Book Room
    // ==========================

    private void bookRoom() {

        Reservation reservation = new Reservation();

        System.out.print("Customer ID: ");
        int customerId = scanner.nextInt();

        if (customerId <= 0) {
            System.out.println("Invalid Customer ID.");
            return;
        }

        reservation.setCustomerId(customerId);

        System.out.print("Room ID: ");
        int roomId = scanner.nextInt();

        if (roomId <= 0) {
            System.out.println("Invalid Room ID.");
            return;
        }

        reservation.setRoomId(roomId);

        scanner.nextLine();

        try {

            System.out.print("Check-In Date (yyyy-mm-dd): ");
            Date checkIn = Date.valueOf(scanner.nextLine());

            System.out.print("Check-Out Date (yyyy-mm-dd): ");
            Date checkOut = Date.valueOf(scanner.nextLine());

            if (!checkOut.after(checkIn)) {
                System.out.println("Check-Out must be after Check-In.");
                return;
            }

            reservation.setCheckIn(checkIn);
            reservation.setCheckOut(checkOut);

        } catch (IllegalArgumentException e) {

            System.out.println("Invalid Date Format.");
            return;
        }

        if (reservationService.bookRoom(reservation)) {
            System.out.println("Room Booked Successfully.");
        } else {
            System.out.println("Booking Failed.");
        }
    }
      // ==========================
    // View All Reservations
    // ==========================

    private void viewReservations() {

        java.util.List<Reservation> reservations =
                reservationService.getAllReservations();

        if (reservations.isEmpty()) {

            System.out.println("No Reservations Found.");
            return;

        }

        System.out.println("\n========== RESERVATION LIST ==========");

        for (Reservation reservation : reservations) {

            System.out.println("--------------------------------------");
            System.out.println("Reservation ID : " + reservation.getReservationId());
            System.out.println("Customer ID    : " + reservation.getCustomerId());
            System.out.println("Room ID        : " + reservation.getRoomId());
            System.out.println("Check-In       : " + reservation.getCheckIn());
            System.out.println("Check-Out      : " + reservation.getCheckOut());

        }

    }

    // ==========================
    // Search Reservation
    // ==========================

    private void searchReservation() {

        System.out.print("Enter Reservation ID: ");

        int reservationId = scanner.nextInt();

        if (reservationId <= 0) {
            System.out.println("Invalid Reservation ID.");
            return;
        }
        scanner.nextLine();

        Reservation reservation =
                reservationService.getReservationById(reservationId);

        if (reservation == null) {

            System.out.println("Reservation Not Found.");
            return;

        }

        System.out.println("\nReservation Details");
        System.out.println("------------------------------");
        System.out.println("Reservation ID : " + reservation.getReservationId());
        System.out.println("Customer ID    : " + reservation.getCustomerId());
        System.out.println("Room ID        : " + reservation.getRoomId());
        System.out.println("Check-In       : " + reservation.getCheckIn());
        System.out.println("Check-Out      : " + reservation.getCheckOut());

    }    // ==========================
    // Update Reservation
    // ==========================

    private void updateReservation() {

        System.out.print("Enter Reservation ID: ");
        int reservationId = scanner.nextInt();

        Reservation reservation =
                reservationService.getReservationById(reservationId);

        if (reservation == null) {

            System.out.println("Reservation Not Found.");
            scanner.nextLine();
            return;

        }

        reservation.setReservationId(reservationId);

        System.out.print("New Customer ID: ");
        reservation.setCustomerId(scanner.nextInt());

        System.out.print("New Room ID: ");
        reservation.setRoomId(scanner.nextInt());
        scanner.nextLine();

        try {

            System.out.print("New Check-In Date (yyyy-mm-dd): ");
            Date checkIn = Date.valueOf(scanner.nextLine());

            System.out.print("New Check-Out Date (yyyy-mm-dd): ");
            Date checkOut = Date.valueOf(scanner.nextLine());

            if (!checkOut.after(checkIn)) {
                System.out.println("Check-Out must be after Check-In.");
                return;
            }

            reservation.setCheckIn(checkIn);
            reservation.setCheckOut(checkOut);

        } catch (IllegalArgumentException e) {

            System.out.println("Invalid Date Format.");
            return;
        }

        if (reservationService.updateReservation(reservation)) {

            System.out.println("Reservation Updated Successfully.");

        } else {

            System.out.println("Failed to Update Reservation.");

        }

    }

    // ==========================
    // Cancel Reservation
    // ==========================

    private void cancelReservation() {

        System.out.print("Enter Reservation ID: ");

        int reservationId = scanner.nextInt();

        if (reservationId <= 0) {
            System.out.println("Invalid Reservation ID.");
            return;
        }
        scanner.nextLine();

        if (reservationService.cancelReservation(reservationId)) {

            System.out.println("Reservation Cancelled Successfully.");

        } else {

            System.out.println("Reservation Not Found.");

        }

    }    // ==========================
    // Check In
    // ==========================

    private void checkIn() {

        System.out.print("Enter Reservation ID: ");

        int reservationId = scanner.nextInt();

        if (reservationId <= 0) {
            System.out.println("Invalid Reservation ID.");
            return;
        }
        scanner.nextLine();

        if (reservationService.checkIn(reservationId)) {

            System.out.println("Check-In Successful.");

        } else {

            System.out.println("Check-In Failed.");

        }

    }

    // ==========================
    // Check Out
    // ==========================

    private void checkOut() {

        System.out.print("Enter Reservation ID: ");
        int reservationId = scanner.nextInt();
        scanner.nextLine();

        if (reservationId <= 0) {
            System.out.println("Invalid Reservation ID.");
            return;
        }

        double bill = reservationService.calculateBill(reservationId);

        if (reservationService.checkOut(reservationId)) {

            System.out.println("Check-Out Successful.");
            System.out.println("Total Bill : ₹" + bill);

        } else {

            System.out.println("Check-Out Failed.");

        }
    }

}
