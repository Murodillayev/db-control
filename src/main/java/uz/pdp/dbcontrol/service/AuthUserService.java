package uz.pdp.dbcontrol.service;

import org.springframework.stereotype.Service;
import uz.pdp.dbcontrol.dto.authuser.AuthUserCreateDto;
import uz.pdp.dbcontrol.dto.authuser.AuthUserDto;
import uz.pdp.dbcontrol.dto.authuser.AuthUserUpdateDto;
import uz.pdp.dbcontrol.mapper.AuthUserMapper;
import uz.pdp.dbcontrol.model.entity.AuthUser;
import uz.pdp.dbcontrol.repository.AuthUserRepository;
import uz.pdp.dbcontrol.service.base.AbstractCrudService;
import uz.pdp.dbcontrol.validation.AuthUserValidator;

@Service
public class AuthUserService extends AbstractCrudService<
                AuthUser,
                AuthUserCreateDto,
                AuthUserUpdateDto,
                AuthUserDto,
                AuthUserRepository,
                AuthUserMapper,
        AuthUserValidator
                > {
    public AuthUserService(AuthUserRepository repository, AuthUserMapper mapper, AuthUserValidator validator) {
        super(repository, mapper, validator);
    }
}
