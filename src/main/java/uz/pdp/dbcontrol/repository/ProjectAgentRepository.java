package uz.pdp.dbcontrol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.dbcontrol.model.entity.ProjectAgent;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectAgentRepository extends JpaRepository<ProjectAgent, String> {
    Optional<ProjectAgent> findByIdAndDeletedFalse(String id);
    
    List<ProjectAgent> findAllByDeletedFalse();
    
    Optional<ProjectAgent> findByDatabaseUrl(String databaseUrl);
}




