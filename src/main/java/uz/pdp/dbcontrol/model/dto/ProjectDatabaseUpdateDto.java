package uz.pdp.dbcontrol.model.dto;

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




