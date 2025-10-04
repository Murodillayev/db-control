package uz.pdp.dbcontrol;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDto {
    private Integer id;
    @JsonProperty("title")
    private String name;
    private Double price;
    private String description;
    private String category;
    private String image;
}
