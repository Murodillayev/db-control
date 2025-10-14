package uz.pdp.dbcontrol.mapper;

import org.springframework.stereotype.Component;
import uz.pdp.dbcontrol.dto.IdNameDto;
import uz.pdp.dbcontrol.dto.projectdatabaseuser.ProjectDatabaseUserCreateDto;
import uz.pdp.dbcontrol.dto.projectdatabaseuser.ProjectDatabaseUserDto;
import uz.pdp.dbcontrol.dto.projectdatabaseuser.ProjectDatabaseUserUpdateDto;
import uz.pdp.dbcontrol.mapper.base.BaseMapper;
import uz.pdp.dbcontrol.model.entity.AuthUser;
import uz.pdp.dbcontrol.model.entity.DatabaseRole;
import uz.pdp.dbcontrol.model.entity.ProjectDatabase;
import uz.pdp.dbcontrol.model.entity.ProjectDatabaseUser;
import uz.pdp.dbcontrol.repository.DatabaseRoleRepository;
import uz.pdp.dbcontrol.validation.AuthUserValidator;
import uz.pdp.dbcontrol.validation.ProjectDatabaseValidator;

import java.util.List;

@Component
public class ProjectDatabaseUserMapper
        implements BaseMapper<
        ProjectDatabaseUserDto,
        ProjectDatabaseUserCreateDto,
        ProjectDatabaseUserUpdateDto,
        ProjectDatabaseUser> {

    private final ProjectDatabaseValidator projectDatabaseValidator;
    private final AuthUserValidator authUserValidator;
    private final DatabaseRoleRepository databaseRoleRepository;

    public ProjectDatabaseUserMapper(ProjectDatabaseValidator projectDatabaseValidator, AuthUserValidator authUserValidator, DatabaseRoleRepository databaseRoleRepository) {
        this.projectDatabaseValidator = projectDatabaseValidator;
        this.authUserValidator = authUserValidator;
        this.databaseRoleRepository = databaseRoleRepository;
    }

    @Override
    public ProjectDatabaseUserDto toDto(ProjectDatabaseUser entity) {
        List<IdNameDto> dbRoles = entity.getRoles()
                .stream()
                .map(dr -> IdNameDto.builder().id(dr.getId()).name(dr.getName()).build())
                .toList();
        return ProjectDatabaseUserDto.builder()
                .id(entity.getId())
                .roles(dbRoles)
                .name(entity.getAuthUser().getName())
                .build();
    }

    @Override
    public ProjectDatabaseUser fromCreateDto(ProjectDatabaseUserCreateDto dto) {
        ProjectDatabase projectDatabase = projectDatabaseValidator.existsAndGet(dto.getDatabaseId());
        AuthUser authUser = authUserValidator.existsAndGet(dto.getAuthUserId());
        List<DatabaseRole> databaseRoles = databaseRoleRepository.findAllByIdIn(dto.getRoleIds());

        ProjectDatabaseUser entity = new ProjectDatabaseUser();
        entity.setDatabase(projectDatabase);
        entity.setAuthUser(authUser);
        entity.setRoles(databaseRoles);
        return entity;
    }

    @Override
    public void fromUpdateDto(ProjectDatabaseUserUpdateDto dto, ProjectDatabaseUser entity) {
        List<DatabaseRole> databaseRoles = databaseRoleRepository.findAllByIdIn(dto.getRoleIds());
        entity.setRoles(databaseRoles);
    }
}
