package uz.pdp.dbcontrol;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import uz.pdp.dbcontrol.config.security.JwtTokenProvider;
import uz.pdp.dbcontrol.dto.LoginRequest;
import uz.pdp.dbcontrol.dto.LoginResponse;
import uz.pdp.dbcontrol.exception.ValidationException;
import uz.pdp.dbcontrol.repository.AuthRoleRepository;
import uz.pdp.dbcontrol.repository.AuthUserRepository;
import uz.pdp.dbcontrol.service.AuthService;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

    @Mock
    AuthUserRepository authUserRepository;

    @Mock
    JwtTokenProvider jwtTokenProvider;

    PasswordEncoder passwordEncoder;

    @Mock
    AuthenticationManager authenticationManager;

    @Mock
    UserDetailsService userDetailsService;

    @Mock
    AuthRoleRepository authRoleRepository;

    @InjectMocks
    AuthService authService;

    // TDD => TEST DRIVEN DEVELOPMENT

    @BeforeEach
    void setUp() {
//        MockitoAnnotations.initMocks(this);
        passwordEncoder = new BCryptPasswordEncoder();
//        authService = new AuthService(authenticationManager, jwtTokenProvider, userDetailsService, passwordEncoder, authUserRepository, authRoleRepository);
    }

    @Test
    void testLogin() {
        LoginRequest request = LoginRequest.builder()
                .username(Data.POSITIVE_USERNAME)
                .password(Data.POSITIVE_PASSWORD)
                .build();
        User mockUser = new User(request.getUsername(), request.getPassword(), Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN")));

        when(authenticationManager.authenticate(any())).thenReturn(any());
        when(userDetailsService.loadUserByUsername(request.getUsername())).thenReturn(mockUser);
        when(jwtTokenProvider.generateAccessToken(request.getUsername())).thenReturn("access_token");
        when(jwtTokenProvider.generateRefreshToken(request.getUsername())).thenReturn("refresh_token");

        LoginResponse response = authService.login(request);

        // Assertion
        assertNotNull(response.getToken());
        assertNotNull(response.getRefreshToken());

        verify(userDetailsService, times(1)).loadUserByUsername(request.getUsername());
        verify(jwtTokenProvider, times(1)).generateRefreshToken(request.getUsername());
        verify(jwtTokenProvider, times(1)).generateAccessToken(request.getUsername());
        verify(authenticationManager, times(1)).authenticate(any());

    }

    @Test
    void shouldThrowValidationExceptionWhenUsernameDoesNotIncludeNumberAndLetters() {
        LoginRequest request = LoginRequest.builder()
                .username("admin")
                .password(Data.POSITIVE_PASSWORD)
                .build();
        // Assertion
        ValidationException validationException = assertThrows(ValidationException.class, () -> authService.login(request));
        assertEquals("Kamida 1ta harf 1ta raqam bolishi shart", validationException.getMessage());
    }

//    @Test
    void shouldThrowValidationExceptionWhenPasswordIsWeak() {
        LoginRequest request = LoginRequest.builder()
                .username(Data.POSITIVE_USERNAME)
                .password("password")
                .build();

        // Assertion
        ValidationException validationException = assertThrows(ValidationException.class, () -> authService.login(request));

        assertEquals("Password is not strong", validationException.getMessage());

    }


}
