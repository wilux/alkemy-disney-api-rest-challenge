package alkemy.challenge.disney_api_rest.util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Component;

import java.util.Properties;

@Component // Marks this as a component. Now, Spring Boot will handle the creation and
           // management of the JWTUtil Bean
public class Mail {
    public void sendMailTo(String mailTo) {
        System.out.println("sending");
        String message = "Registration successful";
        String subject = "Disney Api Registration: Confirmation";
        String to = mailTo;
        String from = "nestorchoele@gmail.com";

        sendEmail(message, subject, to, from);
    }

    private static void sendEmail(String message, String subject, String to, String from) {
        String host = "smtp.gmail.com";
        Properties properties = System.getProperties();

        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("nestorchoele@gmail.com", "xcahzpflqxietsgn"); // Temporal password
                                                                                                 // for Challenge
            }

        });

        session.setDebug(true);

        MimeMessage m = new MimeMessage(session);
        try {
            m.setFrom(from);
            m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            m.setSubject(subject);
            m.setText(message);
            Transport.send(m);

            System.out.println("Sent");
        } catch (Exception e) {
            System.err.println(e);
        }

    }

}
