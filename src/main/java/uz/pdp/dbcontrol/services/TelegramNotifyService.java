package uz.pdp.dbcontrol.services;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uz.pdp.dbcontrol.models.AuthUser;

@Service
@Slf4j
public class TelegramNotifyService {

    public void notifyRegisterUser(AuthUser authUser) {
        log.info("Send notify register to Moderators : user = {}", authUser);
    }
}
