package uz.pdp.dbcontrol.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDatabaseUserCreateDto {
    private String authUserId;
    private String databaseId;
    private List<String> roleIds; // DatabaseRole IDs
}




