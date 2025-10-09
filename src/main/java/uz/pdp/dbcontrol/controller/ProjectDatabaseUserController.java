package uz.pdp.dbcontrol.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.dbcontrol.model.dto.ProjectDatabaseUserCreateDto;
import uz.pdp.dbcontrol.model.dto.ProjectDatabaseUserResponseDto;
import uz.pdp.dbcontrol.model.dto.ProjectDatabaseUserUpdateDto;
import uz.pdp.dbcontrol.service.ProjectDatabaseUserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/db-user")
@RequiredArgsConstructor
public class ProjectDatabaseUserController {

    private final ProjectDatabaseUserService service;

    @PostMapping
    public ResponseEntity<ProjectDatabaseUserResponseDto> create(@RequestBody ProjectDatabaseUserCreateDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<ProjectDatabaseUserResponseDto>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDatabaseUserResponseDto> getById(@PathVariable String id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/database/{databaseId}")
    public ResponseEntity<List<ProjectDatabaseUserResponseDto>> getByDatabaseId(@PathVariable String databaseId) {
        return ResponseEntity.ok(service.getByDatabaseId(databaseId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ProjectDatabaseUserResponseDto>> getByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(service.getByUserId(userId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectDatabaseUserResponseDto> update(
            @PathVariable String id,
            @RequestBody ProjectDatabaseUserUpdateDto dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}




