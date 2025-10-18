package uz.pdp.dbcontrol;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;

@Slf4j
@Getter
public final class CreateUserEvent extends ApplicationEvent {


    private final AuthUser authUser;

    public CreateUserEvent(Object source, AuthUser authUser) {
        super(source);
        this.authUser = authUser;
    }


}
