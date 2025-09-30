package uz.pdp.dbcontrol.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthUserSaveDto {
    private String fullName;
    private String username;
    private String password;
    private String dbUsername;
    private String dbPassword;
    private String email;
    private String phone;
    private String roleId;
}
