package uz.pdp.dbcontrol.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import uz.pdp.dbcontrol.dto.databaserole.DatabaseRoleCreateDto;
import uz.pdp.dbcontrol.dto.databaserole.DatabaseRoleDto;
import uz.pdp.dbcontrol.dto.databaserole.DatabaseRoleUpdateDto;
import uz.pdp.dbcontrol.mapper.base.BaseMapper;
import uz.pdp.dbcontrol.model.entity.DatabaseRole;

public class DatabaseRoleMapper
        implements BaseMapper<DatabaseRoleDto, DatabaseRoleCreateDto, DatabaseRoleUpdateDto, DatabaseRole> {

    @Override
    DatabaseRoleDto toDto(DatabaseRole entity){
        return DatabaseRoleDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .code(entity.getCode())
                .build();
    }

    @Override
    DatabaseRole fromCreateDto(DatabaseRoleCreateDto dto){
        return new DatabaseRole(dto.getName(), dto.getDescription(), dto.getCode());
    }

    @Override
    void fromUpdateDto(DatabaseRoleUpdateDto dto,@MappingTarget DatabaseRole entity){
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setCode(dto.getCode());
    }
}
