package uz.pdp.dbcontrol.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.dbcontrol.exception.ValidationException;

@Data
@Builder
public class LoginRequest {
    private String username;
    private String password;

    public void validate() {

        if (!username.matches("^(?=.*[a-zA-Z])(?=.*\\d).+$")){
            throw new ValidationException("Kamida 1ta harf 1ta raqam bolishi shart");
        }
//        if (!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[_!@#$])[A-Za-z\\d_!@#$]{12,}$")){
//            throw new ValidationException("Password is not strong");
//        }
    }
}
