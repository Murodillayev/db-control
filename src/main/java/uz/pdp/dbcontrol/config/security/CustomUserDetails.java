package uz.pdp.dbcontrol.config.security;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uz.pdp.dbcontrol.model.entity.AuthPermission;
import uz.pdp.dbcontrol.model.entity.AuthRole;
import uz.pdp.dbcontrol.model.entity.AuthUser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
public class CustomUserDetails implements UserDetails {
    private String username;
    private String password;
    private List<GrantedAuthority> authorities;

    public CustomUserDetails(AuthUser user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.authorities = prepareAuthorities(user);
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    private List<GrantedAuthority> prepareAuthorities(AuthUser user) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        AuthRole role = user.getRole();
        if (role == null) {
            return Collections.emptyList();
        }
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getCode()));
        List<AuthPermission> permissions = role.getPermissions();
        if (permissions != null) {
            for (AuthPermission permission : permissions) {
                authorities.add(new SimpleGrantedAuthority(permission.getCode()));
            }
        }
        return authorities;
    }

}
