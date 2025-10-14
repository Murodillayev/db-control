package uz.pdp.dbcontrol.model.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.dbcontrol.model.base.BaseEntity;
import uz.pdp.dbcontrol.model.base.IdEntity;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AuthPermission extends IdEntity {
    private String name;
    private String code;
}
