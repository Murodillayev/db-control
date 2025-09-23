package uz.pdp.dbcontrol.service.base;

import java.util.List;

/**
 * @param <D>
 * @param <CR>
 * @param <UD>
 * @param <C> criteria class. Bu filterlar uchun kerak
 * @param <K>
 */
public interface CrudService<D, CD, UD, C, K> {

    /**
     * @param dto is create dto
     * @return saved data
     */
    D create(CD dto);

    D get(K id);

    List<D> getAll(C criteria);

    D update(UD dto, K id);

    void delete(K id);

}
