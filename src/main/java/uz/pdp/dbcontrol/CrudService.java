package uz.pdp.dbcontrol;

public interface CrudService {

    void create(Object object);

    void update(Object object);

    void delete(Object object);

    Object get(Object object);

    default String getAll() {
        return null;
    }
}
