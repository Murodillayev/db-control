package uz.pdp.dbcontrol;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MVRefresher {
    public void refreshStatistics() {
        log.info("Refresh statistics");
    }
}
