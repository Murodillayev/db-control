package uz.pdp.dbcontrol;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CacheService {
    private final Cache cache;

    public CacheService(CacheManager cacheManager) {
        cache = cacheManager.getCache("products");
    }

    public List<Product> getProducts(String key) {
        return (List<Product>) cache.get(key);
    }


    public void put(String key, List<Product> products) {
        cache.put(key, products);
    }



}
