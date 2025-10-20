package uz.pdp.dbcontrol.validation;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import uz.pdp.dbcontrol.dto.databaserole.DatabaseRoleCreateDto;
import uz.pdp.dbcontrol.dto.databaserole.DatabaseRoleUpdateDto;
import uz.pdp.dbcontrol.exception.ValidationException;
import uz.pdp.dbcontrol.model.entity.DatabaseRole;
import uz.pdp.dbcontrol.repository.DatabaseRoleRepository;

@Component
public class DatabaseRoleValidator implements Validator<
        DatabaseRoleCreateDto,
        DatabaseRoleUpdateDto,
        DatabaseRoleRepository,
        DatabaseRole> {

    @Override
    public void validateForCreate(DatabaseRoleCreateDto dto) {
        validateCommon(dto);
    }

    @Override
    public void validateForUpdate(DatabaseRoleUpdateDto dto) {
        validateCommon(dto);
    }

    private void validateCommon(Object object) {
        if (object == null)
            throw new ValidationException("Database role ma'lumotlari bo‘sh bo‘lishi mumkin emas");

        DatabaseRoleCreateDto dto = (DatabaseRoleCreateDto) object;

        if (!StringUtils.hasText(dto.getName()))
            throw new ValidationException("Database role name bo‘sh bo‘lishi mumkin emas");

        if (!StringUtils.hasText(dto.getCode()))
            throw new ValidationException("Database role code bo‘sh bo‘lishi mumkin emas");

        if (!dto.getCode().matches("^[A-Z_]+$"))
            throw new ValidationException("Database role code faqat katta harf va '_' dan iborat bo‘lishi kerak");

        // description optional, lekin agar berilgan bo‘lsa bo‘sh bo‘lmasin
        if (dto.getDescription() != null && dto.getDescription().trim().isEmpty())
            throw new ValidationException("Description bo‘sh bo‘lishi mumkin emas");
    }
}
