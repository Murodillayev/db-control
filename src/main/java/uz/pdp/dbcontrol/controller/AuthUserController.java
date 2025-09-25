package uz.pdp.dbcontrol.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.dbcontrol.model.dto.LoginRequest;
import uz.pdp.dbcontrol.model.dto.LoginResponse;
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
}

// mapstruct
// properties yml
// Async

// dto -> model
// model -> dto