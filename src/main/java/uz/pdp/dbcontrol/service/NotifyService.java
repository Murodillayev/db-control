package uz.pdp.dbcontrol.service;

import uz.pdp.dbcontrol.model.entity.AuthUser;

public interface NotifyService {

    void sendUserCredentials(AuthUser authUser);
}
