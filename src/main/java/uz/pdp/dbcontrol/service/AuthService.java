package uz.pdp.dbcontrol.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import uz.pdp.dbcontrol.config.security.CustomUserDetails;
import uz.pdp.dbcontrol.config.security.CustomUserDetailsService;
import uz.pdp.dbcontrol.config.security.JwtTokenProvider;
import uz.pdp.dbcontrol.dto.auth.AuthResponseDto;
import uz.pdp.dbcontrol.dto.authuser.AuthUserCreateDto;
import uz.pdp.dbcontrol.model.entity.AuthRole;
import uz.pdp.dbcontrol.model.entity.AuthUser;
import uz.pdp.dbcontrol.repository.AuthRoleRepository;
import uz.pdp.dbcontrol.repository.AuthUserRepository;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final CustomUserDetailsService customUserDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final AuthUserRepository userRepository;
    private final AuthRoleRepository roleRepository;

    public AuthService(AuthenticationManager authenticationManager,
                       JwtTokenProvider jwtTokenProvider,
                       CustomUserDetailsService customUserDetailsService,
                       PasswordEncoder passwordEncoder,
                       AuthUserRepository userRepository,
                       AuthRoleRepository roleRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.customUserDetailsService = customUserDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public AuthResponseDto register(AuthUserCreateDto dto) {
        if (userRepository.existsByUsername(dto.getUsername())) {
            throw new RuntimeException("Username already taken");
        }

        AuthRole role = roleRepository.findById(dto.getRoleId())
                .orElseThrow(() -> new RuntimeException("Role not found"));

        String encodedPassword = passwordEncoder.encode(dto.getPassword());

        AuthUser user = new AuthUser();
        user.setUsername(dto.getUsername());
        user.setPassword(encodedPassword);
        user.setDbUsername(dto.getDbUsername());
        user.setDbPassword(dto.getDbPassword());
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());
        user.setRole(role);
        userRepository.save(user);

        String accessToken = jwtTokenProvider.generateAccessToken(user.getUsername());
        String refreshToken = jwtTokenProvider.generateRefreshToken(user.getUsername());

        return new AuthResponseDto(accessToken, refreshToken);
    }

    public AuthResponseDto login(String username, String password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        CustomUserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

        String accessToken = jwtTokenProvider.generateAccessToken(userDetails.getUsername());
        String refreshToken = jwtTokenProvider.generateRefreshToken(userDetails.getUsername());

        return new AuthResponseDto(accessToken, refreshToken);
    }

    public AuthResponseDto refresh(String refreshToken) {
        if (jwtTokenProvider.validateToken(refreshToken, true)) {
            String username = jwtTokenProvider.getUsernameFromToken(refreshToken, true);

            CustomUserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

            if (!userDetails.isEnabled()) {
                throw new RuntimeException("User is inactive or deleted");
            }

            String newAccess = jwtTokenProvider.generateAccessToken(userDetails.getUsername());

            return new AuthResponseDto(newAccess, refreshToken);
        }

        throw new RuntimeException("Invalid refresh token");
    }
}
