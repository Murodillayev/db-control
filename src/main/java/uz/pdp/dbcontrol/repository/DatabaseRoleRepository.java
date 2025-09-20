package uz.pdp.dbcontrol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.dbcontrol.model.entity.DatabaseRole;

public interface DatabaseRoleRepository extends JpaRepository<DatabaseRole,String> {
}
