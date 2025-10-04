package uz.pdp.dbcontrol;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//@ToString
public class Product {
    private Integer id;
    @JsonProperty("title")
    private String name;

    private Double price;
    private String description;
    private String category;
    private String image;
    private Rating rating;


    public static class Rating {
        private Double rate;
        private Integer count;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", image='" + image + '\'' +
                ", rating=" + rating +
                '}';
    }
}
