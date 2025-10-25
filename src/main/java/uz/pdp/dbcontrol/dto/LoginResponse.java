package uz.pdp.dbcontrol.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder

public class LoginResponse {
    private String token;
    private String refreshToken;
}
