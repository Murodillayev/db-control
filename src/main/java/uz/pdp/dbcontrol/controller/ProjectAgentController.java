package uz.pdp.dbcontrol.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
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
