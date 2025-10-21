package uz.pdp.dbcontrol.services.mailing;

import org.springframework.stereotype.Service;

import jakarta.mail.*;
import jakarta.mail.internet.*;

import java.util.Properties;

@Service
public class Mail {
    public static void mail(String[] args) {
        Mail mail = new Mail();
        mail.sendMail("silverwolf256916@gmail.com", "Test", "Test text");
    }

    public void sendMail(String to, String subject, String text) {
        final String email = "BackendCoffee@gmail.com";
        final String password = "dzcf nspr psno ikck";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new jakarta.mail.Authenticator() {
            @Override
            protected jakarta.mail.PasswordAuthentication getPasswordAuthentication() {
                return new jakarta.mail.PasswordAuthentication(email, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(text);

            Transport.send(message);
            System.out.println("\u001b[32mEmail Successfully sent to -[\u001b[0m" + to + "\u001b[32m]-");
        } catch (MessagingException e) {
            System.out.println("\u001b[31mEmail Not sent to -[\u001b[0m" + to + "\u001b[32m]-");
            e.printStackTrace();
        }

    }
}
