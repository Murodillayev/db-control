package uz.pdp.dbcontrol.model.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectAgentUpdateDto {
    private String databaseUsername;
    private String databasePassword;
    private String databaseUrl;
    private String callbackUrl;
}
