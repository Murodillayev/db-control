package uz.pdp.dbcontrol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.dbcontrol.model.entity.ProjectDatabaseUser;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectDatabaseUserRepository extends JpaRepository<ProjectDatabaseUser, String> {
    Optional<ProjectDatabaseUser> findByIdAndDeletedFalse(String id);
    
    List<ProjectDatabaseUser> findAllByDeletedFalse();
    
    List<ProjectDatabaseUser> findAllByDatabaseIdAndDeletedFalse(String databaseId);
    
    List<ProjectDatabaseUser> findAllByAuthUserIdAndDeletedFalse(String authUserId);
    
    Optional<ProjectDatabaseUser> findByAuthUserIdAndDatabaseIdAndDeletedFalse(String authUserId, String databaseId);
}
