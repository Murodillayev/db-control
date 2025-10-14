package uz.pdp.dbcontrol.dto.databaserole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DatabaseRoleDto {
    private String id;
    private String name;
    private String code;
    private String description;
}
