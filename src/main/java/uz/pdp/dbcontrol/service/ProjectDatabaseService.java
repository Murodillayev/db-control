package uz.pdp.dbcontrol.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.dbcontrol.model.dto.*;
import uz.pdp.dbcontrol.model.entity.AuthUser;
import uz.pdp.dbcontrol.model.entity.ProjectAgent;
import uz.pdp.dbcontrol.model.entity.ProjectDatabase;
import uz.pdp.dbcontrol.repository.AuthUserRepository;
import uz.pdp.dbcontrol.repository.ProjectAgentRepository;
import uz.pdp.dbcontrol.repository.ProjectDatabaseRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectDatabaseService {
    private final ProjectDatabaseRepository databaseRepository;
    private final ProjectAgentRepository agentRepository;
    private final AuthUserRepository userRepository;

    public ProjectDatabaseResponseDto create(ProjectDatabaseCreateDto dto) {
        // Check if database name already exists
        if (databaseRepository.findByName(dto.getName()).isPresent()) {
            throw new RuntimeException("Database name already exists: " + dto.getName());
        }

        // Get agent
        ProjectAgent agent = null;
        if (dto.getAgentId() != null) {
            agent = agentRepository.findByIdAndDeletedFalse(dto.getAgentId())
                    .orElseThrow(() -> new RuntimeException("Agent not found with id: " + dto.getAgentId()));
        }

        // Get members
        List<AuthUser> members = null;
        if (dto.getMemberIds() != null && !dto.getMemberIds().isEmpty()) {
            members = dto.getMemberIds().stream()
                    .map(id -> userRepository.findByIdAndDeletedFalse(id)
                            .orElseThrow(() -> new RuntimeException("User not found with id: " + id)))
                    .collect(Collectors.toList());
        }

        // Create database
        ProjectDatabase database = new ProjectDatabase();
        database.setName(dto.getName());
        database.setDescription(dto.getDescription());
        database.setAgent(agent);
        database.setMembers(members);
        database.setDeleted(false);

        ProjectDatabase saved = databaseRepository.save(database);
        return mapToResponseDto(saved);
    }

    public List<ProjectDatabaseResponseDto> getAll() {
        return databaseRepository.findAllByDeletedFalse()
                .stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    public ProjectDatabaseResponseDto getById(String id) {
        ProjectDatabase database = databaseRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new RuntimeException("Database not found with id: " + id));
        return mapToResponseDto(database);
    }

    public ProjectDatabaseResponseDto update(String id, ProjectDatabaseUpdateDto dto) {
        ProjectDatabase database = databaseRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new RuntimeException("Database not found with id: " + id));

        // Check if new name already exists (if name is being changed)
        if (dto.getName() != null && !dto.getName().equals(database.getName())) {
            if (databaseRepository.findByName(dto.getName()).isPresent()) {
                throw new RuntimeException("Database name already exists: " + dto.getName());
            }
            database.setName(dto.getName());
        }

        if (dto.getDescription() != null) {
            database.setDescription(dto.getDescription());
        }

        if (dto.getAgentId() != null) {
            ProjectAgent agent = agentRepository.findByIdAndDeletedFalse(dto.getAgentId())
                    .orElseThrow(() -> new RuntimeException("Agent not found with id: " + dto.getAgentId()));
            database.setAgent(agent);
        }

        if (dto.getMemberIds() != null) {
            List<AuthUser> members = dto.getMemberIds().stream()
                    .map(memberId -> userRepository.findByIdAndDeletedFalse(memberId)
                            .orElseThrow(() -> new RuntimeException("User not found with id: " + memberId)))
                    .collect(Collectors.toList());
            database.setMembers(members);
        }

        ProjectDatabase updated = databaseRepository.save(database);
        return mapToResponseDto(updated);
    }

    public void delete(String id) {
        ProjectDatabase database = databaseRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new RuntimeException("Database not found with id: " + id));
        database.setDeleted(true);
        databaseRepository.save(database);
    }

    private ProjectDatabaseResponseDto mapToResponseDto(ProjectDatabase database) {
        return ProjectDatabaseResponseDto.builder()
                .id(database.getId())
                .name(database.getName())
                .description(database.getDescription())
                .agent(database.getAgent() != null ? mapAgentToDto(database.getAgent()) : null)
                .members(database.getMembers() != null ? 
                        database.getMembers().stream().map(this::mapUserToDto).collect(Collectors.toList()) : null)
                .createdAt(database.getCreatedAt())
                .updatedAt(database.getUpdatedAt())
                .deleted(database.getDeleted())
                .build();
    }

    private ProjectAgentResponseDto mapAgentToDto(ProjectAgent agent) {
        return ProjectAgentResponseDto.builder()
                .id(agent.getId())
                .databaseUsername(agent.getDatabaseUsername())
                .databaseUrl(agent.getDatabaseUrl())
                .callbackUrl(agent.getCallbackUrl())
                .createdAt(agent.getCreatedAt())
                .updatedAt(agent.getUpdatedAt())
                .deleted(agent.getDeleted())
                .build();
    }

    private UserResponseDto mapUserToDto(AuthUser user) {
        return UserResponseDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .phone(user.getPhone())
                .dbUsername(user.getDbUsername())
                .roleName(user.getRole() != null ? user.getRole().getName() : null)
                .roleCode(user.getRole() != null ? user.getRole().getCode() : null)
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .deleted(user.getDeleted())
                .build();
    }
}




