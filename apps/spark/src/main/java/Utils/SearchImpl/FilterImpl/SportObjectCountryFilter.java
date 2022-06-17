package Utils.SearchImpl.FilterImpl;

import Interfaces.SearchFiltering.IFilter;
import Model.SportObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SportObjectCountryFilter implements IFilter<SportObject> {
    @Override
    public List<SportObject> filter(List<SportObject> objects, Map<String, String[]> params) {
        if(!params.containsKey("country"))
            return objects;
        String filterInput = params.get("country")[0].trim();
        return matchLocationCase(objects, filterInput);
    }

    private List<SportObject> matchLocationCase(List<SportObject> objects, String filterInput) {
        List<SportObject> filteredSportObjects = new ArrayList<>();
        for(SportObject object : objects){
            if(object.isFromCountry(filterInput)){
                filteredSportObjects.add(object);
            }
        }
        return filteredSportObjects;
    }
}
