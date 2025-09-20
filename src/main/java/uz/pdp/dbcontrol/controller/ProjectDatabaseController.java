package uz.pdp.dbcontrol.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/project-database")
public class ProjectDatabaseController {

    @GetMapping("/test")
    public String test() {
        return "Hello World!";
    }
}
