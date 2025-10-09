package uz.pdp.dbcontrol.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import uz.pdp.dbcontrol.model.dto.EmailDto;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {

    private final JavaMailSender mailSender;

    @Async
    public void sendEmail(EmailDto emailDto) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(emailDto.getTo());
            message.setSubject(emailDto.getSubject());
            message.setText(emailDto.getBody());
            
            mailSender.send(message);
            log.info("Email sent successfully to: {}", emailDto.getTo());
        } catch (Exception e) {
            log.error("Failed to send email to: {}. Error: {}", emailDto.getTo(), e.getMessage());
        }
    }

    public void sendWelcomeEmail(String email, String username, String tempPassword) {
        EmailDto emailDto = EmailDto.builder()
                .to(email)
                .subject("Welcome to DB Control System")
                .body(String.format(
                        "Hello %s,\n\n" +
                        "Welcome to DB Control System!\n\n" +
                        "Your account has been created successfully.\n" +
                        "Username: %s\n" +
                        "Temporary Password: %s\n\n" +
                        "Please change your password after first login.\n\n" +
                        "Best regards,\n" +
                        "DB Control Team",
                        username, username, tempPassword
                ))
                .build();
        sendEmail(emailDto);
    }

    public void sendAccountUpdateEmail(String email, String username) {
        EmailDto emailDto = EmailDto.builder()
                .to(email)
                .subject("Account Updated - DB Control System")
                .body(String.format(
                        "Hello %s,\n\n" +
                        "Your account information has been updated successfully.\n\n" +
                        "If you did not make this change, please contact administrator immediately.\n\n" +
                        "Best regards,\n" +
                        "DB Control Team",
                        username
                ))
                .build();
        sendEmail(emailDto);
    }

    public void sendAccountDeletionEmail(String email, String username) {
        EmailDto emailDto = EmailDto.builder()
                .to(email)
                .subject("Account Deleted - DB Control System")
                .body(String.format(
                        "Hello %s,\n\n" +
                        "Your account has been deleted from DB Control System.\n\n" +
                        "If you did not request this, please contact administrator immediately.\n\n" +
                        "Best regards,\n" +
                        "DB Control Team",
                        username
                ))
                .build();
        sendEmail(emailDto);
    }
}




