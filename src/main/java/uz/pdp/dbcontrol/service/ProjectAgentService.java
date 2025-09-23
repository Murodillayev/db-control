package uz.pdp.dbcontrol.service;

import org.springframework.stereotype.Service;
import uz.pdp.dbcontrol.criteria.BaseCriteria;
import uz.pdp.dbcontrol.mapper.ProjectAgentMapper;
import uz.pdp.dbcontrol.model.dto.ProjectAgentCreateDto;
import uz.pdp.dbcontrol.model.dto.ProjectAgentDto;
import uz.pdp.dbcontrol.model.dto.ProjectAgentUpdateDto;
import uz.pdp.dbcontrol.model.entity.ProjectAgent;
import uz.pdp.dbcontrol.repository.ProjectAgentRepository;
import uz.pdp.dbcontrol.service.base.AbstractService;
import uz.pdp.dbcontrol.service.base.CrudService;
import uz.pdp.dbcontrol.validator.ProjectAgentValidator;

import java.util.List;

@Service
public class ProjectAgentService
        extends AbstractService<ProjectAgentRepository, ProjectAgentMapper, ProjectAgentValidator>
        implements CrudService<ProjectAgentDto, ProjectAgentCreateDto, ProjectAgentUpdateDto, BaseCriteria, String> {

    public ProjectAgentService(ProjectAgentRepository repository, ProjectAgentMapper mapper, ProjectAgentValidator validator) {
        super(repository, mapper, validator);
    }


    @Override
    public ProjectAgentDto create(ProjectAgentCreateDto dto) {
        validator.validateOnCreate(dto);
        ProjectAgent projectAgent = mapper.fromDto(dto);
        return mapper.toDto(repository.save(projectAgent));
    }

    @Override
    public ProjectAgentDto get(String id) {
        ProjectAgent projectAgent = validator.existAndGet(id);
        return mapper.toDto(projectAgent);
    }

    @Override
    public List<ProjectAgentDto> getAll(BaseCriteria criteria) {

        return List.of();
    }

    @Override
    public ProjectAgentDto update(ProjectAgentUpdateDto dto, String id) {
        ProjectAgent projectAgent = validator.existAndGet(id);
        mapper.fromDto(dto, projectAgent);
        return mapper.toDto(repository.save(projectAgent));
    }

    @Override
    public void delete(String id) {
        ProjectAgent projectAgent = validator.existAndGet(id);
        projectAgent.setDeleted(true);
        repository.save(projectAgent);
    }
}
