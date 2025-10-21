package uz.pdp.dbcontrol;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class CronService {

    @Scheduled(fixedRate = 20, timeUnit = TimeUnit.SECONDS)
    @CacheEvict(value = "products")
    public void clearProductCache() {
        log.info("Clearing products cache");
    }





}
