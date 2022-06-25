package Interfaces.Base;

import Model.SportObject;

import java.util.Collection;
import java.util.List;

public interface IGenericCRUD<T> {

    List<T> findAll();
    T create(T object);
    void update(T object);

}
