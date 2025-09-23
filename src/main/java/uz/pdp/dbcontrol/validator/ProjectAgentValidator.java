package uz.pdp.dbcontrol.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uz.pdp.dbcontrol.model.dto.ProjectAgentCreateDto;
import uz.pdp.dbcontrol.model.dto.ProjectAgentDto;
import uz.pdp.dbcontrol.model.entity.ProjectAgent;
import uz.pdp.dbcontrol.repository.ProjectAgentRepository;

@Component
@RequiredArgsConstructor
public class ProjectAgentValidator {

    private final ProjectAgentRepository repository;

    public void validateOnCreate(ProjectAgentCreateDto dto) {
        //TODO SOME LOGIC
    }

    public ProjectAgent existAndGet(String id) {
        return repository.findById(id).orElseThrow(
                () -> new RuntimeException("Project agent with id " + id + " not found")
        );
    }
}
