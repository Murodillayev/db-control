package uz.pdp.dbcontrol.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uz.pdp.dbcontrol.model.entity.AuthUser;
import uz.pdp.dbcontrol.repository.AuthUserRepository;

@Component
@RequiredArgsConstructor
public class AuthUserValidator {

    private final AuthUserRepository repository;

    public AuthUser existAndGet(String id) {
        return repository.findById(id).orElseThrow(
                () -> new RuntimeException("User with id " + id + " not found")
        );
    }
}
