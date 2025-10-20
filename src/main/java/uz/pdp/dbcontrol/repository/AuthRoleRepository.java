package uz.pdp.dbcontrol.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.pdp.dbcontrol.model.entity.AuthRole;
import uz.pdp.dbcontrol.repository.base.BaseRepository;

import java.util.Optional;

public interface AuthRoleRepository extends JpaRepository<AuthRole, String>, BaseRepository<AuthRole> {

    @Query("""
            select a from AuthRole a
            where a.deleted = false
            and (:search is null or lower(a.name) like lower(concat('%', :search, '%')))
            """)
    @Override
    Page<AuthRole> findAllByCriteria(@Param("search") String search, Pageable pageable);

    @Query("""
            select a from AuthRole a
            where a.id = :id and a.deleted = false
            """)
    @Override
    Optional<AuthRole> findByIdAndDeletedFalse(@Param("id") String id);
}
