package uz.pdp.dbcontrol.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.dbcontrol.criteria.BaseCriteria;
import uz.pdp.dbcontrol.dto.DataResponse;
import uz.pdp.dbcontrol.dto.authpermission.AuthPermissionDto;
import uz.pdp.dbcontrol.dto.authuser.AuthUserCreateDto;
import uz.pdp.dbcontrol.dto.authuser.AuthUserDto;
import uz.pdp.dbcontrol.dto.authuser.AuthUserUpdateDto;
import uz.pdp.dbcontrol.service.AuthUserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth-users")
public class AuthUserController {
    private final AuthUserService service;

    public AuthUserController(AuthUserService authUserService) {
        this.service = authUserService;
    }

    @PostMapping
    public ResponseEntity<AuthUserDto> create(@RequestBody AuthUserCreateDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthUserDto> update(@PathVariable String id, @RequestBody AuthUserUpdateDto dto){
        return ResponseEntity.ok(service.update(id,dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthUserDto> get(@PathVariable String id){
        return ResponseEntity.ok(service.get(id));
    }

    @GetMapping
    public ResponseEntity<DataResponse<List<AuthUserDto>>> getAll(BaseCriteria criteria) {
        return ResponseEntity.ok(service.getAll(criteria));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
