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

    @PostMapping
    public ResponseEntity<Product> create(ProductSaveDto dto) {

        return new ResponseEntity<>(service.create(dto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> get(@PathVariable Long id) {
        return new ResponseEntity<>(service.get(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Product>> get() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody ProductSaveDto dto) {
        return new ResponseEntity<>(service.update(dto, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }


}
