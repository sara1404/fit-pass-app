package Service;

import Model.SportObject;
import Repository.SportObjectRepository;

public class SportObjectService {
    private SportObjectRepository sportObjectRepository;

    public SportObjectService(SportObjectRepository sportObjectRepository) {
        this.sportObjectRepository = sportObjectRepository;
    }

    public SportObject findByName(String name) {
        return sportObjectRepository.findByName(name);
    }


}
