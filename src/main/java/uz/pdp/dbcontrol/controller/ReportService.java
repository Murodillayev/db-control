package uz.pdp.dbcontrol.controller;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ReportService {



    @Async
    public void sendReport() {
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Logger.getLogger(ReportService.class.getName()).log(Level.INFO, Thread.currentThread().getName() + ": " + i);

        }
    }
}
