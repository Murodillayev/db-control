package uz.pdp.dbcontrol.service;

import org.springframework.stereotype.Service;
import uz.pdp.dbcontrol.criteria.BaseCriteria;
import uz.pdp.dbcontrol.dto.databaserole.DatabaseRoleCreateDto;
import uz.pdp.dbcontrol.dto.databaserole.DatabaseRoleDto;
import uz.pdp.dbcontrol.dto.databaserole.DatabaseRoleUpdateDto;
import uz.pdp.dbcontrol.service.base.AbstractCrudService;
import uz.pdp.dbcontrol.mapper.DatabaseRoleMapper;
import uz.pdp.dbcontrol.model.entity.DatabaseRole;
import uz.pdp.dbcontrol.repository.DatabaseRoleRepository;
import uz.pdp.dbcontrol.validation.DatabaseRoleBaseValidator;

@Service
public class DatabaseRoleService extends AbstractCrudService<
        DatabaseRole,
        DatabaseRoleCreateDto,
        DatabaseRoleUpdateDto,
        DatabaseRoleDto,
        BaseCriteria,
        DatabaseRoleRepository,
        DatabaseRoleMapper,
        DatabaseRoleBaseValidator
        > {
    public DatabaseRoleService(DatabaseRoleRepository repository, DatabaseRoleMapper mapper, DatabaseRoleBaseValidator validator) {
        super(repository, mapper, validator);
    }
}
