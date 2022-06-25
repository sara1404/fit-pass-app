package Utils.SearchImpl.FilterImpl.UserFilters;

import Interfaces.SearchFiltering.IFilter;
import Model.User;
import Utils.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ManagerTypeFilter implements IFilter<User> {
    @Override
    public List<User> filter(List<User> objects, Map<String, String[]> params) {
        if(!params.containsKey("type") || !params.get("type")[0].equals("MANAGER")) {
            return objects;
        }
        return filterManagers(objects);
    }

    private List<User> filterManagers(List<User> users) {
        List<User> managers = new ArrayList<>();
        for(User user : users) {
            if(user.getRole() == Constants.UserRole.MANAGER)
                managers.add(user);
        }
        return managers;
    }
}
