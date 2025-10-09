package uz.pdp.dbcontrol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.dbcontrol.model.entity.ProjectDatabase;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectDatabaseRepository extends JpaRepository<ProjectDatabase, String> {
    Optional<ProjectDatabase> findByIdAndDeletedFalse(String id);
    
    List<ProjectDatabase> findAllByDeletedFalse();
    
    Optional<ProjectDatabase> findByName(String name);
    
    Optional<ProjectDatabase> findByAgentId(String agentId);
}




