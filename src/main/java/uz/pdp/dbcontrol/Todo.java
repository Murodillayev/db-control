package uz.pdp.dbcontrol;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Todo {
    @Id
    private String id = UUID.randomUUID().toString();

    @DocumentReference(lazy = true)
    private AuthUser user;

    private String title;

    private Boolean completed;
}
