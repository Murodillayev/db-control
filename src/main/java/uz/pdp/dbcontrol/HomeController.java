package uz.pdp.dbcontrol;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/product")
@Slf4j
public class HomeController {


    private final ProductExternalApiResource productExternalApiResource;

    public HomeController(ProductExternalApiResource productExternalApiResource) {
        this.productExternalApiResource = productExternalApiResource;
    }

    @GetMapping
    public String getAll(Model model) {
        ResponseEntity<List<Product>> all = productExternalApiResource.getAll();

        model.addAttribute("products", all.getBody());
        return "index";
    }

    @GetMapping("/{id}")
    public String get(@PathVariable Integer id, Model model) {
        ResponseEntity<Product> response = productExternalApiResource.getById(id);

        model.addAttribute("products", List.of(response.getBody()));
        return "index";
    }

    @GetMapping("/post")
    public String post(Model model) {

        ProductDto dto = ProductDto.builder()
                .category("book")
                .description("This is a book")
                .id(100)
                .price(111.1)
                .image(null)
                .name("Java in action")
                .build();
        ResponseEntity<ProductDto> response = productExternalApiResource.post(dto);

        model.addAttribute("products", List.of(response.getBody()));
        return "index";
    }


}
