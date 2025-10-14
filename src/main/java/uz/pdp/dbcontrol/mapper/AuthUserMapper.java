package uz.pdp.dbcontrol.mapper;

import org.springframework.stereotype.Component;
import uz.pdp.dbcontrol.dto.IdNameDto;
import uz.pdp.dbcontrol.dto.authuser.AuthUserCreateDto;
import uz.pdp.dbcontrol.dto.authuser.AuthUserDto;
import uz.pdp.dbcontrol.dto.authuser.AuthUserUpdateDto;
import uz.pdp.dbcontrol.mapper.base.BaseMapper;
import uz.pdp.dbcontrol.model.entity.AuthRole;
import uz.pdp.dbcontrol.model.entity.AuthUser;
import uz.pdp.dbcontrol.validation.AuthRoleValidator;

@Component
public class AuthUserMapper
        implements BaseMapper<AuthUserDto, AuthUserCreateDto, AuthUserUpdateDto, AuthUser> {

    private final AuthRoleValidator authRoleValidator;

    public AuthUserMapper(AuthRoleValidator authRoleValidator) {
        this.authRoleValidator = authRoleValidator;
    }

    @Override
    public AuthUserDto toDto(AuthUser entity) {
        AuthRole role = entity.getRole();
        IdNameDto roleDto = (role == null) ? null : IdNameDto.builder()
                .id(role.getId())
                .name(role.getName())
                .build();

        return AuthUserDto.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .email(entity.getEmail())
                .role(roleDto)
                .build();
    }

    @Override
    public AuthUser fromCreateDto(AuthUserCreateDto dto) {
        AuthRole role = authRoleValidator.existsAndGet(dto.getRoleId());
        AuthUser entity = new AuthUser();
        entity.setUsername(dto.getUsername());
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setRole(role);
        return entity;
    }

    @Override
    public void fromUpdateDto(AuthUserUpdateDto dto, AuthUser entity) {
        AuthRole role = authRoleValidator.existsAndGet(dto.getRoleId());
        entity.setUsername(dto.getUsername());
        entity.setEmail(dto.getEmail());
        entity.setName(dto.getName());
        entity.setPhone(dto.getPhone());
        entity.setDbUsername(dto.getDbUsername());
        entity.setDbPassword(dto.getDbPassword());
        entity.setRole(role);
    }
}
