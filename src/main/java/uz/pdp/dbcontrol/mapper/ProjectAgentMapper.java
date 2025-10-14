package uz.pdp.dbcontrol.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import uz.pdp.dbcontrol.dto.projectagent.ProjectAgentCreateDto;
import uz.pdp.dbcontrol.dto.projectagent.ProjectAgentDto;
import uz.pdp.dbcontrol.dto.projectagent.ProjectAgentUpdateDto;
import uz.pdp.dbcontrol.mapper.base.BaseMapper;
import uz.pdp.dbcontrol.model.entity.ProjectAgent;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public class ProjectAgentMapper
        implements BaseMapper<ProjectAgentDto, ProjectAgentCreateDto, ProjectAgentUpdateDto,ProjectAgent> {
    @Override
    public ProjectAgentDto toDto(ProjectAgent entity){
        return ProjectAgentDto.builder()
                .id(entity.getId())
                .callbackUrl(entity.getCallbackUrl())
                .databaseUrl(entity.getDatabaseUrl())
                .databaseUsername(entity.getDatabaseUsername())
                .build();
    }

    @Override
    public ProjectAgent fromCreateDto(ProjectAgentCreateDto dto){
        return new ProjectAgent(dto.getDatabaseUsername(), dto.getDatabasePassword(), dto.getDatabaseUrl(), dto.getCallbackUrl());
    }

    @Override
    public void fromUpdateDto(ProjectAgentUpdateDto dto,@MappingTarget ProjectAgent entity){
        entity.setDatabasePassword(dto.getDatabasePassword());
        entity.setDatabaseUrl(dto.getDatabaseUrl());
        entity.setCallbackUrl(dto.getCallbackUrl());
        entity.setDatabaseUsername(dto.getDatabaseUsername());
    }
}
