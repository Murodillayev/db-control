package uz.pdp.dbcontrol.dto.authuser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthUserDto {
    private String id;
    private String username;
    private String email;
    private String phone;
    private String roleId;
}
