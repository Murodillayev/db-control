package uz.pdp.dbcontrol.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class AuthRoleDto {
    private String id;
    private String name;
    private String code;
    private List<IdNameDto> permissions;
}
