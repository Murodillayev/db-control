package uz.pdp.dbcontrol.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AuthUserDto {
    private String id;
    private String fullName;

    private String username;
    private String dbUsername;
    private String dbPassword;
    private String email;
    private String phone;
    private AuthRoleDto role;
}
