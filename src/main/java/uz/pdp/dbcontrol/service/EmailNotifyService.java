package uz.pdp.dbcontrol.service;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import uz.pdp.dbcontrol.model.entity.AuthUser;

@Service("email-notify")
public class EmailNotifyService implements NotifyService {

    private final JavaMailSender mailSender;

    public EmailNotifyService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    @Async
    public void sendUserCredentials(AuthUser authUser) {
        try {
            MimeMessage message = mailSender.createMimeMessage();

            String subject = """
                    Salom %s
                    Sizning database usernamingizr: %s,
                    database password: %s
                    """.formatted(authUser.getUsername(), authUser.getDbUsername(), authUser.getDbPassword());

            message.setRecipients(Message.RecipientType.TO, authUser.getEmail());
            message.setSubject("DB control user credentials");
            message.setText(subject);
            mailSender.send(message);
            System.out.println("Mime email muvaffaqiyatli yuborildi!");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }
}
