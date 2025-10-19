package uz.pdp.dbcontrol.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AuthUser {
    @Id
    private String id = UUID.randomUUID().toString();
    private String name;
    private String username;
    private String password;
    private String email;
    private String otp; // one time password
}
