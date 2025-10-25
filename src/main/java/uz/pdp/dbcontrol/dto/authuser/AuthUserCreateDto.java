package uz.pdp.dbcontrol.dto.authuser;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthUserCreateDto {
    private String name;
    private String username;
    private String password;
    private String dbUsername;
    private String dbPassword;
    private String email;
    private String phone;
    private String roleId;
}
