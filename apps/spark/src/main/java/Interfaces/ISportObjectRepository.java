package Interfaces;

import Interfaces.Base.IGenericCRUD;
import Model.SportObject;
import Model.SportObjectContent;

public interface ISportObjectRepository extends IGenericCRUD<SportObject> {
    
    SportObject findByName(String name);
    void deleteByName(String name);
    SportObject updateContent(String id, SportObjectContent content) throws Exception;
}
