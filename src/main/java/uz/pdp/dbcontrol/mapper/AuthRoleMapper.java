package uz.pdp.dbcontrol.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import uz.pdp.dbcontrol.dto.authrole.AuthRoleCreateDto;
import uz.pdp.dbcontrol.dto.authrole.AuthRoleDto;
import uz.pdp.dbcontrol.dto.authrole.AuthRoleUpdateDto;
import uz.pdp.dbcontrol.mapper.base.BaseMapper;
import uz.pdp.dbcontrol.model.entity.AuthPermission;
import uz.pdp.dbcontrol.model.entity.AuthRole;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AuthRoleMapper
        extends BaseMapper<AuthRoleDto, AuthRoleCreateDto, AuthRoleUpdateDto, AuthRole> {

    @Override
    @Mapping(
            target = "permissionIds",
            expression = "java(entity.getPermissions()!=null ? entity.getPermissions().stream().map(p -> p.getId()).toList() : null)"
    )
    AuthRoleDto toDto(AuthRole entity);

    @Mapping(source = "permissionIds", target = "permissions")
    AuthRole fromUpdateDto(AuthRoleCreateDto dto);


    default AuthPermission mapIdToAuthPermission(String id) {
        if (id == null) return null;
        AuthPermission p = new AuthPermission();
        p.setId(id);
        return p;
    }
}
