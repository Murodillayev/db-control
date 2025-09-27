package uz.pdp.dbcontrol.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.dbcontrol.model.dto.ProjectAgentCreateDto;
import uz.pdp.dbcontrol.model.dto.ProjectAgentDto;
import uz.pdp.dbcontrol.service.ProjectAgentService;

@RestController
@RequestMapping("/api/v1/agent")
@RequiredArgsConstructor
public class ProjectAgentController {
    private final ProjectAgentService service;

    @PostMapping
    public ResponseEntity<ProjectAgentDto> create(@RequestBody ProjectAgentCreateDto dto) {
        ProjectAgentDto response = service.create(dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
