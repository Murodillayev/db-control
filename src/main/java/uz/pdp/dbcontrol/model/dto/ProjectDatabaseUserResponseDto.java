package uz.pdp.dbcontrol.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDatabaseUserResponseDto {
    private String id;
    private UserResponseDto user;
    private String databaseId;
    private String databaseName;
    private List<DatabaseRoleResponseDto> roles;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Boolean deleted;
}




