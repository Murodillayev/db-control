package uz.pdp.dbcontrol.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import uz.pdp.dbcontrol.config.CustomUserDetails;

public class Utils {

    public static CustomUserDetails sessionUser(){
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        return (CustomUserDetails) authentication.getPrincipal();
    }
}
