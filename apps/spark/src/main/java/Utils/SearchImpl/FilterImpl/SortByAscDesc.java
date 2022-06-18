package Utils.SearchImpl.FilterImpl;

import Interfaces.SearchFiltering.IFilter;
import Model.SportObject;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class SortByAscDesc implements IFilter<SportObject> {
    @Override
    public List<SportObject> filter(List<SportObject> objects, Map<String, String[]> params) {
        if(!params.containsKey("sortType")) return objects;
        if(params.get("sortType")[0].trim().equals("ascending"))
            Collections.reverse(objects);
        return objects;
    }
}
