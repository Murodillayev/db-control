package uz.pdp.dbcontrol.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.dbcontrol.models.AuthUser;

public interface AuthUserRepository extends JpaRepository<AuthUser, String> {

}
