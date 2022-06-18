package Utils.SearchImpl.FilterImpl;

import Interfaces.SearchFiltering.IFilter;
import Model.SportObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SportObjectOpenFilter implements IFilter<SportObject> {
    @Override
    public List<SportObject> filter(List<SportObject> objects, Map<String, String[]> params) {
        if(!params.containsKey("status"))
            return objects;
        if(params.get("status")[0].equals("false")) return objects;
        return matchStatusCase(objects);
    }

    private List<SportObject> matchStatusCase(List<SportObject> objects) {
        List<SportObject> filteredSportObjects = new ArrayList<>();
        for(SportObject object : objects){
            if(object.getStatus().toString().toLowerCase().equals("open")){
                filteredSportObjects.add(object);
            }
        }
        return filteredSportObjects;
    }
}
