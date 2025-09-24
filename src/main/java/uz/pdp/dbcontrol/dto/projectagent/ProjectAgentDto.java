package uz.pdp.dbcontrol.dto.projectagent;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectAgentDto {
    private String id;
    private String databaseUsername;
    private String databaseUrl;
    private String callbackUrl;
}
