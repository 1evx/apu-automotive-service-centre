/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utility;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Asus
 */
public class EmailUtility {
    private static final String SENDER_EMAIL = "haosheanliew@gmail.com"; 
    private static final String APP_PASSWORD = "hkvz yfwm eyjs rngc";
    
    public static void sendCustomerWelcomeEmail(String targetEmail, String customerName, String tempPassword) {
        
        // 1. Setup the Gmail SMTP Server Properties
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true"); // Required for Gmail security

        // 2. Authenticate with Google
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SENDER_EMAIL, APP_PASSWORD);
            }
        });

        try {
            // 3. Draft the Email
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(SENDER_EMAIL));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(targetEmail));
            message.setSubject("Welcome to APU Automotive Service Centre!");

            // 4. Design the Email Body (You can use HTML!)
            String htmlContent = "<div style='font-family: Arial, sans-serif; padding: 20px; color: #333;'>"
                    + "<h2 style='color: #0d6efd;'>Welcome, " + customerName + "!</h2>"
                    + "<p>Your customer profile has been successfully registered by our technician.</p>"
                    + "<p>Here are your initial login details to access the customer portal:</p>"
                    + "<div style='background-color: #f8f9fa; padding: 15px; border-radius: 5px; margin: 20px 0;'>"
                    + "<strong>Email / Username:</strong> " + targetEmail + "<br>"
                    + "<strong>Temporary Password:</strong> <span style='color: #dc3545;'>" + tempPassword + "</span>"
                    + "</div>"
                    + "<p><i>For your security, please log in and change your password immediately.</i></p>"
                    + "<hr>"
                    + "<p style='font-size: 12px; color: #777;'>This is an automated message from APU Automotive Service Centre. Please do not reply.</p>"
                    + "</div>";

            message.setContent(htmlContent, "text/html");

            // 5. Send the Email
            Transport.send(message);
            System.out.println("DEBUG: Welcome email sent successfully to " + targetEmail);

        } catch (MessagingException e) {
            System.err.println("DEBUG: Failed to send email to " + targetEmail);
            e.printStackTrace();
        }
    }
    
    public static void sendStaffWelcomeEmail(String targetEmail, String staffName, String tempPassword, String assignedRole) {
        
        // 1. Setup the Gmail SMTP Server Properties (Same as before)
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true"); 

        // 2. Authenticate with Google
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SENDER_EMAIL, APP_PASSWORD);
            }
        });

        try {
            // 3. Draft the Email
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(SENDER_EMAIL));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(targetEmail));
            
            // A professional subject line for new employees
            message.setSubject("Welcome to the APU Automotive Team!");

            // 4. Design the Email Body (Tailored for Staff)
            String htmlContent = "<div style='font-family: Arial, sans-serif; padding: 20px; color: #333; max-width: 600px; border: 1px solid #e0e0e0; border-radius: 8px;'>"
                    + "<h2 style='color: #0d6efd;'>Welcome aboard, " + staffName + "!</h2>"
                    + "<p>Your employee account has been successfully provisioned by the administration team.</p>"
                    + "<p>You have been assigned the system role of: <strong>" + assignedRole + "</strong>.</p>"
                    + "<p>Here are your official login credentials to access the staff portal:</p>"
                    + "<div style='background-color: #f8f9fa; padding: 15px; border-left: 4px solid #0d6efd; margin: 20px 0;'>"
                    + "<strong>Login Email:</strong> " + targetEmail + "<br>"
                    + "<strong>Temporary Password:</strong> <span style='font-family: monospace; font-size: 16px; color: #dc3545;'>" + tempPassword + "</span>"
                    + "</div>"
                    + "<p><i>Security Notice: You are required to log in and change your temporary password immediately upon your first login.</i></p>"
                    + "<br>"
                    + "<p>Best regards,<br><strong>APU Automotive Management</strong></p>"
                    + "<hr style='border: none; border-top: 1px solid #eee; margin-top: 20px;'>"
                    + "<p style='font-size: 11px; color: #999; text-align: center;'>This is an automated system message. Please do not reply directly to this email.</p>"
                    + "</div>";

            message.setContent(htmlContent, "text/html");

            // 5. Send the Email
            Transport.send(message);
            System.out.println("DEBUG: Staff Welcome email sent successfully to " + targetEmail);

        } catch (MessagingException e) {
            System.err.println("DEBUG: Failed to send Staff Welcome email to " + targetEmail);
            e.printStackTrace();
        }
    }
}
