package Utils.SearchImpl.FilterImpl.SportObjectFilters;

import Interfaces.SearchFiltering.IFilter;
import Model.SportObject;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class SortBySportObjectCountry implements IFilter<SportObject> {
    @Override
    public List<SportObject> filter(List<SportObject> objects, Map<String, String[]> params) {
        if(!params.containsKey("sort"))
            return objects;
        if(!params.get("sort")[0].trim().equals("Country")) return objects;
        objects.sort(Comparator.comparing(SportObject -> SportObject.getLocation().getAddress().getCountry()));
        return objects;
    }
}
