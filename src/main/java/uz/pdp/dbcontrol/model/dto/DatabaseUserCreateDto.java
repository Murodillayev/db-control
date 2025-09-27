package uz.pdp.dbcontrol.model.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class DatabaseUserCreateDto {
    private String userId;
    private String databaseId;
    private List<String> roleIds;
}

