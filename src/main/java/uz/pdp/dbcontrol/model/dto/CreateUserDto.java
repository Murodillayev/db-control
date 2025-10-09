package uz.pdp.dbcontrol.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserDto {
    private String username;
    private String password;
    private String email;
    private String phone;
    private String dbUsername;
    private String dbPassword;
    private String roleId;
}




