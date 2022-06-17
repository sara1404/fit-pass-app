package Utils.SearchImpl;

import Interfaces.SearchFiltering.IFilter;
import Interfaces.SearchFiltering.IFilterPipeline;
import Model.SportObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class SportObjectsPipeline implements IFilterPipeline<SportObject> {
    ArrayList<IFilter<SportObject>> filters = new ArrayList<>();

    @Override
    public List<SportObject> filterAll(List<SportObject> objects, Map<String, String[]> params) {
        List<SportObject> filteredSportObjects = new ArrayList<>(objects);
        for(IFilter<SportObject> filter : filters){
            filteredSportObjects = filter.filter(filteredSportObjects, params);
        }
        return filteredSportObjects;
    }

    @Override
    public IFilterPipeline<SportObject> addFilter(IFilter<SportObject> filter) {
        filters.add(filter);
        return this;
    }
}
