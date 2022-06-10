package Service;

import Interfaces.ISportObjectRepository;
import Model.SportObject;
import Repository.SportObjectRepository;

import java.util.List;

public class SportObjectService {
    private ISportObjectRepository sportObjectRepository;

    public SportObjectService(ISportObjectRepository sportObjectRepository) {
        this.sportObjectRepository = sportObjectRepository;
    }

    public void create(SportObject object) {
        sportObjectRepository.create(object);
    }

    public void update(SportObject object) {
        sportObjectRepository.update(object);
    }

    public SportObject findByName(String name) {

        return sportObjectRepository.findByName(name);
    }

    public List<SportObject> findAll() {
        return sportObjectRepository.findAll();
    }

    public void deleteByName(String name) {
        sportObjectRepository.deleteByName(name);
    }
}
