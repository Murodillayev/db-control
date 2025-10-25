package uz.pdp.dbcontrol;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import uz.pdp.dbcontrol.dto.LoginRequest;
import uz.pdp.dbcontrol.dto.LoginResponse;
import uz.pdp.dbcontrol.exception.ValidationException;
import uz.pdp.dbcontrol.service.AuthService;

// white -> red -> green

public class TDDexample {

    AuthService authService;

    @Test
    void testLogin() {
        LoginRequest request = LoginRequest.builder()
                .username(Data.POSITIVE_USERNAME)
                .password(Data.POSITIVE_PASSWORD)
                .build();

        LoginResponse response = authService.login(request);

        Assertions.assertNotNull(response.getToken());
        Assertions.assertNotNull(response.getRefreshToken());

    }

    @Test
    void shouldThrowValidationExceptionWhenUsernameDoesNotIncludeNumberAndLetters() {
        LoginRequest request = LoginRequest.builder()
                .username("admin")
                .password(Data.POSITIVE_PASSWORD)
                .build();
        // Assertion
        ValidationException validationException = Assertions.assertThrows(ValidationException.class, () -> authService.login(request));
        Assertions.assertEquals("Kamida 1ta harf 1ta raqam bolishi shart", validationException.getMessage());
    }

    //    @Test
    void shouldThrowValidationExceptionWhenPasswordIsWeak() {
        LoginRequest request = LoginRequest.builder()
                .username(Data.POSITIVE_USERNAME)
                .password("password")
                .build();

        // Assertion
        ValidationException validationException = Assertions.assertThrows(ValidationException.class, () -> authService.login(request));
        Assertions.assertEquals("Password is not strong", validationException.getMessage());

    }
}
