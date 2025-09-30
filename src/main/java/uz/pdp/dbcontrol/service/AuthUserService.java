package uz.pdp.dbcontrol.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.dbcontrol.model.dto.LoginRequest;
import uz.pdp.dbcontrol.model.dto.LoginResponse;
import uz.pdp.dbcontrol.model.dto.TokenDto;
import uz.pdp.dbcontrol.model.entity.AuthUser;
import uz.pdp.dbcontrol.repository.AuthUserRepository;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthUserService {
    private final AuthUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JWTService jwtService;

    public LoginResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        AuthUser user = userRepository.findByUsername(request.getUsername()).orElseThrow(() ->
                new RuntimeException("User not found!")
        );

        String accessToken = jwtService.generateToken(userDetails);
        Date expiration = jwtService.extractExpiration(accessToken);
        String refreshToken = jwtService.generateRefreshToken(userDetails, user.getId());
        Date refreshTokenExp = jwtService.extractExpiration(refreshToken);
        return new LoginResponse(accessToken, expiration, refreshToken, refreshTokenExp);
    }

    public LoginResponse refreshAccesToken(String token) {
        if (jwtService.isTokenExpired(token)) {
            throw new RuntimeException("Refresh token expired");
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(jwtService.extractUsername(token));
        String accessToken = jwtService.generateToken(userDetails);
        Date accessTokenExp=jwtService.extractExpiration(accessToken);
        Date refreshTokenExp=jwtService.extractExpiration(token);
        return new LoginResponse(accessToken,accessTokenExp,token,refreshTokenExp);
    }

    public List<AuthUser> getAllUsers() {
        return userRepository.findAll();
    }
}
