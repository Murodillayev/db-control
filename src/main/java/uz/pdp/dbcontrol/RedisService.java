package uz.pdp.dbcontrol;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RedisService {


    private final RedisTemplate<String, List<Product>> redisTemplate;
    private final ValueOperations<String, List<Product>> operations;

    public RedisService(RedisTemplate<String, List<Product>> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.operations = redisTemplate.opsForValue();
    }

    public void set(String key, List<Product> value) {
        operations.set(key, value);
    }

    public List<Product> get(String key) {
        return operations.get(key);
    }
}


