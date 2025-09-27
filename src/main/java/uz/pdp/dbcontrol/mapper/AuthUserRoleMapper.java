package uz.pdp.dbcontrol.mapper;

import org.springframework.stereotype.Component;
import uz.pdp.dbcontrol.model.dto.AuthRoleDto;
import uz.pdp.dbcontrol.model.dto.IdNameDto;
import uz.pdp.dbcontrol.model.entity.AuthPermission;
import uz.pdp.dbcontrol.model.entity.AuthRole;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuthUserRoleMapper {
    public AuthRoleDto toDto(AuthRole role) {
        return AuthRoleDto.builder()
                .id(role.getId())
                .name(role.getName())
                .code(role.getCode())
                .permissions(preparePermissions(role.getPermissions()))
                .build();
    }

    private List<IdNameDto> preparePermissions(List<AuthPermission> permissions) {
        return permissions.stream()
                .map(p -> IdNameDto.builder()
                        .id(p.getId())
                        .name(p.getName())
                        .build()
                ).collect(Collectors.toList());
    }
}
