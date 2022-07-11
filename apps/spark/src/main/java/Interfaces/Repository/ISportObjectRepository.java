package Interfaces.Repository;

import Interfaces.Base.IGenericCRUD;
import Model.SportObject;
import Model.SportObjectContent;
import Model.TrainingSession;
import Utils.Constants;

import java.util.List;

public interface ISportObjectRepository extends IGenericCRUD<SportObject> {
    
    SportObject findByName(String name);
    void deleteByName(String name);
    SportObject findById(int id);

    SportObjectContent findContent(int objectId, String name);
    SportObject addContent(int id, SportObjectContent content) throws Exception;
    SportObject updateContent(String contentId, TrainingSession content);
    List<TrainingSession> findAdditionalTrainingContent(int objectId);
    List<TrainingSession> findContentByType(int objectId, Constants.TrainingType type);
    List<String> findCoachesForSportObject(int id);
}
