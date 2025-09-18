package uz.pdp.dbcontrol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.dbcontrol.model.entity.ProjectDatabaseUser;

public interface ProjectDatabaseUserRepository extends JpaRepository<ProjectDatabaseUser, String> {
}
