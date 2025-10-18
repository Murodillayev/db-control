package uz.pdp.dbcontrol;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AuthUserDto {
    private String name;
    private String username;
    private String password;
    private String email;
}
