package Utils.SearchImpl.FilterImpl.UserFilters;

import Interfaces.SearchFiltering.IFilter;
import Model.User;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class UserSortBySurname implements IFilter<User> {
    @Override
    public List<User> filter(List<User> objects, Map<String, String[]> params) {
        if(!params.containsKey("sort") || !params.get("sort")[0].equalsIgnoreCase("surname"))
            return objects;
        objects.sort(Comparator.comparing(User::getSurname));
        return objects;
    }
}
