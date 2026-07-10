package ui;

import model.Payment;
import service.PaymentService;

import java.util.List;
import java.util.Scanner;

public class PaymentMenu {

    private Scanner scanner;
    private PaymentService paymentService;

    public PaymentMenu() {
        scanner = new Scanner(System.in);
        paymentService = new PaymentService();
    }

    public void start() {

        while (true) {

            System.out.println("\n========== PAYMENT MANAGEMENT ==========");
            System.out.println("1. Add Payment");
            System.out.println("2. View All Payments");
            System.out.println("3. Search Payment");
            System.out.println("4. Update Payment Status");
            System.out.println("5. Delete Payment");
            System.out.println("6. Back");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    addPayment();
                    break;

                case 2:
                    viewPayments();
                    break;

                case 3:
                    searchPayment();
                    break;

                case 4:
                    updatePaymentStatus();
                    break;

                case 5:
                    deletePayment();
                    break;

                case 6:
                    return;

                default:
                    System.out.println("Invalid Choice.");
            }

        }

    }

    // ==========================
    // Add Payment
    // ==========================

    private void addPayment() {

        Payment payment = new Payment();

        System.out.print("Reservation ID: ");
        payment.setReservationId(scanner.nextInt());

        System.out.print("Total Amount: ");
        payment.setTotalAmount(scanner.nextDouble());
        scanner.nextLine();

        System.out.print("Payment Method (Cash/Card/UPI): ");
        payment.setPaymentMethod(scanner.nextLine());

        System.out.print("Payment Status (Paid/Pending): ");
        payment.setPaymentStatus(scanner.nextLine());

        if (paymentService.addPayment(payment)) {

            System.out.println("Payment Added Successfully.");

        } else {

            System.out.println("Failed to Add Payment.");

        }

    }

    // ==========================
    // View All Payments
    // ==========================

    private void viewPayments() {

        List<Payment> payments = paymentService.getAllPayments();

        if (payments.isEmpty()) {

            System.out.println("No Payments Found.");
            return;

        }

        for (Payment payment : payments) {

            System.out.println("--------------------------------");
            System.out.println("Payment ID     : " + payment.getPaymentId());
            System.out.println("Reservation ID : " + payment.getReservationId());
            System.out.println("Amount         : " + payment.getTotalAmount());
            System.out.println("Method         : " + payment.getPaymentMethod());
            System.out.println("Status         : " + payment.getPaymentStatus());

        }

    }

    // ==========================
    // Search Payment
    // ==========================

    private void searchPayment() {

        System.out.print("Enter Payment ID: ");

        int paymentId = scanner.nextInt();
        scanner.nextLine();

        Payment payment = paymentService.getPaymentById(paymentId);

        if (payment == null) {

            System.out.println("Payment Not Found.");
            return;

        }

        System.out.println("\nPayment Details");
        System.out.println("----------------------------");
        System.out.println("Payment ID     : " + payment.getPaymentId());
        System.out.println("Reservation ID : " + payment.getReservationId());
        System.out.println("Amount         : " + payment.getTotalAmount());
        System.out.println("Method         : " + payment.getPaymentMethod());
        System.out.println("Status         : " + payment.getPaymentStatus());

    }

    // ==========================
    // Update Payment Status
    // ==========================

    private void updatePaymentStatus() {

        System.out.print("Enter Payment ID: ");
        int paymentId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter New Status: ");
        String status = scanner.nextLine();

        if (paymentService.updatePaymentStatus(paymentId, status)) {

            System.out.println("Payment Status Updated.");

        } else {

            System.out.println("Update Failed.");

        }

    }

    // ==========================
    // Delete Payment
    // ==========================

    private void deletePayment() {

        System.out.print("Enter Payment ID: ");

        int paymentId = scanner.nextInt();
        scanner.nextLine();

        if (paymentService.deletePayment(paymentId)) {

            System.out.println("Payment Deleted Successfully.");

        } else {

            System.out.println("Payment Not Found.");

        }

    }

}
