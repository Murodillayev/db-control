package uz.pdp.dbcontrol.mapper;

import uz.pdp.dbcontrol.dto.authpermission.AuthPermissionDto;
import uz.pdp.dbcontrol.dto.authrole.AuthRoleCreateDto;
import uz.pdp.dbcontrol.dto.authrole.AuthRoleDto;
import uz.pdp.dbcontrol.dto.authrole.AuthRoleUpdateDto;
import uz.pdp.dbcontrol.mapper.base.BaseMapper;
import uz.pdp.dbcontrol.model.entity.AuthPermission;
import uz.pdp.dbcontrol.model.entity.AuthRole;

import java.util.List;
import java.util.stream.Collectors;

public class AuthRoleMapper
        implements BaseMapper<AuthRoleDto, AuthRoleCreateDto, AuthRoleUpdateDto, AuthRole> {
    private final AuthPermissionMapper authPermissionMapper;

    public AuthRoleMapper(AuthPermissionMapper authPermissionMapper) {
        this.authPermissionMapper = authPermissionMapper;
    }

    @Override
    public AuthRoleDto toDto(AuthRole entity) {
        List<AuthPermission> permissions = entity.getPermissions();
        List<AuthPermissionDto> permissionDtoList = permissions.stream()
                .map(authPermissionMapper::toDto)
                .toList();

        return AuthRoleDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .code(entity.getCode())
                .permissionIds(permissionDtoList)
                .build();
    }

    @Override
    public AuthRole fromCreateDto(AuthRoleCreateDto dto) {
        return null;
    }

    @Override
    public void fromUpdateDto(AuthRoleUpdateDto dto, AuthRole entity) {

    }
}
