package uz.pdp.dbcontrol.services;

import org.springframework.stereotype.Service;
import uz.pdp.dbcontrol.repos.CreateUserEvent;
import uz.pdp.dbcontrol.models.AuthUser;

import java.util.LinkedList;
import java.util.Queue;

@Service
public class CacheService {

    private static final Queue<CreateUserEvent> createUserEvents = new LinkedList<>();

    public void put(CreateUserEvent createUserEvent) {
        AuthUser authUser = createUserEvent.getAuthUser();
        if (createUserEvents.stream().anyMatch(e -> e.getAuthUser().getId().equals(authUser.getId()))) {
            return;
        }
        createUserEvents.add(createUserEvent);
    }

    public CreateUserEvent poll() {
        return createUserEvents.poll();
    }


    public Integer getSize() {
        return createUserEvents.size();
    }

}
