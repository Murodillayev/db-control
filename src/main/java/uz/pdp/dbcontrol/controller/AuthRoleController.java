package uz.pdp.dbcontrol.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.dbcontrol.criteria.BaseCriteria;
import uz.pdp.dbcontrol.dto.authrole.AuthRoleCreateDto;
import uz.pdp.dbcontrol.dto.authrole.AuthRoleDto;
import uz.pdp.dbcontrol.dto.authrole.AuthRoleUpdateDto;
import uz.pdp.dbcontrol.service.AuthRoleService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth-roles")
public class AuthRoleController {
    private final AuthRoleService service;

    public AuthRoleController(AuthRoleService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<AuthRoleDto> create(@RequestBody AuthRoleCreateDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthRoleDto> update(@PathVariable String id, @RequestBody AuthRoleUpdateDto dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthRoleDto> get(@PathVariable String id) {
        return ResponseEntity.ok(service.get(id));
    }

    @GetMapping
    public ResponseEntity<List<AuthRoleDto>> getAll(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "") String search
    ) {
        return ResponseEntity.ok(service.getAll(
                BaseCriteria.builder()
                        .search(search)
                        .page(page)
                        .size(size)
                        .build()
        ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
