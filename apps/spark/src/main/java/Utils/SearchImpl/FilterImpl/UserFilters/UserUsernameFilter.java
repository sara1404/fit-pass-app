package Utils.SearchImpl.FilterImpl.UserFilters;

import Interfaces.SearchFiltering.IFilter;
import Model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserUsernameFilter implements IFilter<User> {
    @Override
    public List<User> filter(List<User> objects, Map<String, String[]> params) {
        if(!params.containsKey("username")) {
            return objects;
        }
        return filterByUsername(objects, params.get("username")[0]);
    }

    private List<User> filterByUsername(List<User> users, String username) {
        List<User> filtered = new ArrayList<>();
        for(User user : users) {
            if(user.getUsername().toLowerCase().contains(username.toLowerCase())) {
                filtered.add(user);
            }
        }
        return filtered;
    }
}
