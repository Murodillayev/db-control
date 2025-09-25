package uz.pdp.dbcontrol.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
public class ProjectAgentCreateDto {
    private String databaseUsername;
    private String databasePassword;
    private String databaseUrl;
    private String callbackUrl;
}
