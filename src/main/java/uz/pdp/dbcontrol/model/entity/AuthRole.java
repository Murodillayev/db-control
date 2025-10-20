package uz.pdp.dbcontrol.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.dbcontrol.model.base.BaseEntity;
import uz.pdp.dbcontrol.model.base.IdEntity;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AuthRole extends IdEntity {
    private String name;
    private String code;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "auth_role_permission",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    private List<AuthPermission> permissions;
}
