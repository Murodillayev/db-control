package uz.pdp.dbcontrol.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import uz.pdp.dbcontrol.model.base.BaseEntity;

@Document(collection = "database_roles")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class DatabaseRole extends BaseEntity {
    @Field("name")
    private String name;
    
    @Field("code")
    private String code;
    
    @Field("description")
    private String description;
}
