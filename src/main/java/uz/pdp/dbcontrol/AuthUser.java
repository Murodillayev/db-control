package uz.pdp.dbcontrol;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document
public class AuthUser {
    @Id
    private String id = UUID.randomUUID().toString();
    private String username;
    private String password;
    private String name;

    @DocumentReference(lazy = true) // @DBRef
    private List<Todo> todos;

}
