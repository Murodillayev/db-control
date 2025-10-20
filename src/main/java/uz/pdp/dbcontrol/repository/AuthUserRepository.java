package uz.pdp.dbcontrol.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.pdp.dbcontrol.model.entity.AuthUser;
import uz.pdp.dbcontrol.repository.base.BaseRepository;

import java.util.Optional;

public interface AuthUserRepository extends JpaRepository<AuthUser, String>, BaseRepository<AuthUser> {

    @Query("""
        select a from AuthUser a
        where a.deleted = false
        and (:search is null or lower(a.username) like lower(concat('%', :search, '%')))
        """)
    @Override
    Page<AuthUser> findAllByCriteria(@Param("search") String search, Pageable pageable);

    @Query("""
        select a from AuthUser a
        where a.id = :id and a.deleted = false
        """)
    @Override
    Optional<AuthUser> findByIdAndDeletedFalse(@Param("id") String id);

    Optional<AuthUser> findByUsername(String username);

    boolean existsByUsername(String username);
}
