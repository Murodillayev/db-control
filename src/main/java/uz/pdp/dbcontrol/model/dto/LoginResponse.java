package uz.pdp.dbcontrol.model.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
public class LoginResponse {
    private String accessToken;
    private Date accessTokenExpiration;

    private String refreshToken;
    private Date refreshTokenExpiration;
}
