package uz.pdp.dbcontrol.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.dbcontrol.model.dto.DatabaseRoleCreateDto;
import uz.pdp.dbcontrol.model.dto.DatabaseRoleResponseDto;
import uz.pdp.dbcontrol.model.dto.DatabaseRoleUpdateDto;
import uz.pdp.dbcontrol.model.entity.DatabaseRole;
import uz.pdp.dbcontrol.repository.DatabaseRoleRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DatabaseRoleService {
    private final DatabaseRoleRepository roleRepository;

    public DatabaseRoleResponseDto create(DatabaseRoleCreateDto dto) {
        // Check if code already exists
        if (roleRepository.findByCode(dto.getCode()).isPresent()) {
            throw new RuntimeException("Role with this code already exists: " + dto.getCode());
        }

        DatabaseRole role = new DatabaseRole();
        role.setName(dto.getName());
        role.setCode(dto.getCode());
        role.setDescription(dto.getDescription());

        DatabaseRole saved = roleRepository.save(role);
        return mapToResponseDto(saved);
    }

    public List<DatabaseRoleResponseDto> getAll() {
        return roleRepository.findAll()
                .stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    public DatabaseRoleResponseDto getById(String id) {
        DatabaseRole role = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found with id: " + id));
        return mapToResponseDto(role);
    }

    public DatabaseRoleResponseDto getByCode(String code) {
        DatabaseRole role = roleRepository.findByCode(code)
                .orElseThrow(() -> new RuntimeException("Role not found with code: " + code));
        return mapToResponseDto(role);
    }

    public DatabaseRoleResponseDto update(String id, DatabaseRoleUpdateDto dto) {
        DatabaseRole role = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found with id: " + id));

        // Check if new code already exists (if code is being changed)
        if (dto.getCode() != null && !dto.getCode().equals(role.getCode())) {
            if (roleRepository.findByCode(dto.getCode()).isPresent()) {
                throw new RuntimeException("Role with this code already exists: " + dto.getCode());
            }
            role.setCode(dto.getCode());
        }

        if (dto.getName() != null) {
            role.setName(dto.getName());
        }
        if (dto.getDescription() != null) {
            role.setDescription(dto.getDescription());
        }

        DatabaseRole updated = roleRepository.save(role);
        return mapToResponseDto(updated);
    }

    public void delete(String id) {
        DatabaseRole role = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found with id: " + id));
        roleRepository.delete(role);
    }

    private DatabaseRoleResponseDto mapToResponseDto(DatabaseRole role) {
        return DatabaseRoleResponseDto.builder()
                .id(role.getId())
                .name(role.getName())
                .code(role.getCode())
                .description(role.getDescription())
                .build();
    }
}




