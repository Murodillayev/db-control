package uz.pdp.dbcontrol.service;

import org.springframework.stereotype.Service;
import uz.pdp.dbcontrol.dto.authrole.AuthRoleCreateDto;
import uz.pdp.dbcontrol.dto.authrole.AuthRoleDto;
import uz.pdp.dbcontrol.dto.authrole.AuthRoleUpdateDto;
import uz.pdp.dbcontrol.mapper.AuthRoleMapper;
import uz.pdp.dbcontrol.model.entity.AuthRole;
import uz.pdp.dbcontrol.repository.AuthRoleRepository;
import uz.pdp.dbcontrol.service.base.AbstractCrudService;
import uz.pdp.dbcontrol.validation.AuthRoleValidator;

@Service
public class AuthRoleService extends AbstractCrudService<
                AuthRole,
                AuthRoleCreateDto,
                AuthRoleUpdateDto,
                AuthRoleDto,
                AuthRoleRepository,
                AuthRoleMapper,
                AuthRoleValidator
                > {

    public AuthRoleService(AuthRoleRepository repository, AuthRoleMapper mapper, AuthRoleValidator validator) {
        super(repository, mapper, validator);
    }
}
