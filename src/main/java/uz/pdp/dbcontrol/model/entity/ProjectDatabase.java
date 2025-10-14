package uz.pdp.dbcontrol.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
public class ProjectDatabase extends BaseEntity {
    private String name;
    private String description;

    @OneToOne
    @JoinColumn(name = "agent_id")
    private ProjectAgent agent;

    @OneToMany
    private List<AuthUser> members;
}