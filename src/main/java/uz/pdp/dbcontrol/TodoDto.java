package uz.pdp.dbcontrol;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TodoDto {
    private Integer id;
    private String title;
    private String description;
    private Boolean completed;

}
