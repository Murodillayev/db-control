package uz.pdp.dbcontrol.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import uz.pdp.dbcontrol.model.entity.AuthUser;

@Service
@Profile("telegram")
public class TelegramNotifyService implements NotifyService {
    @Override
    public void sendUserCredentials(AuthUser authUser) {

    }
}
