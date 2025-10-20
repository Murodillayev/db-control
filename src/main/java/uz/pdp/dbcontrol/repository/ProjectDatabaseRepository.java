package uz.pdp.dbcontrol.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.pdp.dbcontrol.model.entity.ProjectDatabase;
import uz.pdp.dbcontrol.repository.base.BaseRepository;

import java.util.Optional;

public interface ProjectDatabaseRepository extends JpaRepository<ProjectDatabase, String>, BaseRepository<ProjectDatabase> {

    @Query("""
            select p from ProjectDatabase p
            where p.deleted = false
            and (:search is null or lower(p.name) like lower(concat('%', :search, '%')))
            """)
    @Override
    Page<ProjectDatabase> findAllByCriteria(@Param("search") String search, Pageable pageable);

    @Query("""
            select p from ProjectDatabase p
            where p.id = :id and p.deleted = false
            """)
    @Override
    Optional<ProjectDatabase> findByIdAndDeletedFalse(@Param("id") String id);
}
