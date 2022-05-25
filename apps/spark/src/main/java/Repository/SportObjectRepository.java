package Repository;

import Model.Address;
import Model.Location;
import Model.SportObject;
import Model.WorkTime;
import Utils.Constants;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SportObjectRepository {
    private List<SportObject> sportObjects;
    public SportObjectRepository() {
        sportObjects = new ArrayList<>();

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

    public SportObject findByName(String name) {
        for(SportObject obj: sportObjects)  {
            if(obj.getName().equals(name)) {
                return obj;
            }
        }
        return null;
    }

}
