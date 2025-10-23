package uz.pdp.dbcontrol.validation;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import uz.pdp.dbcontrol.dto.projectagent.ProjectAgentCreateDto;
import uz.pdp.dbcontrol.dto.projectagent.ProjectAgentDto;
import uz.pdp.dbcontrol.dto.projectagent.ProjectAgentUpdateDto;
import uz.pdp.dbcontrol.exception.ValidationException;
import uz.pdp.dbcontrol.model.entity.ProjectAgent;

@Component
public class ProjectAgentBaseValidator
        implements BaseValidator<ProjectAgentCreateDto, ProjectAgentUpdateDto, ProjectAgent> {

    @Override
    public void validateForCreate(ProjectAgentCreateDto dto) {
        validateCommon(dto);
    }

    @Override
    public void validateForUpdate(ProjectAgentUpdateDto dto) {
        validateCommon(dto);
    }

    private void validateCommon(Object object) {
        if (object == null)
            throw new ValidationException("Project agent ma'lumotlari bo‘sh bo‘lishi mumkin emas");

        ProjectAgentCreateDto dto = (ProjectAgentCreateDto) object;

        if (!StringUtils.hasText(dto.getUsername()))
            throw new ValidationException("Database username bo‘sh bo‘lishi mumkin emas");

        if (!StringUtils.hasText(dto.getPassword()))
            throw new ValidationException("Database password bo‘sh bo‘lishi mumkin emas");

        if (!StringUtils.hasText(dto.getDatabaseUrl()))
            throw new ValidationException("Database URL bo‘sh bo‘lishi mumkin emas");

        if (!dto.getDatabaseUrl().startsWith("jdbc:"))
            throw new ValidationException("Database URL 'jdbc:' bilan boshlanishi kerak");

//        if (!StringUtils.hasText(dto.getCallbackUrl()))
//            throw new ValidationException("Callback URL bo‘sh bo‘lishi mumkin emas");

//        if (!dto.getCallbackUrl().startsWith("http://") && !dto.getCallbackUrl().startsWith("https://"))
//            throw new ValidationException("Callback URL noto‘g‘ri formatda kiritilgan (http yoki https bilan boshlanishi kerak)");
    }


}
