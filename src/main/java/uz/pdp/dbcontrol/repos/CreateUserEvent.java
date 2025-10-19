package uz.pdp.dbcontrol.repos;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;
import uz.pdp.dbcontrol.models.AuthUser;

@Slf4j
@Getter
public final class CreateUserEvent extends ApplicationEvent {


    private final AuthUser authUser;

    public CreateUserEvent(Object source, AuthUser authUser) {
        super(source);
        this.authUser = authUser;
    }


}
