package uz.pdp.dbcontrol.mapper;

import org.springframework.stereotype.Component;
import uz.pdp.dbcontrol.model.dto.ProjectAgentCreateDto;
import uz.pdp.dbcontrol.model.dto.ProjectAgentDto;
import uz.pdp.dbcontrol.model.dto.ProjectAgentUpdateDto;
import uz.pdp.dbcontrol.model.entity.ProjectAgent;

@Component
public class ProjectAgentMapper {
    public ProjectAgent fromDto(ProjectAgentCreateDto dto) {
        ProjectAgent agent = new ProjectAgent();
        agent.setCallbackUrl(dto.getCallbackUrl());
        agent.setDatabaseUrl(dto.getDatabaseUrl());
        agent.setDatabaseUsername(dto.getDatabaseUsername());
        agent.setDatabasePassword(dto.getDatabasePassword());
        return agent;
    }

    public void fromDto(ProjectAgentUpdateDto dto, ProjectAgent agent) {
        agent.setCallbackUrl(dto.getCallbackUrl());
        agent.setDatabaseUrl(dto.getDatabaseUrl());
        agent.setDatabaseUsername(dto.getDatabaseUsername());
        agent.setDatabasePassword(dto.getDatabasePassword());
    }

    public ProjectAgentDto toDto(ProjectAgent save) {
        return ProjectAgentDto.builder()
                .id(save.getId())
                .callbackUrl(save.getCallbackUrl())
                .databaseUrl(save.getDatabaseUrl())
                .databaseUsername(save.getDatabaseUsername())
                .databasePassword(save.getDatabasePassword())
                .build();
    }
}
