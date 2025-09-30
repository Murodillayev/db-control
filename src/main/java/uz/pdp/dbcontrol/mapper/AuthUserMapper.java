package uz.pdp.dbcontrol.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.pdp.dbcontrol.config.CustomUserDetails;
import uz.pdp.dbcontrol.model.dto.AuthRoleDto;
import uz.pdp.dbcontrol.model.dto.AuthUserDto;
import uz.pdp.dbcontrol.model.dto.AuthUserSaveDto;
import uz.pdp.dbcontrol.model.entity.AuthRole;
import uz.pdp.dbcontrol.model.entity.AuthUser;
import uz.pdp.dbcontrol.utils.Utils;
import uz.pdp.dbcontrol.validator.AuthRoleValidator;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AuthUserMapper {

    private final AuthRoleValidator authRoleValidator;
    private final AuthUserRoleMapper authUserRoleMapper;
    private final PasswordEncoder encoder;

    public AuthUser fromDto(AuthUserSaveDto dto) {
        CustomUserDetails sessionUser = Utils.sessionUser();
        AuthUser authUser = new AuthUser();
        authUser.setCreatedAt(LocalDateTime.now());
        authUser.setCreatedBy(sessionUser.getUserId());
        fromDto(authUser, dto);
        return authUser;
    }


    public void fromDto(AuthUser authUser, AuthUserSaveDto dto) {
        CustomUserDetails sessionUser = Utils.sessionUser();
        AuthRole role = (dto.getRoleId() != null) ? authRoleValidator.existAndGet(dto.getRoleId()) : null;
        authUser.setDbPassword(dto.getDbPassword());
        authUser.setUsername(dto.getUsername());
        authUser.setEmail(dto.getEmail());
        authUser.setFullName(dto.getFullName());
        authUser.setDbUsername(dto.getDbUsername());
        authUser.setPassword(encoder.encode(dto.getPassword()));
        authUser.setEmail(dto.getEmail());
        authUser.setUpdatedAt(LocalDateTime.now());
        authUser.setUpdatedBy(sessionUser.getUserId());
        authUser.setRole(role);
        authUser.setPhone(dto.getPhone());
    }

    public List<AuthUserDto> toDto(List<AuthUser> users) {
        return users.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
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
                .fullName(authUser.getFullName())
                .email(authUser.getEmail())
                .phone(authUser.getPhone())
                .dbUsername(authUser.getDbUsername())
                .build();
    }
}
