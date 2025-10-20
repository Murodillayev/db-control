package uz.pdp.dbcontrol.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.pdp.dbcontrol.model.entity.ProjectDatabaseUser;
import uz.pdp.dbcontrol.repository.base.BaseRepository;

import java.util.Optional;

public interface ProjectDatabaseUserRepository
        extends JpaRepository<ProjectDatabaseUser, String>, BaseRepository<ProjectDatabaseUser> {

    @Query("""
            select u from ProjectDatabaseUser u
            where u.deleted = false
            and (:search is null or lower(u.authUser.username) like lower(concat('%', :search, '%')))
            """)
    @Override
    Page<ProjectDatabaseUser> findAllByCriteria(@Param("search") String search, Pageable pageable);

    @Query("""
            select u from ProjectDatabaseUser u
            where u.id = :id and u.deleted = false
            """)
    @Override
    Optional<ProjectDatabaseUser> findByIdAndDeletedFalse(@Param("id") String id);
}
