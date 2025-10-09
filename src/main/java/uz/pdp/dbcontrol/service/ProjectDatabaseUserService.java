package uz.pdp.dbcontrol.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.dbcontrol.model.dto.*;
import uz.pdp.dbcontrol.model.entity.AuthUser;
import uz.pdp.dbcontrol.model.entity.DatabaseRole;
import uz.pdp.dbcontrol.model.entity.ProjectDatabase;
import uz.pdp.dbcontrol.model.entity.ProjectDatabaseUser;
import uz.pdp.dbcontrol.repository.AuthUserRepository;
import uz.pdp.dbcontrol.repository.DatabaseRoleRepository;
import uz.pdp.dbcontrol.repository.ProjectDatabaseRepository;
import uz.pdp.dbcontrol.repository.ProjectDatabaseUserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectDatabaseUserService {
    private final ProjectDatabaseUserRepository dbUserRepository;
    private final AuthUserRepository authUserRepository;
    private final ProjectDatabaseRepository databaseRepository;
    private final DatabaseRoleRepository roleRepository;

    public ProjectDatabaseUserResponseDto create(ProjectDatabaseUserCreateDto dto) {
        // Check if user is already assigned to this database
        if (dbUserRepository.findByAuthUserIdAndDatabaseIdAndDeletedFalse(dto.getAuthUserId(), dto.getDatabaseId()).isPresent()) {
            throw new RuntimeException("User is already assigned to this database");
        }

        // Get auth user
        AuthUser authUser = authUserRepository.findByIdAndDeletedFalse(dto.getAuthUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + dto.getAuthUserId()));

        // Get database
        ProjectDatabase database = databaseRepository.findByIdAndDeletedFalse(dto.getDatabaseId())
                .orElseThrow(() -> new RuntimeException("Database not found with id: " + dto.getDatabaseId()));


        List<DatabaseRole> roles = null;
        if (dto.getRoleIds() != null && !dto.getRoleIds().isEmpty()) {
            roles = roleRepository.findAllByIdIn(dto.getRoleIds());
            if (roles.size() != dto.getRoleIds().size()) {
                throw new RuntimeException("Some roles not found");
            }
        }

        ProjectDatabaseUser dbUser = new ProjectDatabaseUser();
        dbUser.setAuthUser(authUser);
        dbUser.setDatabase(database);
        dbUser.setRoles(roles);
        dbUser.setDeleted(false);

        ProjectDatabaseUser saved = dbUserRepository.save(dbUser);
        return mapToResponseDto(saved);
    }

    public List<ProjectDatabaseUserResponseDto> getAll() {
        return dbUserRepository.findAllByDeletedFalse()
                .stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    public ProjectDatabaseUserResponseDto getById(String id) {
        ProjectDatabaseUser dbUser = dbUserRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new RuntimeException("Database user not found with id: " + id));
        return mapToResponseDto(dbUser);
    }

    public List<ProjectDatabaseUserResponseDto> getByDatabaseId(String databaseId) {
        return dbUserRepository.findAllByDatabaseIdAndDeletedFalse(databaseId)
                .stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    public List<ProjectDatabaseUserResponseDto> getByUserId(String userId) {
        return dbUserRepository.findAllByAuthUserIdAndDeletedFalse(userId)
                .stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    public ProjectDatabaseUserResponseDto update(String id, ProjectDatabaseUserUpdateDto dto) {
        ProjectDatabaseUser dbUser = dbUserRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new RuntimeException("Database user not found with id: " + id));

        if (dto.getRoleIds() != null) {
            List<DatabaseRole> roles = roleRepository.findAllByIdIn(dto.getRoleIds());
            if (roles.size() != dto.getRoleIds().size()) {
                throw new RuntimeException("Some roles not found");
            }
            dbUser.setRoles(roles);
        }

        ProjectDatabaseUser updated = dbUserRepository.save(dbUser);
        return mapToResponseDto(updated);
    }

    public void delete(String id) {
        ProjectDatabaseUser dbUser = dbUserRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new RuntimeException("Database user not found with id: " + id));
        dbUser.setDeleted(true);
        dbUserRepository.save(dbUser);
    }

    private ProjectDatabaseUserResponseDto mapToResponseDto(ProjectDatabaseUser dbUser) {
        return ProjectDatabaseUserResponseDto.builder()
                .id(dbUser.getId())
                .user(mapUserToDto(dbUser.getAuthUser()))
                .databaseId(dbUser.getDatabase() != null ? dbUser.getDatabase().getId() : null)
                .databaseName(dbUser.getDatabase() != null ? dbUser.getDatabase().getName() : null)
                .roles(dbUser.getRoles() != null ? 
                        dbUser.getRoles().stream().map(this::mapRoleToDto).collect(Collectors.toList()) : null)
                .createdAt(dbUser.getCreatedAt())
                .updatedAt(dbUser.getUpdatedAt())
                .deleted(dbUser.getDeleted())
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

    private DatabaseRoleResponseDto mapRoleToDto(DatabaseRole role) {
        return DatabaseRoleResponseDto.builder()
                .id(role.getId())
                .name(role.getName())
                .code(role.getCode())
                .description(role.getDescription())
                .build();
    }
}




