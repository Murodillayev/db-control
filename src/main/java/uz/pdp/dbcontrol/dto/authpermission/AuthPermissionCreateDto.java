package uz.pdp.dbcontrol.dto.authpermission;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthPermissionCreateDto {
    private String name;
    private String code;
}

