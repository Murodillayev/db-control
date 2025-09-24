package uz.pdp.dbcontrol.dto.projectdatabaseuser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDatabaseUserDto {
    private String id;
    private String authUserId;
    private String databaseId;
    private List<String> roleIds;
}
