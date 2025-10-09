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
public class ProjectDatabaseResponseDto {
    private String id;
    private String name;
    private String description;
    private ProjectAgentResponseDto agent;
    private List<UserResponseDto> members;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Boolean deleted;
}




