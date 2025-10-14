package uz.pdp.dbcontrol.mapper;

import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;
import uz.pdp.dbcontrol.dto.authpermission.AuthPermissionCreateDto;
import uz.pdp.dbcontrol.dto.authpermission.AuthPermissionDto;
import uz.pdp.dbcontrol.dto.authpermission.AuthPermissionUpdateDto;
import uz.pdp.dbcontrol.mapper.base.BaseMapper;
import uz.pdp.dbcontrol.model.entity.AuthPermission;
@Component
public class AuthPermissionMapper
        implements BaseMapper<AuthPermissionDto, AuthPermissionCreateDto, AuthPermissionUpdateDto, AuthPermission> {

    @Override
    AuthPermissionDto toDto(AuthPermission entity){
        return AuthPermissionDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .code(entity.getCode())
                .build();
    }

    @Override
    AuthPermission fromCreateDto(AuthPermissionCreateDto dto){
        return new AuthPermission(dto.getName(), dto.getCode());
    }

    @Override
    void fromUpdateDto(AuthPermissionUpdateDto dto, @MappingTarget AuthPermission entity){
        entity.setName(dto.getName());
        entity.setCode(dto.getCode());
    }
}
