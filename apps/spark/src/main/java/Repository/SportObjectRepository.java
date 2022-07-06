package Repository;

import DataHandler.*;
import Interfaces.Repository.ISportObjectRepository;

import Model.*;
import Utils.Constants;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SportObjectRepository implements ISportObjectRepository {
    private List<SportObject> sportObjects;
    private TemplateDataHandler<SportObject> sportObjectDataHandler;
    private Map<Integer, SportObject> objectReferences;

    public SportObjectRepository(TemplateDataHandler<SportObject> sportObjectDataHandler) {
        this.sportObjectDataHandler = sportObjectDataHandler;
        this.sportObjects = sportObjectDataHandler.readFromFile();
        objectReferences = new HashMap<>();
        mapObjectListToHashMap();
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
    public SportObject create(SportObject object) {
        object.setId(generateId());
        object.setWorkTime(getGenericWorkTime());
        object.setStatus(Constants.SportObjectStatus.OPEN);
        sportObjects.add(object);
        sportObjectDataHandler.writeToFile(sportObjects);
        return object;
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
    public SportObject findById(int id) {
        for(SportObject sportObject: sportObjects) {
            if(sportObject.getId() == id)
                return sportObject;
        }
        return null;
    }

    @Override
    public SportObjectContent findContent(int objectId, String name) {
        SportObject sportObject = findById(objectId);
        return sportObject.findSpecificContent(name);
    }

    @Override
    public SportObject addContent(int id, SportObjectContent content) throws Exception {
        SportObject sportObject = findById(id);
        if(sportObject.doesContentAlreadyExists(content.getName()))
            throw new Exception("Content already exists!");
        sportObject.addContent(content);
        sportObjectDataHandler.writeToFile(sportObjects);
        return sportObject;
    }

    private int generateId() {
        int id = 0;
        List<Integer> ids = extractExistingIds();

        while(ids.contains(id))
            id++;
        return id;
    }

    private List<Integer> extractExistingIds() {
        return sportObjects.stream().map(SportObject::getId).collect(Collectors.toList());
    }


    @Override
    public SportObject updateContent(String contentId, TrainingSession content){
        SportObject sportObject = findById(content.getObjectId());
        SportObjectContent sportObjectContent = sportObject.findSpecificContent(contentId);
        if(sportObjectContent.getFlag().equals("training")){
            TrainingSession oldTraining = (TrainingSession)sportObjectContent;
            oldTraining.update(content);
        }
        else
            sportObjectContent.update(content);
        return sportObject;
    }

    private void mapObjectListToHashMap() {
        objectReferences = sportObjects.stream()
                .collect(Collectors.toMap(SportObject::getId, Function.identity()));
    }

    public SportObject getReferenceById(int id) {
        if(objectReferences.containsKey(id)) {
            return objectReferences.get(id);
        }
        return null;
    }

    private ArrayList<WorkDay> getGenericWorkTime() {
        return new ArrayList<>(Stream.of(
                new WorkDay("Monday", new WorkTime(LocalTime.of(8, 0), LocalTime.of(21, 0))),
                new WorkDay("Tuesday", new WorkTime(LocalTime.of(8, 0), LocalTime.of(21, 0))),
                new WorkDay("Wednesday", new WorkTime(LocalTime.of(8, 0), LocalTime.of(21, 0))),
                new WorkDay("Thursday", new WorkTime(LocalTime.of(8, 0), LocalTime.of(21, 0))),
                new WorkDay("Friday", new WorkTime(LocalTime.of(8, 0), LocalTime.of(21, 0))),
                new WorkDay("Saturday", new WorkTime(LocalTime.of(8, 0), LocalTime.of(21, 0))),
                new WorkDay("Sunday", new WorkTime(LocalTime.of(8, 0), LocalTime.of(21, 0)))
        ).collect(Collectors.toList())) ;
    }

}
