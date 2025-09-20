package uz.pdp.dbcontrol.controller;


import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.dbcontrol.model.dto.LoginRequest;
import uz.pdp.dbcontrol.model.dto.LoginResponse;
import uz.pdp.dbcontrol.service.AuthUserService;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthUserController {

    private final AuthUserService service;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
//        return new ResponseEntity<>(service.login(request), HttpStatus.OK);
        return ResponseEntity.ok(service.login(request));
    }
}
