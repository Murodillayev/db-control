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
import uz.pdp.dbcontrol.model.dto.*;
import uz.pdp.dbcontrol.model.entity.AuthRole;
import uz.pdp.dbcontrol.model.entity.AuthUser;
import uz.pdp.dbcontrol.repository.AuthRoleRepository;
import uz.pdp.dbcontrol.repository.AuthUserRepository;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthUserService {
    private final AuthUserRepository userRepository;
    private final AuthRoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JWTService jwtService;
    private final EmailService emailService;

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

    // CRUD Operations
    
    public UserResponseDto findById(String id) {
        AuthUser user = userRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        return mapToResponseDto(user);
    }
    
    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAllByDeletedFalse()
                .stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }
    
    public UserResponseDto findByEmail(String email) {
        AuthUser user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
        if (user.getDeleted()) {
            throw new RuntimeException("User account is deleted");
        }
        return mapToResponseDto(user);
    }
    
    public UserResponseDto createUser(CreateUserDto dto) {
        // Check if username already exists
        if (userRepository.findByUsername(dto.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists: " + dto.getUsername());
        }
        
        // Check if email already exists
        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists: " + dto.getEmail());
        }
        
        // Get role
        AuthRole role = roleRepository.findById(dto.getRoleId())
                .orElseThrow(() -> new RuntimeException("Role not found with id: " + dto.getRoleId()));
        
        // Create user
        AuthUser user = new AuthUser();
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());
        user.setDbUsername(dto.getDbUsername());
        user.setDbPassword(dto.getDbPassword());
        user.setRole(role);
        user.setDeleted(false);
        
        AuthUser savedUser = userRepository.save(user);
        
        // Send welcome email
        emailService.sendWelcomeEmail(dto.getEmail(), dto.getUsername(), dto.getPassword());
        
        return mapToResponseDto(savedUser);
    }
    
    public UserResponseDto updateUser(String id, UpdateUserDto dto) {
        AuthUser user = userRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        
        // Check if new username already exists (if username is being changed)
        if (dto.getUsername() != null && !dto.getUsername().equals(user.getUsername())) {
            if (userRepository.findByUsername(dto.getUsername()).isPresent()) {
                throw new RuntimeException("Username already exists: " + dto.getUsername());
            }
            user.setUsername(dto.getUsername());
        }
        
        // Check if new email already exists (if email is being changed)
        if (dto.getEmail() != null && !dto.getEmail().equals(user.getEmail())) {
            if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
                throw new RuntimeException("Email already exists: " + dto.getEmail());
            }
            user.setEmail(dto.getEmail());
        }
        
        // Update other fields
        if (dto.getPhone() != null) {
            user.setPhone(dto.getPhone());
        }
        if (dto.getDbUsername() != null) {
            user.setDbUsername(dto.getDbUsername());
        }
        if (dto.getDbPassword() != null) {
            user.setDbPassword(dto.getDbPassword());
        }
        if (dto.getRoleId() != null) {
            AuthRole role = roleRepository.findById(dto.getRoleId())
                    .orElseThrow(() -> new RuntimeException("Role not found with id: " + dto.getRoleId()));
            user.setRole(role);
        }
        
        AuthUser updatedUser = userRepository.save(user);
        
        // Send update notification email
        emailService.sendAccountUpdateEmail(updatedUser.getEmail(), updatedUser.getUsername());
        
        return mapToResponseDto(updatedUser);
    }
    
    public void deleteUser(String id) {
        AuthUser user = userRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        
        // Soft delete
        user.setDeleted(true);
        userRepository.save(user);
        
        // Send deletion notification email
        emailService.sendAccountDeletionEmail(user.getEmail(), user.getUsername());
    }
    
    // Helper method to map entity to DTO
    private UserResponseDto mapToResponseDto(AuthUser user) {
        return UserResponseDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .phone(user.getPhone())
                .dbUsername(user.getDbUsername())
                .roleName(user.getRole() != null ? user.getRole().getName() : null)
                .roleCode(user.getRole() != null ? user.getRole().getCode() : null)
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .deleted(user.getDeleted())
                .build();
    }
}
