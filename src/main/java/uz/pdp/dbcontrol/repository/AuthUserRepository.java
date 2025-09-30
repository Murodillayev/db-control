package uz.pdp.dbcontrol.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.dbcontrol.model.entity.AuthUser;

import java.util.Optional;

public interface AuthUserRepository extends JpaRepository<AuthUser, String> {
    Optional<AuthUser> findByUsernameAndDeletedFalse(String username);

    @Query("""
            from AuthUser a 
            where not a.deleted
                  and (a.fullName ilike concat('%',:search,'%') or a.username ilike concat('%',:search,'%') or a.dbUsername ilike concat('%',:search,'%'))
            """)
    Page<AuthUser> findAllBySearch(String search, Pageable pageable);
}