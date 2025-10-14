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
public interface ProjectAgentMapper
        extends BaseMapper<ProjectAgentDto, ProjectAgentCreateDto, ProjectAgentUpdateDto,ProjectAgent> {
    @Override
    ProjectAgentDto toDto(ProjectAgent entity);

    @Override
    ProjectAgent fromCreateDto(ProjectAgentCreateDto dto);

    @Override
    void fromUpdateDto(ProjectAgentUpdateDto dto,@MappingTarget ProjectAgent entity);
}
