package uz.pdp.dbcontrol.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDatabaseCreateDto {
    private String name;
    private String description;
    private String agentId;
    private List<String> memberIds; // List of AuthUser IDs
}




