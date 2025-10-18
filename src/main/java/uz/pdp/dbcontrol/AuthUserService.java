package uz.pdp.dbcontrol;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthUserService {

    private final ApplicationEventPublisher publisher;
    private final AuthUserRepository repository;

    @Transactional
    public AuthUser create(AuthUserDto dto) {
        AuthUser authUser = new AuthUser();
        authUser.setUsername(dto.getUsername());
        authUser.setPassword(dto.getPassword());
        authUser.setEmail(dto.getEmail());
        authUser.setName(dto.getName());
        repository.save(authUser);
        publisher.publishEvent(new CreateUserEvent(this, authUser));
        return authUser;


    }
}
