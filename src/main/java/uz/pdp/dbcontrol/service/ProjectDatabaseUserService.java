package uz.pdp.dbcontrol.service;

import org.springframework.stereotype.Service;
import uz.pdp.dbcontrol.dto.projectdatabaseuser.ProjectDatabaseUserCreateDto;
import uz.pdp.dbcontrol.dto.projectdatabaseuser.ProjectDatabaseUserDto;
import uz.pdp.dbcontrol.dto.projectdatabaseuser.ProjectDatabaseUserUpdateDto;
import uz.pdp.dbcontrol.mapper.ProjectDatabaseUserMapper;
import uz.pdp.dbcontrol.model.entity.ProjectDatabaseUser;
import uz.pdp.dbcontrol.repository.ProjectDatabaseUserRepository;
import uz.pdp.dbcontrol.service.base.AbstractCrudService;
import uz.pdp.dbcontrol.validation.ProjectDatabaseUserValidator;

@Service
public class ProjectDatabaseUserService extends AbstractCrudService<
        ProjectDatabaseUser,
        ProjectDatabaseUserCreateDto,
        ProjectDatabaseUserUpdateDto,
        ProjectDatabaseUserDto,
        ProjectDatabaseUserRepository,
        ProjectDatabaseUserMapper,
        ProjectDatabaseUserValidator
        > {
    public ProjectDatabaseUserService(ProjectDatabaseUserRepository repository, ProjectDatabaseUserMapper mapper, ProjectDatabaseUserValidator validator) {
        super(repository, mapper, validator);
    }
}
