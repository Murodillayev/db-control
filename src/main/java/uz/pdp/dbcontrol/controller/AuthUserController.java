package uz.pdp.dbcontrol.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.dbcontrol.model.dto.*;
import uz.pdp.dbcontrol.model.entity.AuthUser;
import uz.pdp.dbcontrol.service.AuthUserService;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthUserController {

    private final AuthUserService service;

    // public
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
//        return new ResponseEntity<>(service.login(request), HttpStatus.OK);
        return ResponseEntity.ok(service.login(request));
    }

    //
    @PostMapping("/refresh-token")
    public ResponseEntity<LoginResponse> refreshToken(@RequestParam String refreshToken) {
        return ResponseEntity.ok(service.refreshToken(refreshToken));
    }

    @PostMapping
    public ResponseEntity<AuthUserDto> create(@RequestBody AuthUserSaveDto dto) {
        AuthUserDto authUserDto = service.create(dto);
        return ResponseEntity.ok(authUserDto);
    }
}