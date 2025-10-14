package uz.pdp.dbcontrol.service.base;

import java.util.List;

public interface CrudService<CD, UD, D> {
    D create(CD dto);
    D update(String id,UD dto);
    void delete(String id);
    D get(String id);
    List<D> getAll();
}