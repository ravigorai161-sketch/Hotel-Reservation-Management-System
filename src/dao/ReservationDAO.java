package dao;

import model.Reservation;
import util.DBConnection;
import dao.CustomerDAO;
import dao.RoomDAO;
import invoice.InvoiceGenerator;
import model.Customer;
import model.Payment;
import model.Room;

import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAO {

    // ==========================
    // Book a Room
    // ==========================
    public boolean bookRoom(Reservation reservation) {

      //  if (!isRoomAvailable(
       //         reservation.getRoomId(),
       //         reservation.getCheckIn().toLocalDate(),
      //          reservation.getCheckOut().toLocalDate())) {

        //    System.out.println("Room is not available for the selected dates.");
        //    return false;
     //   }

        String sql = "INSERT INTO reservations(customer_id, room_id, check_in, check_out) VALUES(?,?,?,?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, reservation.getCustomerId());
            ps.setInt(2, reservation.getRoomId());
            ps.setDate(3, reservation.getCheckIn());
            ps.setDate(4, reservation.getCheckOut());

            int rows = ps.executeUpdate();

            if (rows > 0) {

                updateRoomStatus(reservation.getRoomId(), "Booked");

                System.out.println("Reservation Successful.");

                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // ==========================
    // Check Room Availability
    // ==========================
    public boolean isRoomAvailable(int roomId,
                                   LocalDate checkIn,
                                   LocalDate checkOut) {

        String sql =
                "SELECT COUNT(*) " +
                        "FROM reservations " +
                        "WHERE room_id=? " +
                        "AND (check_in < ? AND check_out > ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, roomId);
            ps.setDate(2, Date.valueOf(checkOut));
            ps.setDate(3, Date.valueOf(checkIn));

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return rs.getInt(1) == 0;

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // ==========================
    // Update Room Status
    // ==========================
    private void updateRoomStatus(int roomId, String status) {

        String sql =
                "UPDATE rooms SET status=? WHERE room_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setInt(2, roomId);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // ==========================
    // Get Reservation By ID
    // ==========================
    public Reservation getReservationById(int reservationId) {

        String sql = "SELECT * FROM reservations WHERE reservation_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, reservationId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Reservation reservation = new Reservation();

                reservation.setReservationId(rs.getInt("reservation_id"));
                reservation.setCustomerId(rs.getInt("customer_id"));
                reservation.setRoomId(rs.getInt("room_id"));
                reservation.setCheckIn(rs.getDate("check_in"));
                reservation.setCheckOut(rs.getDate("check_out"));

                return reservation;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // ==========================
    // Get All Reservations
    // ==========================
    public List<Reservation> getAllReservations() {

        List<Reservation> reservations = new ArrayList<>();

        String sql = "SELECT * FROM reservations ORDER BY reservation_id";

        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {

                Reservation reservation = new Reservation();

                reservation.setReservationId(rs.getInt("reservation_id"));
                reservation.setCustomerId(rs.getInt("customer_id"));
                reservation.setRoomId(rs.getInt("room_id"));
                reservation.setCheckIn(rs.getDate("check_in"));
                reservation.setCheckOut(rs.getDate("check_out"));

                reservations.add(reservation);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reservations;
    }

    // ==========================
    // Search Reservations By Customer ID
    // ==========================
    public List<Reservation> getReservationsByCustomer(int customerId) {

        List<Reservation> reservations = new ArrayList<>();

        String sql = "SELECT * FROM reservations WHERE customer_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, customerId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Reservation reservation = new Reservation();

                reservation.setReservationId(rs.getInt("reservation_id"));
                reservation.setCustomerId(rs.getInt("customer_id"));
                reservation.setRoomId(rs.getInt("room_id"));
                reservation.setCheckIn(rs.getDate("check_in"));
                reservation.setCheckOut(rs.getDate("check_out"));

                reservations.add(reservation);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reservations;
    }

    // ==========================
    // Search Reservations By Room ID
    // ==========================
    public List<Reservation> getReservationsByRoom(int roomId) {

        List<Reservation> reservations = new ArrayList<>();

        String sql = "SELECT * FROM reservations WHERE room_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, roomId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Reservation reservation = new Reservation();

                reservation.setReservationId(rs.getInt("reservation_id"));
                reservation.setCustomerId(rs.getInt("customer_id"));
                reservation.setRoomId(rs.getInt("room_id"));
                reservation.setCheckIn(rs.getDate("check_in"));
                reservation.setCheckOut(rs.getDate("check_out"));

                reservations.add(reservation);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reservations;
    }

    // ==========================
    // Get Active Reservations
    // ==========================
    public List<Reservation> getActiveReservations() {

        List<Reservation> reservations = new ArrayList<>();

        String sql = "SELECT * FROM reservations WHERE check_out >= CURDATE() ORDER BY check_in";

        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {

                Reservation reservation = new Reservation();

                reservation.setReservationId(rs.getInt("reservation_id"));
                reservation.setCustomerId(rs.getInt("customer_id"));
                reservation.setRoomId(rs.getInt("room_id"));
                reservation.setCheckIn(rs.getDate("check_in"));
                reservation.setCheckOut(rs.getDate("check_out"));

                reservations.add(reservation);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reservations;
    }
    // ==========================
    // Update Reservation
    // ==========================
    public boolean updateReservation(Reservation reservation) {

        if (!reservationExists(reservation.getReservationId())) {
            System.out.println("Reservation not found.");
            return false;
        }

        String sql = "UPDATE reservations SET customer_id=?, room_id=?, check_in=?, check_out=? WHERE reservation_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, reservation.getCustomerId());
            ps.setInt(2, reservation.getRoomId());
            ps.setDate(3, reservation.getCheckIn());
            ps.setDate(4, reservation.getCheckOut());
            ps.setInt(5, reservation.getReservationId());

            int rows = ps.executeUpdate();

            if (rows > 0) {

                updateRoomStatus(reservation.getRoomId(), "Booked");

                System.out.println("Reservation Updated Successfully.");

                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // ==========================
    // Cancel Reservation
    // ==========================
    public boolean cancelReservation(int reservationId) {

        Reservation reservation = getReservationById(reservationId);

        if (reservation == null) {

            System.out.println("Reservation not found.");
            return false;

        }

        String sql = "DELETE FROM reservations WHERE reservation_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, reservationId);

            int rows = ps.executeUpdate();

            if (rows > 0) {

                updateRoomStatus(reservation.getRoomId(), "Available");

                System.out.println("Reservation Cancelled Successfully.");

                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // ==========================
    // Check Reservation Exists
    // ==========================
    public boolean reservationExists(int reservationId) {

        String sql = "SELECT reservation_id FROM reservations WHERE reservation_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, reservationId);

            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // ==========================
    // Count Reservations
    // ==========================
    public int getReservationCount() {

        String sql = "SELECT COUNT(*) FROM reservations";

        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            if (rs.next()) {

                return rs.getInt(1);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    // ==========================
    // Count Active Reservations
    // ==========================
    public int getActiveReservationCount() {

        String sql = "SELECT COUNT(*) FROM reservations WHERE check_out >= CURDATE()";

        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            if (rs.next()) {

                return rs.getInt(1);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }
    // ==========================
    // Check In
    // ==========================
    public boolean checkIn(int reservationId) {

        Reservation reservation = getReservationById(reservationId);

        if (reservation == null) {
            System.out.println("Reservation not found.");
            return false;
        }

        String sql = "INSERT INTO checkins(reservation_id) VALUES(?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, reservationId);

            if (ps.executeUpdate() > 0) {

                updateRoomStatus(reservation.getRoomId(), "Occupied");

                System.out.println("Guest Checked In Successfully.");

                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // ==========================
    // Check Out
    // ==========================
    public boolean checkOut(int reservationId, double totalAmount) {

        Reservation reservation = getReservationById(reservationId);

        if (reservation == null) {
            System.out.println("Reservation not found.");
            return false;
        }

        Connection con = null;

        try {

            con = DBConnection.getConnection();
            con.setAutoCommit(false);

            // Insert checkout record
            String checkoutSql =
                    "INSERT INTO checkouts(reservation_id,total_amount) VALUES(?,?)";

            PreparedStatement checkoutPs =
                    con.prepareStatement(checkoutSql);

            checkoutPs.setInt(1, reservationId);
            checkoutPs.setDouble(2, totalAmount);

            checkoutPs.executeUpdate();

            // Insert payment record
            String paymentSql =
                    "INSERT INTO payments(reservation_id,total_amount,payment_method,payment_status) VALUES(?,?,?,?)";

            PreparedStatement paymentPs =
                    con.prepareStatement(paymentSql);

            paymentPs.setInt(1, reservationId);
            paymentPs.setDouble(2, totalAmount);
            paymentPs.setString(3, "Cash");
            paymentPs.setString(4, "Paid");

            paymentPs.executeUpdate();
            // Generate Invoice
            Customer customer = new CustomerDAO().getCustomerById(
                    reservation.getCustomerId());

            Room room = new RoomDAO().getRoomById(
                    reservation.getRoomId());

            Payment payment = new Payment();
            payment.setReservationId(reservationId);
            payment.setTotalAmount(totalAmount);
            payment.setPaymentMethod("Cash");
            payment.setPaymentStatus("Paid");

            InvoiceGenerator.generateInvoice(
                    customer,
                    room,
                    reservation,
                    payment
            );

            // Room Available Again
            updateRoomStatus(reservation.getRoomId(), "Available");

            // Delete check-in record first
            String deleteCheckinSql =
                    "DELETE FROM checkins WHERE reservation_id=?";

            PreparedStatement checkinPs =
                    con.prepareStatement(deleteCheckinSql);

            checkinPs.setInt(1, reservationId);

            checkinPs.executeUpdate();

            String deleteCheckInSql =
                    "DELETE FROM checkins WHERE reservation_id=?";

            PreparedStatement checkInDeletePs =
                    con.prepareStatement(deleteCheckInSql);

            checkInDeletePs.setInt(1, reservationId);
            checkInDeletePs.executeUpdate();

            con.commit();

            System.out.println("Check Out Completed Successfully.");

            return true;

        } catch (SQLException e) {

            try {
                if (con != null)
                    con.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            e.printStackTrace();

        } finally {

            try {
                if (con != null) {
                    con.setAutoCommit(true);
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        return false;
    }

    // ==========================
    // Calculate Bill
    // ==========================
    public double calculateBill(int reservationId) {

        Reservation reservation = getReservationById(reservationId);

        if (reservation == null) {
            return 0;
        }

        long days = ChronoUnit.DAYS.between(
                reservation.getCheckIn().toLocalDate(),
                reservation.getCheckOut().toLocalDate()
        );

        if (days <= 0) {
            days = 1;
        }

        double roomPrice = 0;

        String sql = "SELECT price FROM rooms WHERE room_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, reservation.getRoomId());

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                roomPrice = rs.getDouble("price");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return roomPrice * days;
    }
    // ==========================
    // Is Guest Checked In?
    // ==========================
    public boolean isCheckedIn(int reservationId) {

        String sql =
                "SELECT * FROM checkins WHERE reservation_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, reservationId);

            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

}
