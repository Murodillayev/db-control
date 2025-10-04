package uz.pdp.dbcontrol;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@FeignClient(name = "product-resource", url = "https://fakestoreapi.com")
public interface ProductExternalApiResource {

    @GetMapping("/products")
    ResponseEntity<List<Product>> getAll();

    @GetMapping("/products/{id}")
    ResponseEntity<Product> getById(@PathVariable(name = "id") Integer id, @RequestHeader("Authorization") String token);


    @PostMapping("/products")
    ResponseEntity<ProductDto> post(@RequestBody ProductDto productDto);
}
