package uz.pdp.dbcontrol.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.dbcontrol.model.dto.ProjectAgentCreateDto;
import uz.pdp.dbcontrol.model.dto.ProjectAgentResponseDto;
import uz.pdp.dbcontrol.model.dto.ProjectAgentUpdateDto;
import uz.pdp.dbcontrol.service.ProjectAgentService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/project-agent")
@RequiredArgsConstructor
public class ProjectAgentController {
    
    private final ProjectAgentService service;

    @PostMapping
    public ResponseEntity<ProjectAgentResponseDto> create(@RequestBody ProjectAgentCreateDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<ProjectAgentResponseDto>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectAgentResponseDto> getById(@PathVariable String id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectAgentResponseDto> update(
            @PathVariable String id,
            @RequestBody ProjectAgentUpdateDto dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}




