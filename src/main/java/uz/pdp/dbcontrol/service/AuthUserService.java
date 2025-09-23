package uz.pdp.dbcontrol.service;

import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.dbcontrol.config.jwt.JwtUtil;
import uz.pdp.dbcontrol.model.dto.LoginRequest;
import uz.pdp.dbcontrol.model.dto.LoginResponse;
import uz.pdp.dbcontrol.model.dto.TokenDto;
import uz.pdp.dbcontrol.model.entity.AuthUser;
import uz.pdp.dbcontrol.repository.AuthUserRepository;

@Service
public class AuthUserService {
    private final AuthUserRepository repository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public AuthUserService(AuthUserRepository repository, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    public LoginResponse login(LoginRequest request) {

        AuthUser authUser = repository.findByUsernameAndDeletedFalse(request.getUsername())
                .orElseThrow(
                        () -> new BadCredentialsException("Bad credentials")
                );

        // match password
        if (!passwordEncoder.matches(request.getPassword(), authUser.getPassword())) {
            throw new BadCredentialsException("Bad credentials");
        }

        TokenDto accessToken = jwtUtil.generateAccessToken(authUser);
        TokenDto refreshToken = jwtUtil.generateRefreshToken(authUser);
        return LoginResponse.builder()
                .accessToken(accessToken.getToken())
                .accessTokenExpiration(accessToken.getExpiry())
                .refreshToken(refreshToken.getToken())
                .refreshTokenExpiration(refreshToken.getExpiry())
                .build();

    }

    public LoginResponse refreshToken(String refreshToken) {
        Claims claims = jwtUtil.validateTokenAndExtract("Bearer " + refreshToken);
        String username = claims.getSubject();
        AuthUser authUser = repository.findByUsernameAndDeletedFalse(username).orElseThrow(
                () -> new BadCredentialsException("Bad credentials")
        );
        TokenDto accessToken = jwtUtil.generateAccessToken(authUser);

        return LoginResponse.builder()
                .accessToken(accessToken.getToken())
                .accessTokenExpiration(accessToken.getExpiry())
                .refreshToken(refreshToken)
                .refreshTokenExpiration(claims.getExpiration())
                .build();
    }
}
