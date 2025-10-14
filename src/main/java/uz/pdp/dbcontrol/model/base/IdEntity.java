package uz.pdp.dbcontrol.model.base;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@Getter
@MappedSuperclass
public class IdEntity {
    @Id
    private String id = UUID.randomUUID().toString();
    private boolean deleted = false;

}
