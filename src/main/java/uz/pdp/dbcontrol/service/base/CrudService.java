package uz.pdp.dbcontrol.service.base;

import uz.pdp.dbcontrol.dto.DataResponse;

import java.util.List;

public interface CrudService<CD, UD, D, C> {
    D create(CD dto);

    D update(String id, UD dto);

    void delete(String id);

    D get(String id);

    List<D> getAll(C criteria);
}