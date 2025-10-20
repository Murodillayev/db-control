package uz.pdp.dbcontrol.repository.base;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BaseRepository<E> {
    Page<E> findAllByCriteria(String search, Pageable pageable);

    Optional<E> findByIdAndDeletedFalse(String id);
}
