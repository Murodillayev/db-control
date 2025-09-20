package uz.pdp.dbcontrol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.dbcontrol.model.entity.ProjectDatabase;

public interface ProjectDatabaseRepository extends JpaRepository<ProjectDatabase,String> {
}
