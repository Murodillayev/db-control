package uz.pdp.dbcontrol;


import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {



    // majburiy emas.
    @Bean
    public CacheManager cacheManager(){
      CaffeineCacheManager cacheManager = new CaffeineCacheManager();
      return cacheManager;
    }
}
