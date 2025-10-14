package uz.pdp.dbcontrol.validation;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import uz.pdp.dbcontrol.dto.authpermission.AuthPermissionCreateDto;
import uz.pdp.dbcontrol.dto.authpermission.AuthPermissionDto;
import uz.pdp.dbcontrol.dto.authpermission.AuthPermissionUpdateDto;
import uz.pdp.dbcontrol.exception.ValidationException;
import uz.pdp.dbcontrol.model.entity.AuthPermission;

@Component
public class AuthPermissionBaseValidator
        implements BaseValidator<AuthPermissionCreateDto, AuthPermissionUpdateDto, AuthPermission> {

    @Override
    public void validateForCreate(AuthPermissionCreateDto dto) {
        validateCommon(dto);
    }

    @Override
    public void validateForUpdate(AuthPermissionUpdateDto dto) {
        validateCommon(dto);
    }

    private void validateCommon(Object object) {
        if (object == null)
            throw new ValidationException("Permission ma'lumotlari bo‘sh bo‘lishi mumkin emas");

        AuthPermissionCreateDto dto = (AuthPermissionCreateDto) object;

        if (!StringUtils.hasText(dto.getName()))
            throw new ValidationException("Permission name bo‘sh bo‘lishi mumkin emas");

        if (!StringUtils.hasText(dto.getCode()))
            throw new ValidationException("Permission code bo‘sh bo‘lishi mumkin emas");

        if (!dto.getCode().matches("^[A-Z_]+$"))
            throw new ValidationException("Permission code faqat katta harf va '_' dan iborat bo‘lishi kerak");
    }
}
