package Repository;

import DataHandler.DataHandler;
import DataHandler.SubtypeDataHandler;
import Interfaces.ISportObjectRepository;
import Model.Address;
import Model.Location;
import Model.SportObject;
import Model.WorkTime;
import Utils.Constants;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SportObjectRepository implements ISportObjectRepository {
    private List<SportObject> sportObjects;
    private DataHandler<SportObject> sportObjectDataHandler;

    public SportObjectRepository(DataHandler<SportObject> sportObjectDataHandler) {
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

}
