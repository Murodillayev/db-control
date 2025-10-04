package uz.pdp.dbcontrol;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AppConfig {

    @Bean("fb")
    public WebClient webClientFakeBrowser() {
        return WebClient.create("https://fakestoreapi.com");
    }

    @Bean("jp")
    public WebClient webClientJsonPlaceholder() {
        return WebClient.create("https://jsonplaceholder.typicode.com");
    }


}
