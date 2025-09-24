package uz.pdp.dbcontrol.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uz.pdp.dbcontrol.dto.authrole.AuthRoleCreateDto;
import uz.pdp.dbcontrol.dto.authrole.AuthRoleDto;
import uz.pdp.dbcontrol.dto.authrole.AuthRoleUpdateDto;
import uz.pdp.dbcontrol.model.entity.AuthPermission;
import uz.pdp.dbcontrol.model.entity.AuthRole;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuthRoleMapper {

    @Mapping(
            target = "permissionIds",
            expression = "java(entity.getPermissions()!=null ? entity.getPermissions().stream().map(p -> p.getId()).toList() : null)"
    )
    AuthRoleDto toDto(AuthRole entity);

    @Mapping(source = "permissionIds", target = "permissions")
    AuthRole toEntity(AuthRoleCreateDto dto);

    @Mapping(source = "permissionIds", target = "permissions")
    AuthRole toEntity(AuthRoleUpdateDto dto);

    default List<AuthPermission> mapPermissionIdsToEntities(List<String> ids) {
        if (ids == null) return null;
        return ids.stream().map(id -> {
            AuthPermission p = new AuthPermission();
            p.setId(id);
            return p;
        }).toList();
    }
}
