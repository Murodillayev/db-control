package uz.pdp.dbcontrol.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.dbcontrol.dto.authuser.AuthUserCreateDto;
import uz.pdp.dbcontrol.model.entity.AuthRole;
import uz.pdp.dbcontrol.model.entity.AuthUser;
import uz.pdp.dbcontrol.repository.AuthRoleRepository;
import uz.pdp.dbcontrol.repository.AuthUserRepository;
import uz.pdp.dbcontrol.config.security.JwtTokenProvider;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

        private final AuthenticationManager authenticationManager;
        private final JwtTokenProvider jwtTokenProvider;
        private final UserDetailsService userDetailsService;
        private final PasswordEncoder passwordEncoder;
        private final AuthUserRepository userRepository;
        private final AuthRoleRepository roleRepository;

        public AuthService(AuthenticationManager authenticationManager,
                           JwtTokenProvider jwtTokenProvider,
                           UserDetailsService userDetailsService,
                           PasswordEncoder passwordEncoder,
                           AuthUserRepository userRepository,
                           AuthRoleRepository roleRepository) {
            this.authenticationManager = authenticationManager;
            this.jwtTokenProvider = jwtTokenProvider;
            this.userDetailsService = userDetailsService;
            this.passwordEncoder = passwordEncoder;
            this.userRepository = userRepository;
            this.roleRepository = roleRepository;
        }

        public Map<String, String> register(AuthUserCreateDto dto) {
            if (userRepository.existsByUsername(dto.getUsername())) {
                throw new RuntimeException("Username already taken");
            }

            AuthRole role = roleRepository.findById(dto.getRoleId())
                    .orElseThrow(() -> new RuntimeException("Role not found"));

            String encodedPassword = passwordEncoder.encode(dto.getPassword());

            AuthUser user = new AuthUser();
            user.setUsername(dto.getUsername());
            user.setPassword(encodedPassword);
            user.setEmail(dto.getEmail());
            user.setPhone(dto.getPhone());
            user.setRole(role);
            userRepository.save(user);

            String accessToken = jwtTokenProvider.generateAccessToken(user.getUsername());
            String refreshToken = jwtTokenProvider.generateRefreshToken(user.getUsername());

            Map<String, String> tokens = new HashMap<>();
            tokens.put("accessToken", accessToken);
            tokens.put("refreshToken", refreshToken);
            return tokens;
        }

    /**
     * Foydalanuvchi username va password orqali tizimga kiradi.
     * To‘g‘ri bo‘lsa — access va refresh token qaytariladi.
     */
    public Map<String, String> login(String username, String password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        String accessToken = jwtTokenProvider.generateAccessToken(userDetails.getUsername());
        String refreshToken = jwtTokenProvider.generateRefreshToken(userDetails.getUsername());

        Map<String, String> tokens = new HashMap<>();
        tokens.put("accessToken", accessToken);
        tokens.put("refreshToken", refreshToken);
        return tokens;
    }

    /**
     * Refresh token orqali yangi access token yaratadi.
     * Token yaroqli bo‘lsa — foydalanuvchini DB’dan qayta o‘qiydi.
     */
    public Map<String, String> refresh(String refreshToken) {
        if (jwtTokenProvider.validateToken(refreshToken, true)) {
            String username = jwtTokenProvider.getUsernameFromToken(refreshToken, true);

            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if (!userDetails.isEnabled()) {
                throw new RuntimeException("User is inactive or deleted");
            }

            String newAccess = jwtTokenProvider.generateAccessToken(userDetails.getUsername());

            Map<String, String> result = new HashMap<>();
            result.put("accessToken", newAccess);
            result.put("refreshToken", refreshToken);
            return result;
        }

        throw new RuntimeException("Invalid refresh token");
    }
}
