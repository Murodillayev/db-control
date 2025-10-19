package uz.pdp.dbcontrol.services;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import uz.pdp.dbcontrol.repos.CreateUserEvent;
import uz.pdp.dbcontrol.models.MVRefresher;

@Service
@RequiredArgsConstructor
public class ListenerService {
    private final TelegramNotifyService telegramNotifyService;
    private final EmailNotifyService emailNotifyService;
    private final MVRefresher mvRefresher;
    private final CacheService cacheService;

    //consumer 1
    @EventListener(CreateUserEvent.class)
    @Async
    public void sendNotifyModerator(CreateUserEvent event) {
        telegramNotifyService.notifyRegisterUser(event.getAuthUser());

    }

    //consumer 2
//    @EventListener(CreateUserEvent.class)
//    @Async
    @TransactionalEventListener(classes = {CreateUserEvent.class}, phase = TransactionPhase.AFTER_ROLLBACK)
    public void sendNotifyEmail(CreateUserEvent event) {
        try {
            emailNotifyService.notify(event.getAuthUser());
        } catch (Exception e) {
            cacheService.put(event);
        }
    }

    //consumer 3
    @EventListener(CreateUserEvent.class)
    @Async
    public void refreshUserStat(CreateUserEvent event) {
        mvRefresher.refreshStatistics();
    }

}
