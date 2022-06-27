package Utils.SearchImpl.FilterImpl.UserFilters;

import Interfaces.SearchFiltering.IFilter;
import Model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class UserNameFilter implements IFilter<User> {
    @Override
    public List<User> filter(List<User> objects, Map<String, String[]> params) {
        if(!params.containsKey("name")) {
            return objects;
        }
        return filterByName(objects, params.get("name")[0]);
    }

    private List<User> filterByName(List<User> users, String name) {
        List<User> filtered = new ArrayList<>();
        for(User user : users) {
            if(user.getName().toLowerCase().contains(name.toLowerCase())) {
                filtered.add(user);
            }
        }
        return filtered;
    }
}
