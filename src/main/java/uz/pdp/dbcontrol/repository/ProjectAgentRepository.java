package uz.pdp.dbcontrol.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.pdp.dbcontrol.model.entity.ProjectAgent;
import uz.pdp.dbcontrol.repository.base.BaseRepository;

import java.util.Optional;

public interface ProjectAgentRepository extends JpaRepository<ProjectAgent, String>, BaseRepository<ProjectAgent> {

    @Query("""
            select p from ProjectAgent p
            where p.deleted = false
            and (:search is null or lower(p.databaseUrl) like lower(concat('%', :search, '%')))
            """)
    @Override
    Page<ProjectAgent> findAllByCriteria(@Param("search") String search, Pageable pageable);

    @Query("""
            select p from ProjectAgent p
            where p.id = :id and p.deleted = false
            """)
    @Override
    Optional<ProjectAgent> findByIdAndDeletedFalse(@Param("id") String id);
}
