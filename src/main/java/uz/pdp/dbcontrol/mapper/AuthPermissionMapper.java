package uz.pdp.dbcontrol.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import uz.pdp.dbcontrol.dto.authpermission.AuthPermissionCreateDto;
import uz.pdp.dbcontrol.dto.authpermission.AuthPermissionDto;
import uz.pdp.dbcontrol.dto.authpermission.AuthPermissionUpdateDto;
import uz.pdp.dbcontrol.mapper.base.BaseMapper;
import uz.pdp.dbcontrol.model.entity.AuthPermission;

public class AuthPermissionMapper
        implements BaseMapper<AuthPermissionDto, AuthPermissionCreateDto, AuthPermissionUpdateDto, AuthPermission> {


    @Override
    public AuthPermissionDto toDto(AuthPermission entity) {
        return AuthPermissionDto.builder()
                .name(entity.getName())
                .code(entity.getCode())
                .build();
    }

    @Override
    public AuthPermission fromCreateDto(AuthPermissionCreateDto dto) {
        AuthPermission authPermission = new AuthPermission();
        authPermission.setName(dto.getName());
        authPermission.setCode(dto.getCode());
        return authPermission;
    }

    @Override
    public void fromUpdateDto(AuthPermissionUpdateDto dto, AuthPermission entity) {
        entity.setName(dto.getName());
        entity.setCode(dto.getCode());
    }
}
