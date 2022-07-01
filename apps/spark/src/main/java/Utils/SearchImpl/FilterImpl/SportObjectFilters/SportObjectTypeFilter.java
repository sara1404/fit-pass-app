package Utils.SearchImpl.FilterImpl.SportObjectFilters;

import Interfaces.SearchFiltering.IFilter;
import Model.SportObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class SportObjectTypeFilter implements IFilter<SportObject> {

    @Override
    public List<SportObject> filter(List<SportObject> objects, Map<String, String[]> params) {
        if(!params.containsKey("type"))
            return objects;
        if(params.get("type")[0].trim().startsWith("Choose"))
            return objects;
        String filterInput = params.get("type")[0].trim();
        return matchTypeCase(objects, filterInput);
    }

    private List<SportObject> matchTypeCase(List<SportObject> objects, String filterInput) {
        List<SportObject> filteredSportObjects = new ArrayList<>();
        for(SportObject object : objects){
            if(object.getType().toString().toLowerCase().contains(filterInput.toLowerCase()))
                filteredSportObjects.add(object);
        }
        return filteredSportObjects;
    }
}
