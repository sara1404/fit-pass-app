package Service;

import Interfaces.ISportObjectRepository;
import Model.SportObject;
import Model.SportObjectContent;
import Utils.SearchImpl.FilterImpl.*;
import Utils.SearchImpl.SportObjectsPipeline;

import java.util.List;
import java.util.Map;

public class SportObjectService {
    private ISportObjectRepository sportObjectRepository;
    private SportObjectsPipeline sportObjectsPipeline;

    public SportObjectService(ISportObjectRepository sportObjectRepository) {
        this.sportObjectRepository = sportObjectRepository;
        sportObjectsPipeline = new SportObjectsPipeline();
        sportObjectsPipeline
                .addFilter(new SportObjectNameFilter())
                .addFilter(new SportObjectTypeFilter())
                .addFilter(new SportObjectCountryFilter())
                .addFilter(new SportObjectCityFilter())
                .addFilter(new SportObjectAverageMarkFilter())
                .addFilter(new SportObjectOpenFilter())
                .addFilter(new SortBySportObjectName())
                .addFilter(new SortBySportObjectCity())
                .addFilter(new SortBySportObjectCountry())
                .addFilter(new SortBySportObjectAverageMark())
                .addFilter(new SortByAscDesc());

    }

    public void create(SportObject object) {
        sportObjectRepository.create(object);
    }

    public void update(SportObject object) {
        sportObjectRepository.update(object);
    }

    public SportObject findByName(String name) { return sportObjectRepository.findByName(name);}

    public List<SportObject> findAll() {
        return sportObjectRepository.findAll();
    }

    public SportObject findOne(String id){
        return sportObjectRepository.findByName(id);
    }

    public SportObject updateContent(String id, SportObjectContent content) throws Exception {
        return sportObjectRepository.updateContent(id, content);
    }
    public void deleteByName(String name) {
        sportObjectRepository.deleteByName(name);
    }

    public List<SportObject> filterSportObjects(Map<String, String[]> params){
        return sportObjectsPipeline.filterAll(findAll(), params);
    }
}
