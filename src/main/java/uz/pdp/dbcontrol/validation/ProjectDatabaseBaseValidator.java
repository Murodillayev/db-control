package uz.pdp.dbcontrol.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import uz.pdp.dbcontrol.dto.projectdatabase.ProjectDatabaseCreateDto;
import uz.pdp.dbcontrol.dto.projectdatabase.ProjectDatabaseDto;
import uz.pdp.dbcontrol.dto.projectdatabase.ProjectDatabaseUpdateDto;
import uz.pdp.dbcontrol.exception.NotFoundException;
import uz.pdp.dbcontrol.exception.ValidationException;
import uz.pdp.dbcontrol.model.entity.ProjectDatabase;
import uz.pdp.dbcontrol.repository.ProjectDatabaseRepository;

@Component
@RequiredArgsConstructor
public class ProjectDatabaseBaseValidator
        implements BaseValidator<ProjectDatabaseCreateDto, ProjectDatabaseUpdateDto, ProjectDatabase> {

    private final ProjectDatabaseRepository repository;

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

    }

    @Override
    public ProjectDatabase existsAndGet(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Project database '" + id + "' not found"));
    }
}
