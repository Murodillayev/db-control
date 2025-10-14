package uz.pdp.dbcontrol.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import uz.pdp.dbcontrol.dto.authrole.AuthRoleCreateDto;
import uz.pdp.dbcontrol.dto.authrole.AuthRoleDto;
import uz.pdp.dbcontrol.dto.authrole.AuthRoleUpdateDto;
import uz.pdp.dbcontrol.mapper.base.InterfaceMapper;
import uz.pdp.dbcontrol.model.entity.AuthPermission;
import uz.pdp.dbcontrol.model.entity.AuthRole;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AuthRoleMapper
        extends InterfaceMapper<AuthRoleDto, AuthRoleCreateDto, AuthRoleUpdateDto, AuthRole> {

    @Override
    @Mapping(
            target = "permissionIds",
            expression = "java(entity.getPermissions()!=null ? entity.getPermissions().stream().map(p -> p.getId()).toList() : null)"
    )
    AuthRoleDto toDto(AuthRole entity);

    @Override
    @Mapping(source = "permissionIds", target = "permissions")
    AuthRole toEntityFromCreate(AuthRoleCreateDto dto);

    @Override
    @Mapping(source = "permissionIds", target = "permissions")
    void updateEntityFromDto(AuthRoleUpdateDto dto,
                             @MappingTarget AuthRole entity);

    default AuthPermission mapIdToAuthPermission(String id) {
        if (id == null) return null;
        AuthPermission p = new AuthPermission();
        p.setId(id);
        return p;
    }
}
