package Utils.SearchImpl.FilterImpl.UserFilters;

import Interfaces.SearchFiltering.IFilter;
import Model.Buyer;
import Model.User;
import Utils.Constants;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class UserSortByPoints implements IFilter<User> {
    @Override
    public List<User> filter(List<User> objects, Map<String, String[]> params) {
        if(!params.containsKey("sort") || !params.get("sort")[0].equalsIgnoreCase("points"))
            return objects;
        return sortUsers(filterBuyers(objects));
    }

    private List<User> filterBuyers(List<User> users) {
        List<User> filtered = new ArrayList<>();
        for(User user : users) {
            if(user.getRole() == Constants.UserRole.BUYER) {
                filtered.add(user);
            }
        }
        return filtered;
    }

    private List<User> sortUsers(List<User> users) {
        users.sort(Comparator.comparing(User::getName));
        return users;
    }

}
