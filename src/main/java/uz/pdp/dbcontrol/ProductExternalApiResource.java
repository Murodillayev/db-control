package uz.pdp.dbcontrol;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;


@Service
public class ProductExternalApiResource {

    private final WebClient webClient;

    public ProductExternalApiResource(@Qualifier("fb") WebClient webClient) {
        this.webClient = webClient;
    }

    public ResponseEntity<List<Product>> getAll() {

        List<Product> products =
                webClient
                        .get()
                        .uri("/products")
                        .retrieve()
                        .bodyToFlux(Product.class)
                        .collectList()
                        .block();

        return ResponseEntity.ok(products);
    }

    public ResponseEntity<Product> getById(@PathVariable(name = "id") Integer id) {
        Product product =
                webClient
                        .get()
                        .uri("/products/{id}", id)
                        .retrieve()
                        .bodyToMono(Product.class)
                        .block();
        return ResponseEntity.ok(product);
    }


    public ResponseEntity<ProductDto> post(@RequestBody ProductDto productDto) {
        ProductDto product =
                webClient.post()
                        .uri("/products")
                        .body(Mono.just(productDto), ProductDto.class)
                        .retrieve()
                        .bodyToMono(ProductDto.class)
                        .block();

        return ResponseEntity.ok(product);
    }

    public ResponseEntity<List<Product>> getAllAsync() {

        webClient.get()
                .uri("/products")
                .retrieve()
                .bodyToFlux(Product.class)
                .collectList()
                .flatMap(list -> {
                    System.out.println(Thread.currentThread().getName());
                    System.out.println(list);
                    return Mono.just(list);
                }).subscribe();

        System.out.println(Thread.currentThread().getName());
        return ResponseEntity.ok(null);
    }
}
