package uz.pdp.dbcontrol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.dbcontrol.model.entity.DatabaseRole;

import java.util.List;
import java.util.Optional;

@Repository
public interface DatabaseRoleRepository extends JpaRepository<DatabaseRole, String> {
    Optional<DatabaseRole> findByCode(String code);
    
    List<DatabaseRole> findAllByIdIn(List<String> ids);
}




