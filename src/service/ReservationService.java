package service;

import util.LoggerUtil;
import java.util.logging.Logger;
import dao.PaymentDAO;
import invoice.InvoiceGenerator;
import model.Customer;
import model.Payment;
import model.Room;
import dao.CustomerDAO;
import dao.ReservationDAO;
import dao.RoomDAO;
import model.Reservation;


import java.sql.Date;
import java.util.List;

public class ReservationService {

    private static final Logger logger = LoggerUtil.getLogger();

    private ReservationDAO reservationDAO;
    private RoomService roomService;
    private CustomerDAO customerDAO;
    private RoomDAO roomDAO;
    private PaymentDAO paymentDAO;

    public ReservationService() {
        reservationDAO = new ReservationDAO();
        customerDAO = new CustomerDAO();
        roomDAO = new RoomDAO();
        roomService = new RoomService();

        paymentDAO = new PaymentDAO();
    }

    // ==========================
    // Book Room
    // ==========================
    public boolean bookRoom(Reservation reservation) {

        if (reservation == null) {
            System.out.println("Reservation cannot be null.");
            return false;
        }

        if (!customerDAO.customerExists(reservation.getCustomerId())) {
            System.out.println("Customer does not exist.");
            return false;
        }

        Room room = roomDAO.getRoomById(reservation.getRoomId());

        if (room == null) {
            System.out.println("Room not found.");
            return false;
        }

        if (reservation.getCheckIn() == null ||
                reservation.getCheckOut() == null) {

            System.out.println("Invalid dates.");
            return false;
        }

        if (!reservation.getCheckOut().after(reservation.getCheckIn())) {

            System.out.println("Check-out must be after check-in.");
            return false;
        }

        if (!room.getStatus().equalsIgnoreCase("Available")) {

            System.out.println("Room is not available.");
            return false;
        }

        boolean booked = reservationDAO.bookRoom(reservation);

        if (booked) {

            roomDAO.updateRoomStatus(reservation.getRoomId(), "Booked");

            System.out.println("Room status updated to Booked.");
        }

        if (booked) {
            logger.info("Room booked successfully. Reservation Customer ID: "
                    + reservation.getCustomerId()
                    + ", Room ID: "
                    + reservation.getRoomId());
        } else {
            logger.warning("Room booking failed. Customer ID: "
                    + reservation.getCustomerId());
        }

        return booked;
    }

    // ==========================
    // Update Reservation
    // ==========================
    public boolean updateReservation(Reservation reservation) {

        if (reservation == null) {
            return false;
        }

        if (!reservationDAO.reservationExists(
                reservation.getReservationId())) {

            System.out.println("Reservation not found.");
            return false;
        }

        boolean updated = reservationDAO.updateReservation(reservation);

        if (updated) {
            logger.info("Reservation updated. Reservation ID: "
                    + reservation.getReservationId());
        } else {
            logger.warning("Failed to update reservation. Reservation ID: "
                    + reservation.getReservationId());
        }

        return updated;
    }

    // ==========================
    // Cancel Reservation
    // ==========================
    public boolean cancelReservation(int reservationId) {

        if (!reservationDAO.reservationExists(reservationId)) {

            System.out.println("Reservation not found.");
            return false;
        }

        boolean cancelled = reservationDAO.cancelReservation(reservationId);

        if (cancelled) {
            logger.info("Reservation cancelled. Reservation ID: " + reservationId);
        } else {
            logger.warning("Failed to cancel reservation. Reservation ID: " + reservationId);
        }

        return cancelled;
    }

    // ==========================
    // Get Reservation By ID
    // ==========================
    public Reservation getReservationById(int reservationId) {

        return reservationDAO.getReservationById(reservationId);
    }

    // ==========================
    // Get All Reservations
    // ==========================
    public List<Reservation> getAllReservations() {

        return reservationDAO.getAllReservations();
    }

    // ==========================
    // Get Reservations By Customer
    // ==========================
    public List<Reservation> getReservationsByCustomer(int customerId) {

        return reservationDAO.getReservationsByCustomer(customerId);
    }

    // ==========================
    // Get Reservations By Room
    // ==========================
    public List<Reservation> getReservationsByRoom(int roomId) {

        return reservationDAO.getReservationsByRoom(roomId);
    }

    // ==========================
    // Active Reservations
    // ==========================
    public List<Reservation> getActiveReservations() {

        return reservationDAO.getActiveReservations();
    }

    // ==========================
    // Check Room Availability
    // ==========================
    public boolean isRoomAvailable(int roomId,
                                   Date checkIn,
                                   Date checkOut) {

        return reservationDAO.isRoomAvailable(
                roomId,
                checkIn.toLocalDate(),
                checkOut.toLocalDate()
        );
    }

    // ==========================
    // Check In
    // ==========================
    public boolean checkIn(int reservationId) {

        if (!reservationDAO.reservationExists(reservationId)) {

            System.out.println("Reservation not found.");
            return false;
        }

        boolean checkedIn = reservationDAO.checkIn(reservationId);

        if (checkedIn) {

            Reservation reservation = reservationDAO.getReservationById(reservationId);

            roomDAO.updateRoomStatus(reservation.getRoomId(), "Occupied");

            System.out.println("Room status updated to Occupied.");
        }

        if (checkedIn) {
            logger.info("Guest checked in. Reservation ID: " + reservationId);
        } else {
            logger.warning("Check-in failed. Reservation ID: " + reservationId);
        }
        return checkedIn;
    }

    // ==========================
    // Check Out
    // ==========================
    public boolean checkOut(int reservationId) {

        if (!reservationDAO.reservationExists(reservationId)) {

            System.out.println("Reservation not found.");
            logger.warning("Check-out failed. Reservation ID: " + reservationId + " not found.");
            return false;
        }

        double bill = reservationDAO.calculateBill(reservationId);

        // Perform checkout
        boolean checkedOut = reservationDAO.checkOut(reservationId, bill);

        if (checkedOut) {
            logger.info("Guest checked out successfully. Reservation ID: " + reservationId);
        } else {
            logger.warning("Check-out failed. Reservation ID: " + reservationId);
        }

        return checkedOut;
    }

    // ==========================
    // Calculate Bill
    // ==========================
    public double calculateBill(int reservationId) {

        return reservationDAO.calculateBill(reservationId);
    }

    // ==========================
    // Count Reservations
    // ==========================
    public int getReservationCount() {

        return reservationDAO.getReservationCount();
    }

    // ==========================
    // Count Active Reservations
    // ==========================
    public int getActiveReservationCount() {

        return reservationDAO.getActiveReservationCount();
    }
}
