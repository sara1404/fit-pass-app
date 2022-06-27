package Interfaces;

import Interfaces.Base.IGenericCRUD;
import Model.SportObject;
import Model.SportObjectContent;

public interface ISportObjectRepository extends IGenericCRUD<SportObject> {
    
    SportObject findByName(String name);
    void deleteByName(String name);
    SportObject findById(int id);

    SportObjectContent findContent(String objectId, String name);
    SportObject addContent(int id, SportObjectContent content) throws Exception;
    SportObject updateContent(String id, String contentId, SportObjectContent content);
}
