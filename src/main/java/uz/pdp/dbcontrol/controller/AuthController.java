package uz.pdp.dbcontrol.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.dbcontrol.dto.auth.AuthResponseDto;
import uz.pdp.dbcontrol.dto.auth.LoginDto;
import uz.pdp.dbcontrol.dto.auth.RefreshTokenDto;
import uz.pdp.dbcontrol.dto.authuser.AuthUserCreateDto;
import uz.pdp.dbcontrol.service.AuthService;


@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginDto dto){
        AuthResponseDto tokens=authService.login(dto.getUsername(), dto.getPassword());
        return ResponseEntity.ok(tokens);
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponseDto> refresh(@RequestBody RefreshTokenDto request){
        AuthResponseDto tokens=authService.refresh(request.getRefreshToken());
        return ResponseEntity.ok(tokens);
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDto> register(@RequestBody AuthUserCreateDto dto) {
        AuthResponseDto tokens = authService.register(dto);
        return ResponseEntity.ok(tokens);
    }
}
