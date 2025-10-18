package uz.pdp.dbcontrol;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
@Slf4j
public class EmailNotifyService {

    private final AuthUserRepository authUserRepository;

    public EmailNotifyService(AuthUserRepository authUserRepository) {
        this.authUserRepository = authUserRepository;
    }


    public void notify(AuthUser authUser) {
        String otp = UUID.randomUUID().toString().replace("-", "").toUpperCase().substring(0, 4);
        authUser.setOtp(otp);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        if (new Random().nextBoolean()) {
            throw new RuntimeException("cannot send moderator notification");

        }
        authUserRepository.save(authUser);
        log.info("Notifying email {}", authUser.getEmail());
    }

}
