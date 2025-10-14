package uz.pdp.dbcontrol.dto.authrole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthRoleDto {
    private String id;
    private String name;
    private String code;
    private List<String> permissionIds;
}
