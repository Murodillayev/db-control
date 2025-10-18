package uz.pdp.dbcontrol;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthUserService {

    private final EmailNotifyService emailNotifyService;
    private final MVRefresher refresher;
    private final TelegramNotifyService telegramNotifyService;
    private final AuthUserRepository repository;


    public AuthUser create(AuthUserDto dto) {
        AuthUser authUser = new AuthUser();
        authUser.setUsername(dto.getUsername());
        authUser.setPassword(dto.getPassword());
        authUser.setEmail(dto.getEmail());
        authUser.setName(dto.getName());

        // send notify moderator
        telegramNotifyService.notifyRegisterUser(authUser);

        // refresh statistic materialized view
        refresher.refreshStatistics();

        // send notify email

        emailNotifyService.notify(authUser);
        repository.save(authUser);
        return authUser;


    }
}
