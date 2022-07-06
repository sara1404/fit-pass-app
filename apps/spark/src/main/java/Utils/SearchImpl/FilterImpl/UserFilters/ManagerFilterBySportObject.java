package Utils.SearchImpl.FilterImpl.UserFilters;

import Interfaces.SearchFiltering.IFilter;
import Model.Manager;
import Model.User;
import Utils.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ManagerFilterBySportObject implements IFilter<User> {

    @Override
    public List<User> filter(List<User> objects, Map<String, String[]> params) {
        if(!params.containsKey("managerHasObject")) {
            return objects;
        }
        try {
            boolean hasObject = Boolean.parseBoolean(params.get("managerHasObject")[0]);
            return filterByObject(objects,hasObject);
        } catch(Exception e) {
            return objects;
        }
    }

    private List<User> filterByObject(List<User> users, boolean hasObject) {
        List<User> filtered = new ArrayList<>();

        for(User user : users) {
            if(user.getRole() != Constants.UserRole.MANAGER) continue;
            Manager manager = (Manager) user;
            if(hasObject && manager.getSportObject() != null) {
                filtered.add(user);
            } else if(!hasObject && manager.getSportObject() == null) {
                filtered.add(user);
            }
        }
        return filtered;
    }
}
