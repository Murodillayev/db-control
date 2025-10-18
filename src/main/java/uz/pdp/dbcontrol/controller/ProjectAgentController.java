package uz.pdp.dbcontrol.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.dbcontrol.criteria.BaseCriteria;
import uz.pdp.dbcontrol.dto.projectagent.ProjectAgentCreateDto;
import uz.pdp.dbcontrol.dto.projectagent.ProjectAgentDto;
import uz.pdp.dbcontrol.dto.projectagent.ProjectAgentUpdateDto;
import uz.pdp.dbcontrol.service.ProjectAgentService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/project-agent")
public class ProjectAgentController {
    private final ProjectAgentService service;

    public ProjectAgentController(ProjectAgentService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ProjectAgentDto> create(@RequestBody ProjectAgentCreateDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectAgentDto> update(@PathVariable String id, @RequestBody ProjectAgentUpdateDto dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectAgentDto> get(@PathVariable String id) {
        return ResponseEntity.ok(service.get(id));
    }

    @GetMapping
    public ResponseEntity<List<ProjectAgentDto>> getAll(
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