package invoice;

import util.LoggerUtil;
import java.util.logging.Logger;
import email.EmailSender;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import model.Customer;
import model.Payment;
import model.Reservation;
import model.Room;

import java.io.File;
import java.io.FileOutputStream;
import java.time.temporal.ChronoUnit;

public class InvoiceGenerator {
    private static final Logger logger = LoggerUtil.getLogger();

    public static void generateInvoice(
            Customer customer,
            Room room,
            Reservation reservation,
            Payment payment) {

        try {

            File folder = new File("Invoices");

            if (!folder.exists()) {
                folder.mkdirs();
            }

            File file = new File(
                    folder,
                    "Invoice_" + reservation.getReservationId() + ".pdf"
            );

            Document document = new Document();

            PdfWriter.getInstance(
                    document,
                    new FileOutputStream(file)
            );

            document.open();

            Font titleFont = new Font(Font.HELVETICA, 20, Font.BOLD);
            Font headingFont = new Font(Font.HELVETICA, 14, Font.BOLD);
            Font normalFont = new Font(Font.HELVETICA, 12);

            Paragraph title = new Paragraph(
                    "HOTEL RESERVATION SYSTEM",
                    titleFont
            );

            title.setAlignment(Element.ALIGN_CENTER);

            document.add(title);
            document.add(new Paragraph(" "));
            document.add(new Paragraph(
                    "Invoice No : INV-" + reservation.getReservationId(),
                    headingFont
            ));
            document.add(new Paragraph(" "));

            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100);

            table.addCell("Customer Name");
            table.addCell(customer.getName());

            table.addCell("Room Number");
            table.addCell(String.valueOf(room.getRoomNumber()));

            table.addCell("Room Type");
            table.addCell(room.getRoomType());

            table.addCell("Check In");
            table.addCell(reservation.getCheckIn().toString());

            table.addCell("Check Out");
            table.addCell(reservation.getCheckOut().toString());

            long days = ChronoUnit.DAYS.between(
                    reservation.getCheckIn().toLocalDate(),
                    reservation.getCheckOut().toLocalDate()
            );

            if (days <= 0) {
                days = 1;
            }

            table.addCell("Days Stayed");
            table.addCell(String.valueOf(days));

            table.addCell("Price Per Day");
            table.addCell("₹" + room.getPrice());

            table.addCell("Total Amount");
            table.addCell("₹" + payment.getTotalAmount());

            table.addCell("Payment Method");
            table.addCell(payment.getPaymentMethod());

            table.addCell("Payment Status");
            table.addCell(payment.getPaymentStatus());

            document.add(table);

            document.add(new Paragraph(" "));
            document.add(new Paragraph(
                    "Thank you for choosing our hotel!",
                    headingFont
            ));

            document.close();

            logger.info("Invoice generated successfully: " + file.getAbsolutePath());

            System.out.println("Invoice Generated Successfully!");
            System.out.println("Location: " + file.getAbsolutePath());
            EmailSender.sendInvoice(
                    customer.getEmail(),
                    file.getAbsolutePath()
            );

        } catch (Exception e) {
            logger.severe("Invoice generation failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
}