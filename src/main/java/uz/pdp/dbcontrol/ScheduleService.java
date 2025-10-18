package uz.pdp.dbcontrol;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ScheduleService {

    private final CacheService cacheService;
    private final EmailNotifyService emailNotifyService;

    public ScheduleService(CacheService cacheService, EmailNotifyService emailNotifyService) {
        this.cacheService = cacheService;
        this.emailNotifyService = emailNotifyService;
    }

    @Scheduled(cron = "*/3 * * * * *")
    public void sendNotifyEmail() {
        log.info("Cron working");

        if (cacheService.getSize() < 1) {
            return;
        }
        CreateUserEvent poll = cacheService.poll();
        try {
            emailNotifyService.notify(poll.getAuthUser());
        } catch (Exception e) {
            cacheService.put(poll);
        }
        log.info("CRON :: Sending email to {}, Queue size => {} ", poll.getAuthUser(), cacheService.getSize());

    }
}
