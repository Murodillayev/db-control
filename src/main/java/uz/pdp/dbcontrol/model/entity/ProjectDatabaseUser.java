package uz.pdp.dbcontrol.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.dbcontrol.model.base.BaseEntity;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ProjectDatabaseUser extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private AuthUser authUser;

    @ManyToOne
    @JoinColumn(name = "database_id")
    private ProjectDatabase database;

    @ManyToMany
    @JoinTable(
            name = "database_user_role",
            joinColumns = @JoinColumn(name = "database_user_id"),
            inverseJoinColumns = @JoinColumn(name = "database_role_id")
    )
    private List<DatabaseRole> roles;
}
