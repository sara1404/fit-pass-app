package Service;

import Interfaces.Repository.ISportObjectRepository;
import Model.SportObject;
import Model.SportObjectContent;
import Model.TrainingSession;
import Utils.Constants;
import Utils.SearchImpl.FilterImpl.SportObjectFilters.*;
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
                .addFilter(new SortByAscDesc<SportObject>());

    }

    public SportObject create(SportObject object) throws Exception{
        if(object == null) throw new Exception("Sport Object should have data!");
       return sportObjectRepository.create(object);
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

    public SportObject findById(int id) {
        return sportObjectRepository.findById(id);
    }

    public SportObjectContent findContent(int objectId, String name){
        return sportObjectRepository.findContent(objectId, name);
    }

    public SportObject addContent(int id, SportObjectContent content) throws Exception {
        return sportObjectRepository.addContent(id, content);
    }

    public SportObject updateContent(String contentId, TrainingSession content){
        return sportObjectRepository.updateContent(contentId, content);
    }

    public void deleteByName(String name) {
        sportObjectRepository.deleteByName(name);
    }

    public List<SportObject> filterSportObjects(Map<String, String[]> params){
        return sportObjectsPipeline.filterAll(findAll(), params);
    }

    public void updateLogoUrlForObject(SportObject sportObject, String logoUrl) {
        sportObject.setLogoUrl(logoUrl);
        sportObjectRepository.update(sportObject);
    }

    public List<TrainingSession> findAdditionalTrainingContent(int objectId) {
        return sportObjectRepository.findAdditionalTrainingContent(objectId);
    }

    public List<TrainingSession> findTrainingContentByType(int objectId, Constants.TrainingType type) {
        return sportObjectRepository.findContentByType(objectId, type);
    }

}
