package Repository;

import DataHandler.DataHandler;
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
        sportObjects = new ArrayList<>();
        this.sportObjectDataHandler = sportObjectDataHandler;

        sportObjects.add(new SportObject("Objekat1",
                    Constants.SportObjectType.GYM,
                    Constants.SportObjectOffer.GROUP_TRAINING,
                    Constants.SportObjectStatus.OPEN,
                    new Location(1, 1, new Address("janka cmelika", "56", "Novi Sad", 21000)),
                    "logo",
                    9,
                new WorkTime(LocalDateTime.now(), LocalDateTime.now())


        ));
    }

    public List<SportObject> findAll() {
        return sportObjects;
    }

    @Override
    public void create(SportObject object) {
        sportObjects.add(object);
    }

    @Override
    public void update(SportObject object)  {
        SportObject obj = findByName(object.getName());
        obj.update(object);
    }

    public SportObject findByName(String name) {
        for(SportObject obj: sportObjects)  {
            if(obj.getName().equals(name)) {
                return obj;
            }
        }
        return null;
    }

    @Override
    public void deleteByName(String name) {
        SportObject obj = findByName(name);
        sportObjects.remove(obj);
    }

}
