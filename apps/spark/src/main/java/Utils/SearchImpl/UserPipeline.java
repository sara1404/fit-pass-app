package Utils.SearchImpl;

import Interfaces.SearchFiltering.IFilter;
import Interfaces.SearchFiltering.IFilterPipeline;
import Model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserPipeline implements IFilterPipeline<User> {
    private List<IFilter<User>> filters;

    public UserPipeline() {
        filters = new ArrayList<>();
    }

    @Override
    public List<User> filterAll(List<User> objects, Map<String, String[]> params) {
        List<User> filtered = new ArrayList(objects);
        for(IFilter<User> filter: filters) {
            filtered = filter.filter(filtered, params);
        }
        return filtered;
    }

    @Override
    public IFilterPipeline<User> addFilter(IFilter<User> filter) {
       filters.add(filter);
       return this;
    }
}
