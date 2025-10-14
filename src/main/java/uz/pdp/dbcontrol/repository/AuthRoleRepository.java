package uz.pdp.dbcontrol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.dbcontrol.model.entity.AuthRole;

public interface AuthRoleRepository extends JpaRepository<AuthRole,String> {

}
