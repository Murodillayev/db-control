package uz.pdp.dbcontrol.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.dbcontrol.models.AuthUser;
import uz.pdp.dbcontrol.models.dto.AuthUserDto;
import uz.pdp.dbcontrol.services.AuthUserService;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/user")
public class UserController {

    private final AuthUserService service;
    private final AtomicInteger counter = new AtomicInteger(0);

    public UserController(AuthUserService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<AuthUser> create() {
        AuthUser authUser = service.create(AuthUserDto.builder()
                .username(UUID.randomUUID().toString().replace("-", "").substring(0, 4))
                .email(UUID.randomUUID() + "@gmail.com")
                .name("User " + counter.incrementAndGet())
                .password(UUID.randomUUID().toString().replace("-", "").substring(0, 8))
                .build());

        return ResponseEntity.ok(authUser);

    }
}
