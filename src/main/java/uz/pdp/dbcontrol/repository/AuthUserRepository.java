package uz.pdp.dbcontrol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.dbcontrol.model.entity.AuthUser;

public interface AuthUserRepository extends JpaRepository<AuthUser, String> {
}
