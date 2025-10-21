package uz.pdp.dbcontrol;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CacheService {

    private final ConcurrentHashMap<String, List<Product>> PRODUCTS = new ConcurrentHashMap<>();

    public List<Product> get(String key) {
        try {
            return PRODUCTS.get(key);
        } catch (Exception e) {
            return null;
        }
    }

    public void put(String key, List<Product> list) {
        PRODUCTS.put(key, list);
    }

    public void clear(String products) {
        PRODUCTS.remove(products);
    }
}
