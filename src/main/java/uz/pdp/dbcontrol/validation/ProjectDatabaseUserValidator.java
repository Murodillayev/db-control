package uz.pdp.dbcontrol.validation;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import uz.pdp.dbcontrol.dto.projectdatabaseuser.ProjectDatabaseUserCreateDto;
import uz.pdp.dbcontrol.dto.projectdatabaseuser.ProjectDatabaseUserUpdateDto;
import uz.pdp.dbcontrol.exception.ValidationException;
import uz.pdp.dbcontrol.model.entity.ProjectDatabaseUser;
import uz.pdp.dbcontrol.repository.ProjectDatabaseUserRepository;

@Component
public class ProjectDatabaseUserValidator implements Validator<
        ProjectDatabaseUserCreateDto,
        ProjectDatabaseUserUpdateDto,
        ProjectDatabaseUserRepository,
        ProjectDatabaseUser
        > {

    @Override
    public void validateForCreate(ProjectDatabaseUserCreateDto dto) {
        validateCommon(dto);
    }

    @Override
    public void validateForUpdate(ProjectDatabaseUserUpdateDto dto) {
        validateCommon(dto);
    }

    private void validateCommon(Object object) {
        if (object == null)
            throw new ValidationException("Project database user ma'lumotlari bo‘sh bo‘lishi mumkin emas");

        ProjectDatabaseUserCreateDto dto = (ProjectDatabaseUserCreateDto) object;

        if (!StringUtils.hasText(dto.getAuthUserId()))
            throw new ValidationException("Auth user tanlanmagan");

        if (!StringUtils.hasText(dto.getDatabaseId()))
            throw new ValidationException("Database tanlanmagan");

        if (dto.getRoleIds() == null || dto.getRoleIds().isEmpty())
            throw new ValidationException("Kamida bitta role tanlanishi kerak");
    }
}
