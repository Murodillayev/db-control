package uz.pdp.dbcontrol;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TelegramNotifyService {

    public void notifyRegisterUser(AuthUser authUser) {
        log.info("Send notify register to Moderators : user = {}", authUser);
    }
}
