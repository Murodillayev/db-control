package uz.pdp.dbcontrol.dto.authpermission;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthPermissionDto {
    private String id;
    private String name;
    private String code;
}
