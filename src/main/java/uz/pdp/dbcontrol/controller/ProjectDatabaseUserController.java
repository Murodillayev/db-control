package uz.pdp.dbcontrol.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.dbcontrol.criteria.BaseCriteria;
import uz.pdp.dbcontrol.dto.DataResponse;
import uz.pdp.dbcontrol.dto.authpermission.AuthPermissionDto;
import uz.pdp.dbcontrol.dto.projectdatabaseuser.ProjectDatabaseUserCreateDto;
import uz.pdp.dbcontrol.dto.projectdatabaseuser.ProjectDatabaseUserDto;
import uz.pdp.dbcontrol.dto.projectdatabaseuser.ProjectDatabaseUserUpdateDto;
import uz.pdp.dbcontrol.service.ProjectDatabaseUserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/project-database-users")
public class ProjectDatabaseUserController {
    private final ProjectDatabaseUserService service;

    public ProjectDatabaseUserController(ProjectDatabaseUserService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ProjectDatabaseUserDto> create(@RequestBody ProjectDatabaseUserCreateDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectDatabaseUserDto> update(@PathVariable String id, @RequestBody ProjectDatabaseUserUpdateDto dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDatabaseUserDto> get(@PathVariable String id) {
        return ResponseEntity.ok(service.get(id));
    }

    @GetMapping
    public ResponseEntity<DataResponse<List<ProjectDatabaseUserDto>>> getAll(BaseCriteria criteria) {
        return ResponseEntity.ok(service.getAll(criteria));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
