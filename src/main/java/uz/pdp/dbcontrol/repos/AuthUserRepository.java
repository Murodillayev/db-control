package uz.pdp.dbcontrol.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.dbcontrol.models.AuthUser;

import java.util.Optional;

public interface AuthUserRepository extends JpaRepository<AuthUser, String> {
    Optional<AuthUser> findByEmail(String email);
    Optional<AuthUser> findByUsername(String username);
}
