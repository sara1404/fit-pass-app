package Utils.SearchImpl.FilterImpl.SportObjectFilters;

import Interfaces.SearchFiltering.IFilter;
import Model.SportObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SportObjectCityFilter implements IFilter<SportObject> {
    public List<SportObject> filter(List<SportObject> objects, Map<String, String[]> params) {
        if(!params.containsKey("city"))
            return objects;
        if(params.get("city")[0].trim().startsWith("Choose"))
            return objects;
        String filterInput = params.get("city")[0].trim();
        return matchLocationCase(objects, filterInput);
    }

    private List<SportObject> matchLocationCase(List<SportObject> objects, String filterInput) {
        List<SportObject> filteredSportObjects = new ArrayList<>();
        for(SportObject object : objects){
            if(object.isFromCity(filterInput)){
                filteredSportObjects.add(object);
            }
        }
        return filteredSportObjects;
    }
}
