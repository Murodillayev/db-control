package uz.pdp.dbcontrol.service;

import org.springframework.stereotype.Service;
import uz.pdp.dbcontrol.dto.authpermission.AuthPermissionCreateDto;
import uz.pdp.dbcontrol.dto.authpermission.AuthPermissionDto;
import uz.pdp.dbcontrol.dto.authpermission.AuthPermissionUpdateDto;
import uz.pdp.dbcontrol.mapper.AuthPermissionMapper;
import uz.pdp.dbcontrol.model.entity.AuthPermission;
import uz.pdp.dbcontrol.repository.AuthPermissionRepository;
import uz.pdp.dbcontrol.service.base.AbstractCrudService;
import uz.pdp.dbcontrol.validation.AuthPermissionValidator;

@Service
public class AuthPermissionService extends AbstractCrudService<
                AuthPermission,
                AuthPermissionCreateDto,
                AuthPermissionUpdateDto,
                AuthPermissionDto,
                AuthPermissionRepository,
                AuthPermissionMapper,
                AuthPermissionValidator
                > {
    public AuthPermissionService(AuthPermissionRepository repository, AuthPermissionMapper mapper, AuthPermissionValidator validator) {
        super(repository, mapper, validator);
    }
}
