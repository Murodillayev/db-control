package uz.pdp.dbcontrol.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uz.pdp.dbcontrol.model.base.BaseEntity;

import java.util.Collection;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AuthUser extends BaseEntity implements UserDetails {
    private String username;
    private String password;
    private String dbUsername;
    private String dbPassword;
    private String email;
    private String phone;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private AuthRole role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new java.util.ArrayList<>();

        // Add role authority
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName().toUpperCase()));

        // Add permission authorities
        if (role != null && role.getPermissions() != null) {
            authorities.addAll(
                    role.getPermissions().stream()
                            .map(permission -> new SimpleGrantedAuthority(permission.getName()))
                            .toList()
            );
        }

        return authorities;
    }
}
