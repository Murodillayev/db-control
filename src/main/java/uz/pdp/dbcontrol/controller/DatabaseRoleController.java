package uz.pdp.dbcontrol.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.dbcontrol.model.dto.DatabaseRoleCreateDto;
import uz.pdp.dbcontrol.model.dto.DatabaseRoleResponseDto;
import uz.pdp.dbcontrol.model.dto.DatabaseRoleUpdateDto;
import uz.pdp.dbcontrol.service.DatabaseRoleService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/database-role")
@RequiredArgsConstructor
public class DatabaseRoleController {

    private final DatabaseRoleService service;

    @PostMapping
    public ResponseEntity<DatabaseRoleResponseDto> create(@RequestBody DatabaseRoleCreateDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<DatabaseRoleResponseDto>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatabaseRoleResponseDto> getById(@PathVariable String id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<DatabaseRoleResponseDto> getByCode(@PathVariable String code) {
        return ResponseEntity.ok(service.getByCode(code));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DatabaseRoleResponseDto> update(
            @PathVariable String id,
            @RequestBody DatabaseRoleUpdateDto dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}




