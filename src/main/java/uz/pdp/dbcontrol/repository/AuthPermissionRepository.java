package uz.pdp.dbcontrol.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.pdp.dbcontrol.model.entity.AuthPermission;
import uz.pdp.dbcontrol.repository.base.BaseRepository;

import java.util.Optional;

public interface AuthPermissionRepository
        extends JpaRepository<AuthPermission, String>, BaseRepository<AuthPermission> {

    @Query("""
            select a from AuthPermission a
            where a.deleted = false
            and (:search is null or lower(a.name) like lower(concat('%', :search, '%')))
            """)
    @Override
    Page<AuthPermission> findAllByCriteria(@Param("search") String search, Pageable pageable);

    @Query("""
                select a from AuthPermission a
                where a.id = :id and a.deleted = false
            """)
    @Override
    Optional<AuthPermission> findByIdAndDeletedFalse(@Param("id") String id);


}
