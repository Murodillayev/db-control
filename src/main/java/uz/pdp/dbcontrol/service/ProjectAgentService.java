package uz.pdp.dbcontrol.service;

import org.springframework.stereotype.Service;
import uz.pdp.dbcontrol.dto.projectagent.ProjectAgentCreateDto;
import uz.pdp.dbcontrol.dto.projectagent.ProjectAgentDto;
import uz.pdp.dbcontrol.dto.projectagent.ProjectAgentUpdateDto;
import uz.pdp.dbcontrol.service.base.AbstractCrudService;
import uz.pdp.dbcontrol.mapper.ProjectAgentMapper;
import uz.pdp.dbcontrol.model.entity.ProjectAgent;
import uz.pdp.dbcontrol.repository.ProjectAgentRepository;
import uz.pdp.dbcontrol.validation.ProjectAgentBaseValidator;

@Service
public class ProjectAgentService extends AbstractCrudService<
        ProjectAgent,
        ProjectAgentCreateDto,
        ProjectAgentUpdateDto,
        ProjectAgentDto,
        ProjectAgentRepository,
        ProjectAgentMapper,
        ProjectAgentBaseValidator
        > {
    public ProjectAgentService(ProjectAgentRepository repository, ProjectAgentMapper mapper, ProjectAgentBaseValidator validator) {
        super(repository, mapper, validator);
    }
}
