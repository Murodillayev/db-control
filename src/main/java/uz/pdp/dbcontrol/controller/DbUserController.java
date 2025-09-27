package uz.pdp.dbcontrol.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.dbcontrol.model.dto.DatabaseUserCreateDto;
import uz.pdp.dbcontrol.model.dto.DatabaseUserDto;
import uz.pdp.dbcontrol.service.DatabaseUserService;

@RestController
@RequestMapping("/api/v1/db-user")
@RequiredArgsConstructor
public class DbUserController {

    private final DatabaseUserService service;

    @PostMapping
    public ResponseEntity<DatabaseUserDto> create(@RequestBody DatabaseUserCreateDto dto){
        DatabaseUserDto databaseUserDto = service.create(dto);
        return ResponseEntity.ok(databaseUserDto);
    }
}
