package Interfaces.Base;

import java.util.Collection;

public interface IGenericCRUD<T> {

    Collection<T> findAll();
    void create(T object);

}
