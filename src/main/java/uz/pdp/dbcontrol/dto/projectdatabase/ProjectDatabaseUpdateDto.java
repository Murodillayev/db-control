package uz.pdp.dbcontrol.dto.projectdatabase;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDatabaseUpdateDto {
    private String name;
    private String description;
    private String agentId;
    private List<String> memberIds;
}
