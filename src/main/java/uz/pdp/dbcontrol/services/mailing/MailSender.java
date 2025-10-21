package uz.pdp.dbcontrol.services.mailing;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailSender {
    private final Mail mailService;

    public void sendMail(String to, String subject, String text) {
        mailService.sendMail(to, subject, text);
    }

}
