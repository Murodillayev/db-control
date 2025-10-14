package uz.pdp.dbcontrol.service;

import org.springframework.stereotype.Service;
import uz.pdp.dbcontrol.criteria.BaseCriteria;
import uz.pdp.dbcontrol.dto.projectdatabase.ProjectDatabaseCreateDto;
import uz.pdp.dbcontrol.dto.projectdatabase.ProjectDatabaseDto;
import uz.pdp.dbcontrol.dto.projectdatabase.ProjectDatabaseUpdateDto;
import uz.pdp.dbcontrol.service.base.AbstractCrudService;
import uz.pdp.dbcontrol.mapper.ProjectDatabaseMapper;
import uz.pdp.dbcontrol.model.entity.ProjectDatabase;
import uz.pdp.dbcontrol.repository.ProjectDatabaseRepository;
import uz.pdp.dbcontrol.validation.ProjectDatabaseValidator;

@Service
public class ProjectDatabaseService extends AbstractCrudService<
        ProjectDatabase,
        ProjectDatabaseCreateDto,
        ProjectDatabaseUpdateDto,
        ProjectDatabaseDto,
        BaseCriteria,
        ProjectDatabaseRepository,
        ProjectDatabaseMapper,
        ProjectDatabaseValidator
        > {
    public ProjectDatabaseService(ProjectDatabaseRepository repository, ProjectDatabaseMapper mapper, ProjectDatabaseValidator validator) {
        super(repository, mapper, validator);
    }
}
