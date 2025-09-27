package uz.pdp.dbcontrol.model.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DatabaseUserDto {

    private AuthUserDto authUser;

    private IdNameDto database;

    private List<IdNameDto> roles;
}
