package Utils.SearchImpl.FilterImpl.UserFilters;


import Interfaces.SearchFiltering.IFilter;
import Model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserRoleTypeFilter implements IFilter<User> {
    @Override
    public List<User> filter(List<User> objects, Map<String, String[]> params) {
        if(!params.containsKey("type")) {
            return objects;
        }
        return filterByRole(objects, params.get("type")[0]);
    }

    private List<User> filterByRole(List<User> users, String role) {
        List<User> filtered = new ArrayList<>();
        for(User user : users) {
            if(user.getRole().toString().equalsIgnoreCase(role)) {
                filtered.add(user);
            }
        }
        return filtered;
    }
}
