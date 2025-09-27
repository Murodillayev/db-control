package uz.pdp.dbcontrol.service;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import uz.pdp.dbcontrol.model.entity.AuthUser;

@Service
public class EmailService implements NotifyService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    @Async
    public void sendUserCredentials(AuthUser authUser) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
//            MimeMessageHelper helper = new MimeMessageHelper(message, true);
//            helper.setTo(authUser.getEmail());
//            helper.setSubject(subject);
//            helper.setText(htmlBody, true); // true - HTML formatini yoqish

            // Agar fayl qo‘shish kerak bo‘lsa
//            if (attachmentPath != null && !attachmentPath.isEmpty()) {
//                FileSystemResource file = new FileSystemResource(new File(attachmentPath));
//                helper.addAttachment(file.getFilename(), file);


//            }

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
