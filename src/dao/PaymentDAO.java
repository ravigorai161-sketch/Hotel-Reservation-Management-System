package dao;

import model.Payment;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAO {

    // ==========================
    // Add Payment
    // ==========================
    public boolean addPayment(Payment payment) {

        String sql =
                "INSERT INTO payments(reservation_id,total_amount,payment_method,payment_status) VALUES(?,?,?,?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, payment.getReservationId());
            ps.setDouble(2, payment.getTotalAmount());
            ps.setString(3, payment.getPaymentMethod());
            ps.setString(4, payment.getPaymentStatus());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Payment Added Successfully.");
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // ==========================
    // Get Payment By ID
    // ==========================
    public Payment getPaymentById(int paymentId) {

        String sql =
                "SELECT * FROM payments WHERE payment_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, paymentId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Payment payment = new Payment();

                payment.setPaymentId(rs.getInt("payment_id"));
                payment.setReservationId(rs.getInt("reservation_id"));
                payment.setTotalAmount(rs.getDouble("total_amount"));
                payment.setPaymentMethod(rs.getString("payment_method"));
                payment.setPaymentStatus(rs.getString("payment_status"));

                // Remove this line if your model doesn't have paymentDate
                // payment.setPaymentDate(rs.getTimestamp("payment_date"));

                return payment;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // ==========================
    // Get Payment By Reservation ID
    // ==========================
    public Payment getPaymentByReservationId(int reservationId) {

        String sql =
                "SELECT * FROM payments WHERE reservation_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, reservationId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Payment payment = new Payment();

                payment.setPaymentId(rs.getInt("payment_id"));
                payment.setReservationId(rs.getInt("reservation_id"));
                payment.setTotalAmount(rs.getDouble("total_amount"));
                payment.setPaymentMethod(rs.getString("payment_method"));
                payment.setPaymentStatus(rs.getString("payment_status"));

                // Remove if paymentDate not added
                // payment.setPaymentDate(rs.getTimestamp("payment_date"));

                return payment;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // ==========================
    // Get All Payments
    // ==========================
    public List<Payment> getAllPayments() {

        List<Payment> payments = new ArrayList<>();

        String sql =
                "SELECT * FROM payments ORDER BY payment_date DESC";

        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {

                Payment payment = new Payment();

                payment.setPaymentId(rs.getInt("payment_id"));
                payment.setReservationId(rs.getInt("reservation_id"));
                payment.setTotalAmount(rs.getDouble("total_amount"));
                payment.setPaymentMethod(rs.getString("payment_method"));
                payment.setPaymentStatus(rs.getString("payment_status"));

                // Remove if paymentDate not added
                // payment.setPaymentDate(rs.getTimestamp("payment_date"));

                payments.add(payment);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return payments;
    }
    // ==========================
    // Update Payment Status
    // ==========================
    public boolean updatePaymentStatus(int paymentId, String status) {

        String sql = "UPDATE payments SET payment_status=? WHERE payment_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setInt(2, paymentId);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Payment Status Updated.");
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // ==========================
    // Delete Payment
    // ==========================
    public boolean deletePayment(int paymentId) {

        String sql = "DELETE FROM payments WHERE payment_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, paymentId);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Payment Deleted Successfully.");
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // ==========================
    // Check Payment Exists
    // ==========================
    public boolean paymentExists(int paymentId) {

        String sql = "SELECT payment_id FROM payments WHERE payment_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, paymentId);

            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // ==========================
    // Get Paid Payments
    // ==========================
    public List<Payment> getPaidPayments() {

        List<Payment> payments = new ArrayList<>();

        String sql = "SELECT * FROM payments WHERE payment_status='Paid' ORDER BY payment_date DESC";

        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {

                Payment payment = new Payment();

                payment.setPaymentId(rs.getInt("payment_id"));
                payment.setReservationId(rs.getInt("reservation_id"));
                payment.setTotalAmount(rs.getDouble("total_amount"));
                payment.setPaymentMethod(rs.getString("payment_method"));
                payment.setPaymentStatus(rs.getString("payment_status"));

                payments.add(payment);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return payments;
    }

    // ==========================
    // Get Pending Payments
    // ==========================
    public List<Payment> getPendingPayments() {

        List<Payment> payments = new ArrayList<>();

        String sql = "SELECT * FROM payments WHERE payment_status='Pending' ORDER BY payment_date DESC";

        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {

                Payment payment = new Payment();

                payment.setPaymentId(rs.getInt("payment_id"));
                payment.setReservationId(rs.getInt("reservation_id"));
                payment.setTotalAmount(rs.getDouble("total_amount"));
                payment.setPaymentMethod(rs.getString("payment_method"));
                payment.setPaymentStatus(rs.getString("payment_status"));

                payments.add(payment);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return payments;
    }

    // ==========================
    // Get Payments By Method
    // ==========================
    public List<Payment> getPaymentsByMethod(String method) {

        List<Payment> payments = new ArrayList<>();

        String sql = "SELECT * FROM payments WHERE payment_method=? ORDER BY payment_date DESC";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, method);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Payment payment = new Payment();

                payment.setPaymentId(rs.getInt("payment_id"));
                payment.setReservationId(rs.getInt("reservation_id"));
                payment.setTotalAmount(rs.getDouble("total_amount"));
                payment.setPaymentMethod(rs.getString("payment_method"));
                payment.setPaymentStatus(rs.getString("payment_status"));

                payments.add(payment);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return payments;
    }
    // ==========================
    // Get Total Revenue
    // ==========================
    public double getTotalRevenue() {

        String sql = "SELECT IFNULL(SUM(total_amount),0) AS revenue FROM payments WHERE payment_status='Paid'";

        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            if (rs.next()) {
                return rs.getDouble("revenue");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    // ==========================
    // Today's Revenue
    // ==========================
    public double getTodayRevenue() {

        String sql = """
                SELECT IFNULL(SUM(total_amount),0) AS revenue
                FROM payments
                WHERE DATE(payment_date)=CURDATE()
                AND payment_status='Paid'
                """;

        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            if (rs.next()) {
                return rs.getDouble("revenue");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    // ==========================
    // Monthly Revenue
    // ==========================
    public double getMonthlyRevenue(int month, int year) {

        String sql = """
                SELECT IFNULL(SUM(total_amount),0) AS revenue
                FROM payments
                WHERE MONTH(payment_date)=?
                AND YEAR(payment_date)=?
                AND payment_status='Paid'
                """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, month);
            ps.setInt(2, year);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getDouble("revenue");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    // ==========================
    // Total Payment Count
    // ==========================
    public int getPaymentCount() {

        String sql = "SELECT COUNT(*) FROM payments";

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
    // Paid Payment Count
    // ==========================
    public int getPaidPaymentCount() {

        String sql = """
                SELECT COUNT(*)
                FROM payments
                WHERE payment_status='Paid'
                """;

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
    // Pending Payment Count
    // ==========================
    public int getPendingPaymentCount() {

        String sql = """
                SELECT COUNT(*)
                FROM payments
                WHERE payment_status='Pending'
                """;

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
    // Revenue Between Two Dates
    // ==========================
    public double getRevenueBetween(Date startDate, Date endDate) {

        String sql = """
                SELECT IFNULL(SUM(total_amount),0) AS revenue
                FROM payments
                WHERE payment_date BETWEEN ? AND ?
                AND payment_status='Paid'
                """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDate(1, startDate);
            ps.setDate(2, endDate);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getDouble("revenue");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

}
