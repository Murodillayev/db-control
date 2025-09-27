package uz.pdp.dbcontrol.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.pdp.dbcontrol.model.dto.AuthRoleDto;
import uz.pdp.dbcontrol.model.dto.AuthUserDto;
import uz.pdp.dbcontrol.model.dto.AuthUserSaveDto;
import uz.pdp.dbcontrol.model.entity.AuthRole;
import uz.pdp.dbcontrol.model.entity.AuthUser;
import uz.pdp.dbcontrol.validator.AuthRoleValidator;

@Component
@RequiredArgsConstructor
public class AuthUserMapper {

    private final AuthRoleValidator authRoleValidator;
    private final AuthUserRoleMapper authUserRoleMapper;
    private final PasswordEncoder encoder;

    public AuthUser fromDto(AuthUserSaveDto dto) {

        AuthRole role = (dto.getRoleId() != null) ? authRoleValidator.existAndGet(dto.getRoleId()) : null;

        AuthUser authUser = new AuthUser();
        authUser.setDbPassword(dto.getDbPassword());
        authUser.setUsername(dto.getUsername());
        authUser.setEmail(dto.getEmail());
        authUser.setDbUsername(dto.getDbUsername());
        authUser.setPassword(encoder.encode(dto.getPassword()));
        authUser.setEmail(dto.getEmail());
        authUser.setRole(role);
        authUser.setPhone(dto.getPhone());

        return authUser;
    }

    public AuthUserDto toDto(AuthUser authUser) {
        AuthRole authRole = authUser.getRole();
        AuthRoleDto authRoleDto = (authRole == null) ? null : authUserRoleMapper.toDto(authUser.getRole());
        return AuthUserDto.builder()
                .id(authUser.getId())
                .role(authRoleDto)
                .username(authUser.getUsername())
                .dbPassword(authUser.getDbPassword())
                .dbUsername(authUser.getDbUsername())
                .email(authUser.getEmail())
                .phone(authUser.getPhone())
                .dbUsername(authUser.getDbUsername())
                .build();
    }
}
