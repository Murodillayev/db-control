package uz.pdp.dbcontrol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.dbcontrol.model.entity.AuthUser;

import java.util.Optional;
@Repository
public interface AuthUserRepository extends JpaRepository<AuthUser, String> {
    Optional<AuthUser> findByUsernameAndDeletedFalse(String username);

    Optional<AuthUser> findByUsername(String username);
}
