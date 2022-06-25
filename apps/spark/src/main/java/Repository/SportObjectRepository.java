package Repository;

import DataHandler.DataHandler;
import DataHandler.SubtypeDataHandler;
import DataHandler.SportObjectDataHandler;
import Interfaces.ISportObjectRepository;

import Model.SportObject;
import Model.SportObjectContent;

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
    public SportObject updateContent(String id, SportObjectContent content) throws Exception {
        SportObject sportObject = findByName(id);
        if(sportObject.doesContentAlreadyExists(content.getName()))
            throw new Exception("Content already exists!");
        sportObject.addContent(content);
        sportObjectDataHandler.writeToFile(sportObjects);
        return sportObject;
    }


}
