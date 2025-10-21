package uz.pdp.dbcontrol;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
public class RedisService {


    private final RedisTemplate<String, List<Product>> redisTemplate;
    private final ValueOperations<String, List<Product>> operations;


    private static final String priductPrefix = "PRODUCT_";
    private static final String personPrefix = "PERSON_";


    public RedisService(RedisTemplate<String, List<Product>> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.operations = redisTemplate.opsForValue();
    }

    public void setProduct(String key, List<Product> value) {
        operations.set(priductPrefix + key, value);
    }

    public void sePerson(String key, List<Product> value) {
        operations.set(personPrefix + key, value);
    }

    public List<Product> get(String key) {
        return operations.get(key);
    }
}


