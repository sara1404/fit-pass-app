package Interfaces.Base;

import java.util.Collection;
import java.util.List;

public interface IGenericCRUD<T> {

    List<T> findAll();
    void create(T object);
    void update(T object);

}
