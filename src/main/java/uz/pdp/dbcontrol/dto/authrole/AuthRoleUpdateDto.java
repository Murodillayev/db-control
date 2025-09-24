package uz.pdp.dbcontrol.dto.authrole;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthRoleUpdateDto {
    private String id;
    private String name;
    private String code;
    private List<String> permissionIds;
}
