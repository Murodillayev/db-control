package uz.pdp.dbcontrol;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import uz.pdp.dbcontrol.props.RemoteApiProps;

import java.util.List;

@Controller
@RequestMapping("/product")
@Slf4j
public class HomeController {

    private final RestTemplate restTemplate;
    private final RemoteApiProps remoteApiProps;

    public HomeController(RestTemplate restTemplate, RemoteApiProps remoteApiProps) {
        this.restTemplate = restTemplate;
        this.remoteApiProps = remoteApiProps;
    }


    @GetMapping("/for-object")
    public String testGetForObjectAll(Model model) {
        Product[] products = restTemplate.getForObject(remoteApiProps.getProductsApi(), Product[].class);
        model.addAttribute("products", products);
        return "index";
    }

    @GetMapping("/for-object/{id}")
    public String testGetForObject(Model model, @PathVariable String id) {
        Product product = restTemplate.getForObject(remoteApiProps.getProductApi(), Product.class, id);

        model.addAttribute("products", List.of(product));
        return "index";
    }


    @GetMapping("/for-entity")
    public String testGetForEntityAll(Model model) {

        ResponseEntity<Product[]> forEntity = restTemplate.getForEntity(remoteApiProps.getProductsApi(), Product[].class);

        HttpHeaders headers = forEntity.getHeaders();
        HttpStatusCode statusCode = forEntity.getStatusCode();
        Product[] products = forEntity.getBody();

        model.addAttribute("products", products);
        model.addAttribute("statusCode", statusCode);
        model.addAttribute("headers", headers);
        return "index";
    }

    @GetMapping("/for-entity/{id}")
    public String testGetForEntity(Model model, @PathVariable String id) {

        ResponseEntity<Product> forEntity = restTemplate.getForEntity(remoteApiProps.getProductApi(), Product.class, id);

        HttpHeaders headers = forEntity.getHeaders();
        HttpStatusCode statusCode = forEntity.getStatusCode();
        Product product = forEntity.getBody();
        model.addAttribute("products", List.of(product));
        model.addAttribute("statusCode", statusCode);
        model.addAttribute("headers", headers);
        return "index";
    }

    @GetMapping("/exchange")
    public String testExchange(Model model) {
        ResponseEntity<List<Product>> exchange = restTemplate.exchange(remoteApiProps.getProductsApi(), HttpMethod.GET, null, new ParameterizedTypeReference<>() {
        });

        HttpHeaders headers = exchange.getHeaders();
        HttpStatusCode statusCode = exchange.getStatusCode();
        List<Product> products = exchange.getBody();

        model.addAttribute("products", products);
        model.addAttribute("statusCode", statusCode);
        model.addAttribute("headers", headers);
        return "index";
    }

    @GetMapping("/exchange/{id}")
    public String testExchange(Model model, @PathVariable String id) {
//        Map<String, Object> reqBody = Map.of(
//                "username", "admin",
//                "password", "123"
//        );
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
//        httpHeaders.put("Content-Disposition", List.of("attachment; filename=product.json"));
//        httpHeaders.put("Authorization", List.of("Bearer adsksafhoaisdoyuyoiasiyasdouiysadoiuasdiouyadsiouyadsiouy"));
//        HttpEntity<Map<String, Object>> httpEntity = new HttpEntity<>(reqBody, httpHeaders);
        ResponseEntity<Product> exchange = restTemplate.exchange(remoteApiProps.getProductApi(), HttpMethod.GET, null, Product.class, id);
        HttpHeaders headers = exchange.getHeaders();
        HttpStatusCode statusCode = exchange.getStatusCode();
        Product product = exchange.getBody();

        model.addAttribute("products", List.of(product));
        model.addAttribute("statusCode", statusCode);
        model.addAttribute("headers", headers);
        return "index";
    }


    @GetMapping("/post")
    public String testPost(Model model) {

        ProductCreateDto requestBody = ProductCreateDto.builder()
                .id(100)
                .price(12.1)
                .image("https://www.google.com/url?sa=i&url=https%3A%2F%2Fmedium.com%2F%40rafaelmammadov%2Fwhat-is-java-what-is-ide-3b2fa48fb6a1&psig=AOvVaw3kaQ6ooVr5lWMyQGC5qd58&ust=1759642393798000&source=images&cd=vfe&opi=89978449&ved=0CBUQjRxqFwoTCNjAqPDoiZADFQAAAAAdAAAAABAE")
                .name("Java in action")
                .category("Book")
                .description("This is a test")
                .build();


        ResponseEntity<Product> response = restTemplate.postForEntity(remoteApiProps.getProductsApi(), requestBody, Product.class);
        model.addAttribute("products", List.of(response.getBody()));
        model.addAttribute("statusCode", response.getStatusCode());
        model.addAttribute("headers", response.getHeaders());

        return "index";
    }

    @GetMapping("/post-with-exchange")
    public String testPostExchange(Model model) {

        ProductCreateDto requestBody = ProductCreateDto.builder()
                .id(100)
                .price(12.1)
                .image("https://www.google.com/url?sa=i&url=https%3A%2F%2Fmedium.com%2F%40rafaelmammadov%2Fwhat-is-java-what-is-ide-3b2fa48fb6a1&psig=AOvVaw3kaQ6ooVr5lWMyQGC5qd58&ust=1759642393798000&source=images&cd=vfe&opi=89978449&ved=0CBUQjRxqFwoTCNjAqPDoiZADFQAAAAAdAAAAABAE")
                .name("Java in action")
                .category("Book")
                .description("This is a test")
                .build();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("lang", "uz");
        httpHeaders.put("Authorization", List.of("Bearer adsksafhoaisdoyuyoiasiyasdouiysadoiuasdiouyadsiouyadsiouy"));
        HttpEntity<ProductCreateDto> httpEntity = new HttpEntity<>(requestBody, httpHeaders);

        ResponseEntity<Product> response = restTemplate.exchange(remoteApiProps.getProductsApi(), HttpMethod.POST, httpEntity, Product.class);
        model.addAttribute("products", List.of(response.getBody()));
        model.addAttribute("statusCode", response.getStatusCode());
        model.addAttribute("headers", response.getHeaders());

        return "index";
    }

}
