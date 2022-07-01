package Utils.SearchImpl.FilterImpl.SportObjectFilters;

import Interfaces.SearchFiltering.IFilter;
import Model.SportObject;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class SortByAscDesc<T> implements IFilter<T> {
    @Override
    public List<T> filter(List<T> objects, Map<String, String[]> params) {
        if(!params.containsKey("sortType")) return objects;
        if(params.get("sortType")[0].trim().equals("ascending"))
            Collections.reverse(objects);
        return objects;
    }
}
