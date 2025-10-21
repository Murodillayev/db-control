package uz.pdp.dbcontrol;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repository;
    private final CacheService cacheService;

    //    @CacheEvict(value = "products", allEntries = true)
    public Product create(ProductSaveDto dto) {
        Product product = new Product();
        product.setDescription(dto.getDescription());
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        return repository.save(product);
    }


    @CacheEvict(value = "products", allEntries = true)
    @CachePut(value = "product", key = "#id")
    public Product update(ProductSaveDto dto, Long id) {
        Product product = repository.findById(id).orElseThrow(
                () -> new RuntimeException("Product not found")
        );
        product.setDescription(dto.getDescription());
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        return repository.save(product);
    }


    @Cacheable(value = "product", key = "#id")
    @SneakyThrows
    public Product get(Long id) {
        Thread.sleep(2000);
        return repository.findById(id).orElse(null);
    }

    @SneakyThrows
    @Cacheable(value = "products")
    public List<Product> getAll() {
        List<Product> products = cacheService.getProducts("products");
        if (products != null) {
            return products;
        }
        List<Product> all = repository.findAll();
        Thread.sleep(3000);
        cacheService.put("products",all);
        return all;
    }

    //    @CacheEvict(value = "products", allEntries = true)
    @CacheEvict(value = {"product"}, key = "#id")
    public void delete(Long id) {
        repository.deleteById(id);
    }

}


// simple   ->      // Con
// redis
