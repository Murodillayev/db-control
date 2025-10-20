package uz.pdp.dbcontrol.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.dbcontrol.criteria.BaseCriteria;
import uz.pdp.dbcontrol.dto.DataResponse;
import uz.pdp.dbcontrol.dto.authpermission.AuthPermissionDto;
import uz.pdp.dbcontrol.dto.projectdatabase.ProjectDatabaseCreateDto;
import uz.pdp.dbcontrol.dto.projectdatabase.ProjectDatabaseDto;
import uz.pdp.dbcontrol.dto.projectdatabase.ProjectDatabaseUpdateDto;
import uz.pdp.dbcontrol.service.ProjectDatabaseService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/project-databases")
public class ProjectDatabaseController {
    private final ProjectDatabaseService service;

    public ProjectDatabaseController(ProjectDatabaseService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ProjectDatabaseDto> create(@RequestBody ProjectDatabaseCreateDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectDatabaseDto> update(@PathVariable String id, @RequestBody ProjectDatabaseUpdateDto dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDatabaseDto> get(@PathVariable String id) {
        return ResponseEntity.ok(service.get(id));
    }

    @GetMapping
    public ResponseEntity<DataResponse<List<ProjectDatabaseDto>>> getAll(BaseCriteria criteria) {
        return ResponseEntity.ok(service.getAll(criteria));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
