package uz.pdp.dbcontrol.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.pdp.dbcontrol.model.entity.DatabaseRole;
import uz.pdp.dbcontrol.repository.base.BaseRepository;

import java.util.Optional;

public interface DatabaseRoleRepository extends JpaRepository<DatabaseRole, String>, BaseRepository<DatabaseRole> {

    @Query("""
            select d from DatabaseRole d
            where d.deleted = false
            and (:search is null or lower(d.name) like lower(concat('%', :search, '%')))
            """)
    @Override
    Page<DatabaseRole> findAllByCriteria(@Param("search") String search, Pageable pageable);

    @Query("""
            select d from DatabaseRole d
            where d.id = :id and d.deleted = false
            """)
    @Override
    Optional<DatabaseRole> findByIdAndDeletedFalse(@Param("id") String id);
}
