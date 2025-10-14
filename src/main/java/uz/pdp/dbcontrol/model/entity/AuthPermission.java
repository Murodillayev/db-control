package uz.pdp.dbcontrol.model.entity;

import jakarta.persistence.Entity;
import lombok.*;
import uz.pdp.dbcontrol.model.base.BaseEntity;
import uz.pdp.dbcontrol.model.base.IdEntity;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class AuthPermission extends IdEntity {
    private String name;
    private String code;
}
