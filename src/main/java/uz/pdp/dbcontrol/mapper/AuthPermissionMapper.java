package uz.pdp.dbcontrol.mapper;

import org.mapstruct.Mapper;
import uz.pdp.dbcontrol.dto.authpermission.AuthPermissionCreateDto;
import uz.pdp.dbcontrol.dto.authpermission.AuthPermissionDto;
import uz.pdp.dbcontrol.dto.authpermission.AuthPermissionUpdateDto;
import uz.pdp.dbcontrol.model.entity.AuthPermission;

@Mapper(componentModel = "spring")
public interface AuthPermissionMapper {
    AuthPermissionDto toDto(AuthPermission entity);
    AuthPermission toEntity(AuthPermissionCreateDto dto);
    AuthPermission toEntity(AuthPermissionUpdateDto dto);
}
