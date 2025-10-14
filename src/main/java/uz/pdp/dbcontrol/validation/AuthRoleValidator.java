package uz.pdp.dbcontrol.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import uz.pdp.dbcontrol.dto.authrole.AuthRoleCreateDto;
import uz.pdp.dbcontrol.dto.authrole.AuthRoleUpdateDto;
import uz.pdp.dbcontrol.exception.NotFoundException;
import uz.pdp.dbcontrol.exception.ValidationException;
import uz.pdp.dbcontrol.model.entity.AuthRole;
import uz.pdp.dbcontrol.repository.AuthRoleRepository;

@Component
@RequiredArgsConstructor
public class AuthRoleValidator implements BaseValidator<AuthRoleCreateDto, AuthRoleUpdateDto, AuthRole> {
    private final AuthRoleRepository repository;

    @Override
    public void validateForCreate(AuthRoleCreateDto dto) {
        validateCommon(dto);
    }

    @Override
    public void validateForUpdate(AuthRoleUpdateDto dto) {
        validateCommon(dto);
    }

    private void validateCommon(Object object) {
        if (object == null)
            throw new ValidationException("Role ma'lumotlari bo‘sh bo‘lishi mumkin emas");

        AuthRoleCreateDto dto = (AuthRoleCreateDto) object;

        if (!StringUtils.hasText(dto.getName()))
            throw new ValidationException("Role name bo‘sh bo‘lishi mumkin emas");

        if (!StringUtils.hasText(dto.getCode()))
            throw new ValidationException("Role code bo‘sh bo‘lishi mumkin emas");

        if (!dto.getCode().matches("^[A-Z_]+$"))
            throw new ValidationException("Role code faqat katta harf va '_' dan iborat bo‘lishi kerak");

        if (dto.getPermissionIds() == null || dto.getPermissionIds().isEmpty())
            throw new ValidationException("Kamida bitta permission tanlanishi kerak");
    }

    @Override
    public AuthRole existsAndGet(String id) {
        return repository.findById(id).orElseThrow(
                () -> new NotFoundException("Role with id '" + id + "' not found")
        );
    }
}
