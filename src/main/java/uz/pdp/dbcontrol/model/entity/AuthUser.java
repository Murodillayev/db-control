package uz.pdp.dbcontrol.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import uz.pdp.dbcontrol.model.base.BaseEntity;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AuthUser extends BaseEntity {
    private String username;
    private String password;

    @Column(name = "db_username")
    private String dbUsername;
    private String dbPassword;
    private String email;
    private String phone;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private AuthRole role;
}
