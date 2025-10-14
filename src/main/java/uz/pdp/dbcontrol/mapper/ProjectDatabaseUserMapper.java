package uz.pdp.dbcontrol.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import uz.pdp.dbcontrol.dto.projectdatabaseuser.ProjectDatabaseUserCreateDto;
import uz.pdp.dbcontrol.dto.projectdatabaseuser.ProjectDatabaseUserDto;
import uz.pdp.dbcontrol.dto.projectdatabaseuser.ProjectDatabaseUserUpdateDto;
import uz.pdp.dbcontrol.mapper.base.InterfaceMapper;
import uz.pdp.dbcontrol.model.entity.AuthUser;
import uz.pdp.dbcontrol.model.entity.DatabaseRole;
import uz.pdp.dbcontrol.model.entity.ProjectDatabase;
import uz.pdp.dbcontrol.model.entity.ProjectDatabaseUser;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProjectDatabaseUserMapper
        extends InterfaceMapper<ProjectDatabaseUserDto, ProjectDatabaseUserCreateDto, ProjectDatabaseUserUpdateDto, ProjectDatabaseUser> {
    @Override
    @Mapping(source = "authUser.id", target = "authUserId")
    @Mapping(source = "database.id", target = "databaseId")
    @Mapping(source = "roles", target = "roleIds")
    ProjectDatabaseUserDto toDto(ProjectDatabaseUser entity);

    @Override
    @Mapping(source = "authUserId", target = "authUser")
    @Mapping(source = "databaseId", target = "database")
    @Mapping(source = "roleIds", target = "roles")
    ProjectDatabaseUser toEntityFromCreate(ProjectDatabaseUserCreateDto dto);

    @Override
    @Mapping(source = "authUserId", target = "authUser")
    @Mapping(source = "databaseId", target = "database")
    @Mapping(source = "roleIds", target = "roles")
    void updateEntityFromDto(ProjectDatabaseUserUpdateDto dto, @MappingTarget ProjectDatabaseUser entity);

    default String mapAuthUserToId(AuthUser authUser) {
        return authUser != null ? authUser.getId() : null;
    }

    default String mapDatabaseToId(ProjectDatabase projectDatabase) {
        return projectDatabase != null ? projectDatabase.getId() : null;
    }

    default String mapRoleToId(DatabaseRole databaseRole) {
        return databaseRole != null ? databaseRole.getId() : null;
    }

    default AuthUser mapIdToAuthUser(String id) {
        if (id == null) return null;
        AuthUser authUser = new AuthUser();
        authUser.setId(id);
        return authUser;
    }

    default ProjectDatabase mapIdToProjectDatabase(String id) {
        if (id == null) return null;
        ProjectDatabase projectDatabase = new ProjectDatabase();
        projectDatabase.setId(id);
        return projectDatabase;
    }

    default DatabaseRole mapIdToDatabaseRole(String id) {
        if (id == null) return null;
        DatabaseRole databaseRole=new DatabaseRole();
        databaseRole.setId(id);
        return databaseRole;
    }
}
