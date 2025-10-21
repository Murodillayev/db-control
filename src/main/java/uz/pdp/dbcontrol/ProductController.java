package uz.pdp.dbcontrol;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;
    private final CacheService cacheService;

    @PostMapping
    public ResponseEntity<Product> create(ProductSaveDto dto) {

        cacheService.clear("products");
        return new ResponseEntity<>(service.create(dto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> get(@PathVariable Long id) {
        return new ResponseEntity<>(service.get(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Product>> get() {
        List<Product> productList = cacheService.get("products");

        if (productList == null) {
            productList = service.getAll();
            cacheService.put("products", productList);
        }

        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody ProductSaveDto dto) {
        cacheService.clear("products");
        return new ResponseEntity<>(service.update(dto, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        cacheService.clear("products");
        service.delete(id);
        return ResponseEntity.noContent().build();
    }


}
