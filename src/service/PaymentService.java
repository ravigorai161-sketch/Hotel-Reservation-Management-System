package service;

import dao.PaymentDAO;
import model.Payment;
import util.LoggerUtil;
import java.util.logging.Logger;

import java.sql.Date;
import java.util.List;

public class PaymentService {
    private static final Logger logger = LoggerUtil.getLogger();

    private PaymentDAO paymentDAO;

    public PaymentService() {
        paymentDAO = new PaymentDAO();
    }

    // ==========================
    // Add Payment
    // ==========================
    public boolean addPayment(Payment payment) {

        if (payment == null) {
            System.out.println("Payment cannot be null.");
            logger.warning("Failed to add payment: Payment object is null.");
            return false;
        }

        if (payment.getReservationId() <= 0) {
            System.out.println("Invalid Reservation ID.");
            logger.warning("Failed to add payment: Invalid Reservation ID.");
            return false;
        }

        if (payment.getTotalAmount() <= 0) {
            System.out.println("Amount must be greater than 0.");
            logger.warning("Failed to add payment: Invalid amount.");
            return false;
        }

        if (payment.getPaymentMethod() == null ||
                payment.getPaymentMethod().trim().isEmpty()) {

            System.out.println("Payment method is required.");
            logger.warning("Failed to add payment: Payment method is empty.");
            return false;
        }

        if (payment.getPaymentStatus() == null ||
                payment.getPaymentStatus().trim().isEmpty()) {

            payment.setPaymentStatus("Pending");
        }

        boolean added = paymentDAO.addPayment(payment);

        if (added) {
            logger.info("Payment added successfully. Reservation ID: "
                    + payment.getReservationId()
                    + ", Amount: ₹" + payment.getTotalAmount()
                    + ", Status: " + payment.getPaymentStatus());
        } else {
            logger.warning("Failed to add payment. Reservation ID: "
                    + payment.getReservationId());
        }

        return added;
    }
    // ==========================
    // Get Payment By ID
    // ==========================
    public Payment getPaymentById(int paymentId) {

        return paymentDAO.getPaymentById(paymentId);
    }

    // ==========================
    // Get Payment By Reservation
    // ==========================
    public Payment getPaymentByReservationId(int reservationId) {

        return paymentDAO.getPaymentByReservationId(reservationId);
    }

    // ==========================
    // View All Payments
    // ==========================
    public List<Payment> getAllPayments() {

        return paymentDAO.getAllPayments();
    }

    // ==========================
    // Update Payment Status
    // ==========================
    public boolean updatePaymentStatus(int paymentId, String status) {

        if (!paymentDAO.paymentExists(paymentId)) {

            System.out.println("Payment not found.");
            return false;
        }

        return paymentDAO.updatePaymentStatus(paymentId, status);
    }

    // ==========================
    // Delete Payment
    // ==========================
    public boolean deletePayment(int paymentId) {

        if (!paymentDAO.paymentExists(paymentId)) {

            System.out.println("Payment not found.");
            return false;
        }

        return paymentDAO.deletePayment(paymentId);
    }

    // ==========================
    // Paid Payments
    // ==========================
    public List<Payment> getPaidPayments() {

        return paymentDAO.getPaidPayments();
    }

    // ==========================
    // Pending Payments
    // ==========================
    public List<Payment> getPendingPayments() {

        return paymentDAO.getPendingPayments();
    }

    // ==========================
    // Search By Payment Method
    // ==========================
    public List<Payment> getPaymentsByMethod(String method) {

        return paymentDAO.getPaymentsByMethod(method);
    }

    // ==========================
    // Total Revenue
    // ==========================
    public double getTotalRevenue() {

        return paymentDAO.getTotalRevenue();
    }

    // ==========================
    // Today's Revenue
    // ==========================
    public double getTodayRevenue() {

        return paymentDAO.getTodayRevenue();
    }

    // ==========================
    // Monthly Revenue
    // ==========================
    public double getMonthlyRevenue(int month, int year) {

        return paymentDAO.getMonthlyRevenue(month, year);
    }

    // ==========================
    // Revenue Between Dates
    // ==========================
    public double getRevenueBetween(Date startDate, Date endDate) {

        return paymentDAO.getRevenueBetween(startDate, endDate);
    }

    // ==========================
    // Total Payments
    // ==========================
    public int getPaymentCount() {

        return paymentDAO.getPaymentCount();
    }

    // ==========================
    // Paid Payment Count
    // ==========================
    public int getPaidPaymentCount() {

        return paymentDAO.getPaidPaymentCount();
    }

    // ==========================
    // Pending Payment Count
    // ==========================
    public int getPendingPaymentCount() {

        return paymentDAO.getPendingPaymentCount();
    }
}