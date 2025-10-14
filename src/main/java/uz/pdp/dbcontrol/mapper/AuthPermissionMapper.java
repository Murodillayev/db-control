package uz.pdp.dbcontrol.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import uz.pdp.dbcontrol.dto.authpermission.AuthPermissionCreateDto;
import uz.pdp.dbcontrol.dto.authpermission.AuthPermissionDto;
import uz.pdp.dbcontrol.dto.authpermission.AuthPermissionUpdateDto;
import uz.pdp.dbcontrol.mapper.base.BaseMapper;
import uz.pdp.dbcontrol.model.entity.AuthPermission;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AuthPermissionMapper
        extends BaseMapper<AuthPermissionDto, AuthPermissionCreateDto, AuthPermissionUpdateDto, AuthPermission> {

    @Override
    AuthPermissionDto toDto(AuthPermission entity);

    @Override
    AuthPermission fromCreateDto(AuthPermissionCreateDto dto);

    @Override
    void fromUpdateDto(AuthPermissionUpdateDto dto,
                             @MappingTarget AuthPermission entity);
}
