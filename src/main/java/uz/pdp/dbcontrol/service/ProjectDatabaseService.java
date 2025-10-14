package uz.pdp.dbcontrol.service;

import org.springframework.stereotype.Service;
import uz.pdp.dbcontrol.dto.projectdatabase.ProjectDatabaseCreateDto;
import uz.pdp.dbcontrol.dto.projectdatabase.ProjectDatabaseDto;
import uz.pdp.dbcontrol.dto.projectdatabase.ProjectDatabaseUpdateDto;
import uz.pdp.dbcontrol.service.base.AbstractCrudService;
import uz.pdp.dbcontrol.mapper.ProjectDatabaseMapper;
import uz.pdp.dbcontrol.model.entity.ProjectDatabase;
import uz.pdp.dbcontrol.repository.ProjectDatabaseRepository;
import uz.pdp.dbcontrol.validation.ProjectDatabaseBaseValidator;

@Service
public class ProjectDatabaseService extends AbstractCrudService<
        ProjectDatabase,
        ProjectDatabaseCreateDto,
        ProjectDatabaseUpdateDto,
        ProjectDatabaseDto,
        ProjectDatabaseRepository,
        ProjectDatabaseMapper,
        ProjectDatabaseBaseValidator
        > {
    public ProjectDatabaseService(ProjectDatabaseRepository repository, ProjectDatabaseMapper mapper, ProjectDatabaseBaseValidator validator) {
        super(repository, mapper, validator);
    }
}
