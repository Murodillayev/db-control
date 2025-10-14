package uz.pdp.dbcontrol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.dbcontrol.model.entity.DatabaseRole;

import java.util.Collection;
import java.util.List;

public interface DatabaseRoleRepository extends JpaRepository<DatabaseRole,String> {
    List<DatabaseRole> findAllByIdIn(List<String> ids);
}
