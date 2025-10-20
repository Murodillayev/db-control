package uz.pdp.dbcontrol.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import uz.pdp.dbcontrol.dto.projectdatabase.ProjectDatabaseCreateDto;
import uz.pdp.dbcontrol.dto.projectdatabase.ProjectDatabaseDto;
import uz.pdp.dbcontrol.dto.projectdatabase.ProjectDatabaseUpdateDto;
import uz.pdp.dbcontrol.mapper.base.InterfaceMapper;
import uz.pdp.dbcontrol.model.entity.AuthUser;
import uz.pdp.dbcontrol.model.entity.ProjectAgent;
import uz.pdp.dbcontrol.model.entity.ProjectDatabase;
import uz.pdp.dbcontrol.model.entity.ProjectDatabaseUser;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProjectDatabaseMapper
        extends InterfaceMapper<ProjectDatabaseDto, ProjectDatabaseCreateDto, ProjectDatabaseUpdateDto, ProjectDatabase> {

    @Override
    @Mapping(source = "agent.id", target = "agentId")
    @Mapping(source = "members", target = "memberIds")
    ProjectDatabaseDto toDto(ProjectDatabase entity);

    @Override
    @Mapping(source = "agentId", target = "agent")
    @Mapping(source = "memberIds", target = "members")
    ProjectDatabase toEntityFromCreate(ProjectDatabaseCreateDto dto);

    @Override
    @Mapping(source = "agentId", target = "agent")
    @Mapping(source = "memberIds", target = "members")
    void updateEntityFromDto(ProjectDatabaseUpdateDto dto,@MappingTarget ProjectDatabase entity);

    default String mapAuthUserToId(ProjectDatabaseUser projectDatabaseUser) {
        return projectDatabaseUser != null ? projectDatabaseUser.getId() : null;
    }

    default ProjectDatabaseUser mapIdToAuthUser(String id) {
        if (id == null) return null;
        ProjectDatabaseUser user = new ProjectDatabaseUser();
        user.setId(id);
        return user;
    }

    default ProjectAgent mapIdToProjectAgent(String id) {
        if (id == null) return null;
        ProjectAgent agent = new ProjectAgent();
        agent.setId(id);
        return agent;
    }
}
