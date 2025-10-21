package uz.pdp.dbcontrol.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uz.pdp.dbcontrol.repos.AuthUserRepository;
import uz.pdp.dbcontrol.models.AuthUser;
import uz.pdp.dbcontrol.services.mailing.MailSender;

import java.security.SecureRandom;

@Service
@Slf4j
public class EmailNotifyService {

    private final AuthUserRepository authUserRepository;
    private final MailSender mailSender;
    private final SecureRandom random = new SecureRandom();

    public EmailNotifyService(AuthUserRepository authUserRepository, MailSender mailSender) {
        this.authUserRepository = authUserRepository;
        this.mailSender = mailSender;
    }

    public String generateOtp() {
        int code = 100000 + random.nextInt(900000); // 6-digit
        return String.valueOf(code);
    }

    public void sendOtp(AuthUser user) {
        String subject = "Your DB Control OTP Code";
        String text = "Your one-time code is: " + user.getOtp() + "\nIt expires in 5 minutes.";
        try {
            mailSender.sendMail(user.getEmail(), subject, text);
            log.info("OTP sent to {}", user.getEmail());
        } catch (Exception e) {
            log.error("Failed to send OTP to {}: {}", user.getEmail(), e.getMessage());
            throw new RuntimeException("Failed to send OTP");
        }
    }
}
