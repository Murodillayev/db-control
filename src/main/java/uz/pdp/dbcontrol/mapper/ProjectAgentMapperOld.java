package uz.pdp.dbcontrol.mapper;

import org.springframework.stereotype.Component;
import uz.pdp.dbcontrol.model.dto.ProjectAgentCreateDto;
import uz.pdp.dbcontrol.model.dto.ProjectAgentDto;
import uz.pdp.dbcontrol.model.dto.ProjectAgentUpdateDto;
import uz.pdp.dbcontrol.model.entity.ProjectAgent;
import uz.pdp.dbcontrol.utils.Utils;

import java.time.LocalDateTime;

@Component
public class ProjectAgentMapperOld {
    public ProjectAgent fromDto(ProjectAgentCreateDto dto) {
        String userId = Utils.sessionUser().getUserId();
        ProjectAgent agent = new ProjectAgent();
        agent.setCallbackUrl(dto.getCallbackUrl());
        agent.setDatabaseUrl(dto.getDatabaseUrl());
        agent.setDatabaseUsername(dto.getDatabaseUsername());
        agent.setDatabasePassword(dto.getDatabasePassword());
        agent.setCreatedAt(LocalDateTime.now());
        agent.setUpdatedAt(LocalDateTime.now());
        agent.setCreatedBy(userId);
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
