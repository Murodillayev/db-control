package uz.pdp.dbcontrol.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uz.pdp.dbcontrol.model.entity.AuthRole;
import uz.pdp.dbcontrol.repository.AuthRoleRepository;

@Component
@RequiredArgsConstructor
public class AuthRoleValidator {

    private final AuthRoleRepository repository;

    public AuthRole existAndGet(String roleId) {
        return repository.findById(roleId).orElseThrow(
                () -> new RuntimeException("Role with id " + roleId + " not found")
        );
    }
}
