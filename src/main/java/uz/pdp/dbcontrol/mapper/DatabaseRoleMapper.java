package uz.pdp.dbcontrol.mapper;

import org.springframework.stereotype.Component;
import uz.pdp.dbcontrol.dto.databaserole.DatabaseRoleCreateDto;
import uz.pdp.dbcontrol.dto.databaserole.DatabaseRoleDto;
import uz.pdp.dbcontrol.dto.databaserole.DatabaseRoleUpdateDto;
import uz.pdp.dbcontrol.mapper.base.BaseMapper;
import uz.pdp.dbcontrol.model.entity.DatabaseRole;

@Component
public class DatabaseRoleMapper
        implements BaseMapper<DatabaseRoleDto, DatabaseRoleCreateDto, DatabaseRoleUpdateDto, DatabaseRole> {

    @Override
    public DatabaseRoleDto toDto(DatabaseRole entity) {
        return DatabaseRoleDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .code(entity.getCode())
                .description(entity.getDescription())
                .build();
    }

    @Override
    public DatabaseRole fromCreateDto(DatabaseRoleCreateDto dto) {
        DatabaseRole entity = new DatabaseRole();
        entity.setName(dto.getName());
        entity.setCode(dto.getCode());
        entity.setDescription(dto.getDescription());
        return entity;
    }

    @Override
    public void fromUpdateDto(DatabaseRoleUpdateDto dto, DatabaseRole entity) {
        entity.setName(dto.getName());
        entity.setCode(dto.getCode());
        entity.setDescription(dto.getDescription());
    }
}
