package Utils.SearchImpl.FilterImpl;

import Interfaces.SearchFiltering.IFilter;
import Model.SportObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class SportObjectNameFilter implements IFilter<SportObject> {
    @Override
    public List<SportObject> filter(List<SportObject> objects, Map<String, String[]> params) {
        if(!params.containsKey("name"))
            return objects;
        String filterInput = params.get("name")[0].trim();
        return matchNameCase(objects, filterInput);
    }

    private List<SportObject> matchNameCase(List<SportObject> objects, String filterInput) {
        List<SportObject> filteredSportObjects = new ArrayList<>();
        for(SportObject obj : objects){
            if(obj.getName().toLowerCase().contains(filterInput.toLowerCase()))
                filteredSportObjects.add(obj);
        }
        return filteredSportObjects;
    }
}
