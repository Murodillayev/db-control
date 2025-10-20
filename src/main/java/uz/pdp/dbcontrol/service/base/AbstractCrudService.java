package uz.pdp.dbcontrol.service.base;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.dbcontrol.criteria.BaseCriteria;
import uz.pdp.dbcontrol.dto.DataResponse;
import uz.pdp.dbcontrol.exception.NotFoundException;
import uz.pdp.dbcontrol.mapper.base.InterfaceMapper;
import uz.pdp.dbcontrol.model.base.IdEntity;
import uz.pdp.dbcontrol.repository.base.BaseRepository;
import uz.pdp.dbcontrol.validation.Validator;

import java.util.List;

public abstract class AbstractCrudService<
        E extends IdEntity,
        CD,
        UD,
        D,
        C extends BaseCriteria,
        R extends BaseRepository<E> & JpaRepository<E, String>,
        M extends InterfaceMapper<D, CD, UD, E>,
        V extends Validator<CD, UD, R, E>
        > implements CrudService<CD, UD, D, C> {

    private final R repository;
    private final M mapper;
    private final V validator;

    public AbstractCrudService(R repository, M mapper, V validator) {
        this.repository = repository;
        this.mapper = mapper;
        this.validator = validator;
    }

    @Override
    public D create(CD dto) {
        validator.validateForCreate(dto);
        E entity = mapper.toEntityFromCreate(dto);
        repository.save(entity);
        return mapper.toDto(entity);
    }

    @Override
    public D update(String id, UD dto) {
        validator.validateForUpdate(dto);
        E entity = validator.existsAndGet(repository, id);
        mapper.updateEntityFromDto(dto, entity);
        repository.save(entity);
        return mapper.toDto(entity);
    }

    @Override
    public void delete(String id) {
        E entity = validator.existsAndGet(repository, id);
        entity.setDeleted(true);
        repository.save(entity);
    }

    @Override
    public D get(String id) {
        E entity = validator.existsAndGet(repository, id);
        return mapper.toDto(entity);
    }

    @Override
    public DataResponse<List<D>> getAll(C criteria) {
        PageRequest pageable = PageRequest.of(criteria.getPage(), criteria.getSize());

        Page<E> page = repository.findAllByCriteria(criteria.getSearch(), pageable);

        List<D> list = page.getContent().stream()
                .map(mapper::toDto)
                .toList();
        return new DataResponse<>(list, page.getTotalElements(), page.getTotalPages());
    }
}
