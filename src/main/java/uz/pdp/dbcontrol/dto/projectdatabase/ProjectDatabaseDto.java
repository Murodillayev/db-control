package uz.pdp.dbcontrol.dto.projectdatabase;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.dbcontrol.dto.projectdatabaseuser.ProjectDatabaseUserDto;
import uz.pdp.dbcontrol.model.entity.ProjectDatabaseUser;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectDatabaseDto {
    private String id;
    private String name;
    private String description;
    private String agentId;
    private List<ProjectDatabaseUserDto> members;
}
