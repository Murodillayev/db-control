package uz.pdp.dbcontrol.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.dbcontrol.criteria.BaseCriteria;
import uz.pdp.dbcontrol.dto.databaserole.DatabaseRoleCreateDto;
import uz.pdp.dbcontrol.dto.databaserole.DatabaseRoleDto;
import uz.pdp.dbcontrol.dto.databaserole.DatabaseRoleUpdateDto;
import uz.pdp.dbcontrol.service.DatabaseRoleService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/database-roles")
public class DatabaseRoleController {
    private final DatabaseRoleService service;

    public DatabaseRoleController(DatabaseRoleService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<DatabaseRoleDto> create(@RequestBody DatabaseRoleCreateDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DatabaseRoleDto> update(@PathVariable String id, @RequestBody DatabaseRoleUpdateDto dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatabaseRoleDto> get(@PathVariable String id) {
        return ResponseEntity.ok(service.get(id));
    }

    @GetMapping
    public ResponseEntity<List<DatabaseRoleDto>> getAll(
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
