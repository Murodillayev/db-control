package uz.pdp.dbcontrol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableCaching
@EnableScheduling
public class DbControlApplication {

    public static void main(String[] args) {
        SpringApplication.run(DbControlApplication.class, args);
    }

}
