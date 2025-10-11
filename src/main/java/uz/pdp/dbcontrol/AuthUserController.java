package uz.pdp.dbcontrol;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class AuthUserController {

    private final UserRepository repository;

    public AuthUserController(UserRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<AuthUser> create(@RequestBody Map<String, String> dto) {
        AuthUser authUser = new AuthUser();
        authUser.setUsername(dto.get("username"));
        authUser.setPassword(dto.get("password"));
        authUser.setName(dto.get("name"));
        AuthUser save = repository.save(authUser);
        return ResponseEntity.ok(save);
    }

    @GetMapping
    public ResponseEntity<List<AuthUser>> getAll() {
        List<AuthUser> all = repository.findAll();
        return ResponseEntity.ok(all);
    }

}
