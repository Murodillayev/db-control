package uz.pdp.dbcontrol.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.dbcontrol.model.dto.ProjectAgentCreateDto;
import uz.pdp.dbcontrol.model.dto.ProjectAgentResponseDto;
import uz.pdp.dbcontrol.model.dto.ProjectAgentUpdateDto;
import uz.pdp.dbcontrol.model.entity.ProjectAgent;
import uz.pdp.dbcontrol.repository.ProjectAgentRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectAgentService {
    private final ProjectAgentRepository agentRepository;

    public ProjectAgentResponseDto create(ProjectAgentCreateDto dto) {
        // Check if database URL already exists
        if (agentRepository.findByDatabaseUrl(dto.getDatabaseUrl()).isPresent()) {
            throw new RuntimeException("Agent with this database URL already exists: " + dto.getDatabaseUrl());
        }

        ProjectAgent agent = new ProjectAgent();
        agent.setDatabaseUsername(dto.getDatabaseUsername());
        agent.setDatabasePassword(dto.getDatabasePassword());
        agent.setDatabaseUrl(dto.getDatabaseUrl());
        agent.setCallbackUrl(dto.getCallbackUrl());
        agent.setDeleted(false);

        ProjectAgent saved = agentRepository.save(agent);
        return mapToResponseDto(saved);
    }

    public List<ProjectAgentResponseDto> getAll() {
        return agentRepository.findAllByDeletedFalse()
                .stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    public ProjectAgentResponseDto getById(String id) {
        ProjectAgent agent = agentRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new RuntimeException("Agent not found with id: " + id));
        return mapToResponseDto(agent);
    }

    public ProjectAgentResponseDto update(String id, ProjectAgentUpdateDto dto) {
        ProjectAgent agent = agentRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new RuntimeException("Agent not found with id: " + id));

        // Check if new database URL already exists (if URL is being changed)
        if (dto.getDatabaseUrl() != null && !dto.getDatabaseUrl().equals(agent.getDatabaseUrl())) {
            if (agentRepository.findByDatabaseUrl(dto.getDatabaseUrl()).isPresent()) {
                throw new RuntimeException("Agent with this database URL already exists: " + dto.getDatabaseUrl());
            }
            agent.setDatabaseUrl(dto.getDatabaseUrl());
        }

        if (dto.getDatabaseUsername() != null) {
            agent.setDatabaseUsername(dto.getDatabaseUsername());
        }
        if (dto.getDatabasePassword() != null) {
            agent.setDatabasePassword(dto.getDatabasePassword());
        }
        if (dto.getCallbackUrl() != null) {
            agent.setCallbackUrl(dto.getCallbackUrl());
        }

        ProjectAgent updated = agentRepository.save(agent);
        return mapToResponseDto(updated);
    }

    public void delete(String id) {
        ProjectAgent agent = agentRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new RuntimeException("Agent not found with id: " + id));
        agent.setDeleted(true);
        agentRepository.save(agent);
    }

    private ProjectAgentResponseDto mapToResponseDto(ProjectAgent agent) {
        return ProjectAgentResponseDto.builder()
                .id(agent.getId())
                .databaseUsername(agent.getDatabaseUsername())
                .databaseUrl(agent.getDatabaseUrl())
                .callbackUrl(agent.getCallbackUrl())
                .createdAt(agent.getCreatedAt())
                .updatedAt(agent.getUpdatedAt())
                .deleted(agent.getDeleted())
                .build();
    }
}




