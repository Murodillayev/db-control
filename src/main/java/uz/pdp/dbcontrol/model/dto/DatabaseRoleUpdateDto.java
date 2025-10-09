package uz.pdp.dbcontrol.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DatabaseRoleUpdateDto {
    private String name;
    private String code;
    private String description;
}




