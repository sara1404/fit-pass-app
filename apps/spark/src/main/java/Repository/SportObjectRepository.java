package Repository;

import DataHandler.DataHandler;
import DataHandler.SubtypeDataHandler;
import DataHandler.SportObjectDataHandler;
import Interfaces.ISportObjectRepository;

import Model.SportObject;
import Model.SportObjectContent;
import Model.TrainingSession;

import java.util.List;

public class SportObjectRepository implements ISportObjectRepository {
    private List<SportObject> sportObjects;
    private SportObjectDataHandler sportObjectDataHandler;

    public SportObjectRepository(SportObjectDataHandler sportObjectDataHandler) {
        this.sportObjectDataHandler = sportObjectDataHandler;
        this.sportObjects = sportObjectDataHandler.readFromFile();
    }

    @Override
    public List<SportObject> findAll() {
        return sportObjects;
    }

    @Override
    public SportObject findByName(String name) {
        for(SportObject obj: sportObjects)  {
            if(obj.getName().equals(name)) {
                return obj;
            }
        }
        return null;
    }

    @Override
    public void create(SportObject object) {
        sportObjects.add(object);
        sportObjectDataHandler.writeToFile(sportObjects);
    }

    @Override
    public void update(SportObject object)  {
        SportObject obj = findByName(object.getName());
        obj.update(object);
        sportObjectDataHandler.writeToFile(sportObjects);
    }

    @Override
    public void deleteByName(String name) {
        SportObject obj = findByName(name);
        sportObjects.remove(obj);
        sportObjectDataHandler.writeToFile(sportObjects);
    }

    @Override
    public SportObjectContent findContent(String objectId, String name) {
        SportObject sportObject = findByName(objectId);
        return sportObject.findSpecificContent(name);
    }

    @Override
    public SportObject addContent(String id, SportObjectContent content) throws Exception {
        SportObject sportObject = findByName(id);
        if(sportObject.doesContentAlreadyExists(content.getName()))
            throw new Exception("Content already exists!");
        sportObject.addContent(content);
        sportObjectDataHandler.writeToFile(sportObjects);
        return sportObject;
    }
    @Override
    public SportObject updateContent(String id, String contentId, SportObjectContent content){
        SportObject sportObject = findByName(id);
        SportObjectContent sportObjectContent = sportObject.findSpecificContent(contentId);
        if(sportObjectContent.getFlag().equals("training")){
            TrainingSession oldTraining = (TrainingSession)sportObjectContent;
            TrainingSession training = (TrainingSession)content;
            oldTraining.update(training);
        }
        else
            sportObjectContent.update(content);
        return sportObject;
    }

}
