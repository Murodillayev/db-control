package uz.pdp.dbcontrol.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.dbcontrol.model.base.BaseEntity;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class DatabaseRole extends BaseEntity {
    private String name;
    
    private String code;
    
    private String description;
}
