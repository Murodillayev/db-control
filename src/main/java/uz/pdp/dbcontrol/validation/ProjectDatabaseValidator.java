package uz.pdp.dbcontrol.validation;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import uz.pdp.dbcontrol.dto.projectdatabase.ProjectDatabaseCreateDto;
import uz.pdp.dbcontrol.dto.projectdatabase.ProjectDatabaseUpdateDto;
import uz.pdp.dbcontrol.exception.ValidationException;

@Component
public class ProjectDatabaseValidator implements Validator<ProjectDatabaseCreateDto, ProjectDatabaseUpdateDto> {

    @Override
    public void validateForCreate(ProjectDatabaseCreateDto dto) {
        validateCommon(dto);
    }

    @Override
    public void validateForUpdate(ProjectDatabaseUpdateDto dto) {
        validateCommon(dto);
    }

    private void validateCommon(Object object) {
        if (object == null)
            throw new ValidationException("Project database ma'lumotlari bo‘sh bo‘lishi mumkin emas");

        ProjectDatabaseCreateDto dto = (ProjectDatabaseCreateDto) object;

        if (!StringUtils.hasText(dto.getName()))
            throw new ValidationException("Project database name bo‘sh bo‘lishi mumkin emas");

        if (dto.getDescription() != null && dto.getDescription().trim().isEmpty())
            throw new ValidationException("Description bo‘sh bo‘lishi mumkin emas");

        if (!StringUtils.hasText(dto.getAgentId()))
            throw new ValidationException("Project agent tanlanmagan");

        if (dto.getMemberIds() == null || dto.getMemberIds().isEmpty())
            throw new ValidationException("Kamida bitta member tanlanishi kerak");
    }
}
