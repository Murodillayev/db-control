package uz.pdp.dbcontrol.validation;

import uz.pdp.dbcontrol.exception.NotFoundException;
import uz.pdp.dbcontrol.model.base.IdEntity;
import uz.pdp.dbcontrol.repository.base.BaseRepository;

public interface Validator<CD, UD, R extends BaseRepository<E> ,E> {

    void validateForCreate(CD dto);

    void validateForUpdate(UD dto);

    default E existsAndGet(R repository, String id) {
        return repository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new NotFoundException("Entity not found"));
    }

}
