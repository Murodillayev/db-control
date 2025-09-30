package uz.pdp.dbcontrol.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.dbcontrol.model.dto.ProjectDatabaseCreateDto;
import uz.pdp.dbcontrol.model.dto.ProjectDatabaseDto;

@RestController
@RequestMapping("/api/v1/project-database")
public class ProjectDatabaseController {


    public ResponseEntity<ProjectDatabaseDto> create(ProjectDatabaseCreateDto dto) {


        return null;
    }
}
