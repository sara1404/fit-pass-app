package Utils.SearchImpl.FilterImpl.UserFilters;

import Interfaces.SearchFiltering.IFilter;
import Model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserSurnameFilter implements IFilter<User> {
    @Override
    public List<User> filter(List<User> objects, Map<String, String[]> params) {
        if(!params.containsKey("surname")) {
            return objects;
        }
        return filterBySurname(objects, params.get("surname")[0]);
    }

    private List<User> filterBySurname(List<User> users, String surname) {
        List<User> filtered = new ArrayList<>();
        for(User user : users) {
            if(user.getSurname().toLowerCase().contains(surname.toLowerCase())) {
                filtered.add(user);
            }
        }
        return filtered;
    }
}
