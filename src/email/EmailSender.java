package email;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.io.File;
import java.util.Properties;

public class EmailSender {

    // Replace with your Gmail address
    private static final String FROM_EMAIL = "ravigorai161@gmail.com";

    // Replace with your 16-character App Password
    private static final String APP_PASSWORD = "qbeh atpg owyi bdts";

    public static void sendInvoice(String toEmail, String pdfPath) {

        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(FROM_EMAIL, APP_PASSWORD);
                    }
                });

        try {

            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(FROM_EMAIL));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(toEmail)
            );

            message.setSubject("Hotel Reservation Invoice");

            MimeBodyPart textPart = new MimeBodyPart();

            textPart.setText(
                    "Dear Customer,\n\n" +
                            "Thank you for choosing our hotel.\n" +
                            "Please find your invoice attached.\n\n" +
                            "Regards,\nHotel Reservation System"
            );

            MimeBodyPart attachmentPart = new MimeBodyPart();

            attachmentPart.attachFile(new File(pdfPath));

            Multipart multipart = new MimeMultipart();

            multipart.addBodyPart(textPart);
            multipart.addBodyPart(attachmentPart);

            message.setContent(multipart);

            Transport.send(message);

            System.out.println("Invoice emailed successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
