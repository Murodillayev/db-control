package uz.pdp.dbcontrol.dto.authrole;

import lombok.*;
import uz.pdp.dbcontrol.dto.authpermission.AuthPermissionDto;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthRoleDto {
    private String id;
    private String name;
    private String code;
    private List<AuthPermissionDto> permissions; // nested DTO
}