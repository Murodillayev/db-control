package uz.pdp.dbcontrol.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import uz.pdp.dbcontrol.dto.projectagent.ProjectAgentCreateDto;
import uz.pdp.dbcontrol.dto.projectagent.ProjectAgentDto;
import uz.pdp.dbcontrol.dto.projectagent.ProjectAgentUpdateDto;
import uz.pdp.dbcontrol.mapper.base.InterfaceMapper;
import uz.pdp.dbcontrol.model.entity.ProjectAgent;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProjectAgentMapper
        extends InterfaceMapper<ProjectAgentDto, ProjectAgentCreateDto, ProjectAgentUpdateDto,ProjectAgent> {
    @Override
    ProjectAgentDto toDto(ProjectAgent entity);

    @Override
    ProjectAgent toEntityFromCreate(ProjectAgentCreateDto dto);

    @Override
    void updateEntityFromDto(ProjectAgentUpdateDto dto,@MappingTarget ProjectAgent entity);
}
