package uz.pdp.dbcontrol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import uz.pdp.dbcontrol.props.RemoteApiProps;

@SpringBootApplication
@EnableConfigurationProperties({RemoteApiProps.class})
public class DbControlApplication {

    public static void main(String[] args) {
        SpringApplication.run(DbControlApplication.class, args);
    }


}

// Call external api

// HttpClient (low-level)

// RestTemplate, FeignClient, WebClient (high-level)