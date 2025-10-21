package uz.pdp.dbcontrol;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class ProductSaveDto {
    private String name;
    private String description;
    private Double price;
}
