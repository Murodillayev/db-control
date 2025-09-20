package uz.pdp.dbcontrol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.dbcontrol.model.entity.AuthPermission;

public interface AuthPermissionRepository extends JpaRepository<AuthPermission, String> {
}
