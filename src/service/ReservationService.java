package service;

import dao.CustomerDAO;
import dao.ReservationDAO;
import dao.RoomDAO;
import model.Reservation;
import model.Room;

import java.sql.Date;
import java.util.List;

public class ReservationService {

    private ReservationDAO reservationDAO;
    private CustomerDAO customerDAO;
    private RoomDAO roomDAO;

    public ReservationService() {
        reservationDAO = new ReservationDAO();
        customerDAO = new CustomerDAO();
        roomDAO = new RoomDAO();
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

        return reservationDAO.bookRoom(reservation);
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

        return reservationDAO.updateReservation(reservation);
    }

    // ==========================
    // Cancel Reservation
    // ==========================
    public boolean cancelReservation(int reservationId) {

        if (!reservationDAO.reservationExists(reservationId)) {

            System.out.println("Reservation not found.");
            return false;
        }

        return reservationDAO.cancelReservation(reservationId);
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

        return reservationDAO.checkIn(reservationId);
    }

    // ==========================
    // Check Out
    // ==========================
    public boolean checkOut(int reservationId) {

        if (!reservationDAO.reservationExists(reservationId)) {

            System.out.println("Reservation not found.");
            return false;
        }

        double bill = reservationDAO.calculateBill(reservationId);

        return reservationDAO.checkOut(
                reservationId,
                bill
        );
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
