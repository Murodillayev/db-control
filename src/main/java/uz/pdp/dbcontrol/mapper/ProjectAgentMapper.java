package uz.pdp.dbcontrol.mapper;

import org.mapstruct.Mapper;
import uz.pdp.dbcontrol.model.dto.ProjectAgentCreateDto;
import uz.pdp.dbcontrol.model.entity.ProjectAgent;

@Mapper(componentModel = "spring")
public interface ProjectAgentMapper {

//    ProjectAgentMapper INSTANCE = Mappers.getMapper(ProjectAgentMapper.class);

    ProjectAgent fromDto(ProjectAgentCreateDto dto);

}
