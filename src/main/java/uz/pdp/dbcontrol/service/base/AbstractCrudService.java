package uz.pdp.dbcontrol.service.base;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.dbcontrol.exception.NotFoundException;
import uz.pdp.dbcontrol.mapper.base.InterfaceMapper;
import uz.pdp.dbcontrol.model.base.IdEntity;
import uz.pdp.dbcontrol.validation.Validator;

import java.util.List;

public abstract class AbstractCrudService<
        E extends IdEntity,
        CD,
        UD,
        D,
        R extends JpaRepository<E, String>,
        M extends InterfaceMapper<D, CD, UD, E>,
        V extends Validator<CD, UD>
        > implements CrudService<CD, UD, D> {

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
        E entity = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Entity not found"));
        mapper.updateEntityFromDto(dto, entity);
        repository.save(entity);
        return mapper.toDto(entity);
    }

    @Override
    public void delete(String id) {
        E entity = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Entity not found"));
        entity.setDeleted(true);
        repository.save(entity);
    }

    @Override
    public D get(String id) {
        E entity = repository.findById(id)
                .filter(e -> !e.isDeleted())
                .orElseThrow(() -> new NotFoundException("Entity not found"));
        return mapper.toDto(entity);
    }

    @Override
    public List<D> getAll() {
        return repository.findAll()
                .stream()
                .filter(e -> !e.isDeleted())
                .map(mapper::toDto)
                .toList();
    }
}
