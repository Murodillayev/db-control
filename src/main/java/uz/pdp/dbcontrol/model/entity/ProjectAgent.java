package uz.pdp.dbcontrol.model.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLRestriction;
import uz.pdp.dbcontrol.model.base.BaseEntity;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@SQLRestriction("deleted=false")
public class ProjectAgent extends BaseEntity {
    private String databaseUsername;
    private String databasePassword;
    private String databaseUrl;
    private String callbackUrl;
}
