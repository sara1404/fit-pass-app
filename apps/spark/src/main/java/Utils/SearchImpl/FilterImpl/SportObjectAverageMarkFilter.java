package Utils.SearchImpl.FilterImpl;

import Interfaces.SearchFiltering.IFilter;
import Model.SportObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SportObjectAverageMarkFilter implements IFilter<SportObject> {
    @Override
    public List<SportObject> filter(List<SportObject> objects, Map<String, String[]> params) {
        if(!params.containsKey("averageMark"))
            return objects;
        String filteredInput = params.get("averageMark")[0].trim();
        return matchAverageMarkCase(objects, filteredInput);
    }

    private List<SportObject> matchAverageMarkCase(List<SportObject> objects, String filteredInput) {
        List<SportObject> filteredSportObjects = new ArrayList<>();
        for (SportObject object : objects){
            if(String.valueOf(object.getAverageMark()).equals(filteredInput)){
                filteredSportObjects.add(object);
            }
        }
        return filteredSportObjects;
    }
}
