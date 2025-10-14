package uz.pdp.dbcontrol.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import uz.pdp.dbcontrol.dto.authuser.AuthUserCreateDto;
import uz.pdp.dbcontrol.dto.authuser.AuthUserDto;
import uz.pdp.dbcontrol.dto.authuser.AuthUserUpdateDto;
import uz.pdp.dbcontrol.mapper.base.InterfaceMapper;
import uz.pdp.dbcontrol.model.entity.AuthRole;
import uz.pdp.dbcontrol.model.entity.AuthUser;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AuthUserMapper
        extends InterfaceMapper<AuthUserDto, AuthUserCreateDto, AuthUserUpdateDto, AuthUser> {

    @Override
    @Mapping(source = "role.id", target = "roleId")
    AuthUserDto toDto(AuthUser entity);

    @Override
    @Mapping(source = "roleId", target = "role.id")
    AuthUser toEntityFromCreate(AuthUserCreateDto dto);

    @Override
    @Mapping(source = "roleId", target = "role.id")
    void updateEntityFromDto(AuthUserUpdateDto dto,@MappingTarget AuthUser entity);

    default String mapRoleToId(AuthRole role){
        return role!=null?role.getId():null;
    }

    default AuthRole mapIdToRole(String id){
        if (id==null)return null;
        AuthRole authRole=new AuthRole();
        authRole.setId(id);
        return authRole;
    }
}
