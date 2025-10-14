package uz.pdp.dbcontrol.controller;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.dbcontrol.criteria.BaseCriteria;
import uz.pdp.dbcontrol.dto.AppErrorResponse;
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
    @Operation(summary = "DB yaratish uchun", description = "Bu api orqali database yaratiladi")
    @ApiResponses(value = {
//            @ApiResponse(responseCode = "201", description = "Muvaffaqiyatli yaratildi"),
            @ApiResponse(responseCode = "400", description = "Noto'g'ri so'rov (masalan, validation xatosi)",
                    content = @io.swagger.v3.oas.annotations.media.Content(
                            mediaType = "application/json",
                            schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = AppErrorResponse.class)
                    ))
    })
    @ApiResponse
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
    public ResponseEntity<List<ProjectDatabaseDto>> getAll(
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
