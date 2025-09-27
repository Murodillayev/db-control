package uz.pdp.dbcontrol.service;

import org.springframework.stereotype.Service;
import uz.pdp.dbcontrol.criteria.BaseCriteria;
import uz.pdp.dbcontrol.mapper.ProjectDatabaseUserMapper;
import uz.pdp.dbcontrol.model.dto.DatabaseUserCreateDto;
import uz.pdp.dbcontrol.model.dto.DatabaseUserDto;
import uz.pdp.dbcontrol.repository.ProjectDatabaseUserRepository;
import uz.pdp.dbcontrol.service.base.AbstractService;
import uz.pdp.dbcontrol.service.base.CrudService;
import uz.pdp.dbcontrol.validator.ProjectDatabaseUserValidator;

import java.util.List;

@Service
public  class DatabaseUserService
        extends AbstractService<ProjectDatabaseUserRepository, ProjectDatabaseUserMapper, ProjectDatabaseUserValidator>
        implements CrudService<
        DatabaseUserDto,
        DatabaseUserCreateDto,
        DatabaseUserCreateDto,
        BaseCriteria,
        String> {

    public DatabaseUserService(ProjectDatabaseUserRepository repository, ProjectDatabaseUserMapper mapper, ProjectDatabaseUserValidator validator) {
        super(repository, mapper, validator);
    }

    @Override
    public DatabaseUserDto create(DatabaseUserCreateDto dto) {
        return null;
    }

    @Override
    public DatabaseUserDto get(String id) {
        return null;
    }

    @Override
    public List<DatabaseUserDto> getAll(BaseCriteria criteria) {
        return List.of();
    }

    @Override
    public DatabaseUserDto update(DatabaseUserCreateDto dto, String id) {
        return null;
    }

    @Override
    public void delete(String id) {

    }
}
