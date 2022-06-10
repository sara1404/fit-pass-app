package Interfaces;

import Interfaces.Base.IGenericCRUD;
import Model.SportObject;

public interface ISportObjectRepository extends IGenericCRUD<SportObject> {
    SportObject findByName(String name);
    void deleteByName(String name);
}
