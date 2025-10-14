package uz.pdp.dbcontrol.mapper;

import org.springframework.stereotype.Component;
import uz.pdp.dbcontrol.dto.projectdatabase.ProjectDatabaseCreateDto;
import uz.pdp.dbcontrol.dto.projectdatabase.ProjectDatabaseDto;
import uz.pdp.dbcontrol.dto.projectdatabase.ProjectDatabaseUpdateDto;
import uz.pdp.dbcontrol.dto.projectdatabaseuser.ProjectDatabaseUserDto;
import uz.pdp.dbcontrol.mapper.base.BaseMapper;
import uz.pdp.dbcontrol.model.entity.ProjectAgent;
import uz.pdp.dbcontrol.model.entity.ProjectDatabase;
import uz.pdp.dbcontrol.model.entity.ProjectDatabaseUser;
import uz.pdp.dbcontrol.validation.ProjectAgentBaseValidator;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class ProjectDatabaseMapper
        implements BaseMapper<ProjectDatabaseDto, ProjectDatabaseCreateDto, ProjectDatabaseUpdateDto, ProjectDatabase> {
    private final ProjectDatabaseUserMapper projectDatabaseUserMapper;
    private final ProjectAgentBaseValidator projectAgentValidator;

    public ProjectDatabaseMapper(ProjectDatabaseUserMapper projectDatabaseUserMapper, ProjectAgentBaseValidator projectAgentValidator) {
        this.projectDatabaseUserMapper = projectDatabaseUserMapper;
        this.projectAgentValidator = projectAgentValidator;
    }

    @Override
    public ProjectDatabaseDto toDto(ProjectDatabase entity) {

        List<ProjectDatabaseUser> members = entity.getMembers();
        ProjectAgent agent = entity.getAgent();

        List<ProjectDatabaseUserDto> memberList = members.stream()
                .map(projectDatabaseUserMapper::toDto)
                .collect(Collectors.toList());

        return ProjectDatabaseDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .agentId(agent.getId())
                .members(memberList)
                .build();
    }


    @Override
    public ProjectDatabase fromCreateDto(ProjectDatabaseCreateDto dto) {
        ProjectDatabase entity = new ProjectDatabase();
        ProjectAgent agent = projectAgentValidator.existsAndGet(dto.getAgentId());
        entity.setAgent(agent);
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        return entity;
    }

    @Override
    public void fromUpdateDto(ProjectDatabaseUpdateDto dto, ProjectDatabase entity) {
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setAgent(projectAgentValidator.existsAndGet(dto.getAgentId()));
    }

}
