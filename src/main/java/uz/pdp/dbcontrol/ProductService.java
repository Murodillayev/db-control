package uz.pdp.dbcontrol;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repository;

    public Product create(ProductSaveDto dto) {
        Product product = new Product();
        product.setDescription(dto.getDescription());
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        return repository.save(product);
    }

    public Product update(ProductSaveDto dto, Long id) {
        Product product = repository.findById(id).orElseThrow(
                () -> new RuntimeException("Product not found")
        );
        product.setDescription(dto.getDescription());
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        return repository.save(product);
    }

    public Product get(Long id) {
        return repository.findById(id).orElse(null);
    }

    @SneakyThrows
    public List<Product> getAll() {
        Thread.sleep(3000);
        return repository.findAll();
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }


}

// simple
// redis
