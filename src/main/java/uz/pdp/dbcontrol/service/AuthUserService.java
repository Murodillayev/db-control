package uz.pdp.dbcontrol.service;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

        TokenDto dto = jwtUtil.generateAccessToken(authUser);

        return LoginResponse.builder()
                .accessToken(dto.getToken())
                .accessTokenExpiration(dto.getExpiry())
                .build();

    }
}
