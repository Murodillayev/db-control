package uz.pdp.dbcontrol.dto.projectagent;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectAgentDto {
    private String id;
    private String databaseUsername;
    private String databaseUrl;
    private String callbackUrl;
}
