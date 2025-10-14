package uz.pdp.dbcontrol.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import uz.pdp.dbcontrol.dto.databaserole.DatabaseRoleCreateDto;
import uz.pdp.dbcontrol.dto.databaserole.DatabaseRoleDto;
import uz.pdp.dbcontrol.dto.databaserole.DatabaseRoleUpdateDto;
import uz.pdp.dbcontrol.mapper.base.InterfaceMapper;
import uz.pdp.dbcontrol.model.entity.DatabaseRole;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DatabaseRoleMapper
        extends InterfaceMapper<DatabaseRoleDto, DatabaseRoleCreateDto, DatabaseRoleUpdateDto, DatabaseRole> {

    @Override
    DatabaseRoleDto toDto(DatabaseRole entity);

    @Override
    DatabaseRole toEntityFromCreate(DatabaseRoleCreateDto dto);

    @Override
    void updateEntityFromDto(DatabaseRoleUpdateDto dto,@MappingTarget DatabaseRole entity);
}
