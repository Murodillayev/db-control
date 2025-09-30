package uz.pdp.dbcontrol.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.dbcontrol.criteria.BaseCriteria;
import uz.pdp.dbcontrol.model.dto.*;
import uz.pdp.dbcontrol.service.AuthUserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthUserController {

    private final AuthUserService service;

    // public
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
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

    @GetMapping("/{id}")
    public ResponseEntity<AuthUserDto> get(@PathVariable String id) {
        AuthUserDto authUserDto = service.get(id);
        return ResponseEntity.ok(authUserDto);
    }

    @GetMapping
    public ResponseEntity<DataResponse<List<AuthUserDto>>> getAll(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "") String search
    ) {
        DataResponse<List<AuthUserDto>> response = service.getAll(
                BaseCriteria.builder()
                        .size(size)
                        .page(page)
                        .search(search)
                        .build()
        );

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthUserDto> update(@RequestBody AuthUserSaveDto dto, @PathVariable String id) {
        AuthUserDto authUserDto = service.update(dto, id);
        return ResponseEntity.ok(authUserDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}