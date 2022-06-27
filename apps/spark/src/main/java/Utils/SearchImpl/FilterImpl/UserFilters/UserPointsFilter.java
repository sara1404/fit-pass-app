package Utils.SearchImpl.FilterImpl.UserFilters;

import Interfaces.SearchFiltering.IFilter;
import Model.Buyer;
import Model.User;
import Utils.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserPointsFilter implements IFilter<User> {
    @Override
    public List<User> filter(List<User> objects, Map<String, String[]> params) {
        if(!params.containsKey("points")) {
            return objects;
        }
        return filterByPoints(objects, Integer.parseInt(params.get("points")[0]));
    }

    private List<User> filterByPoints(List<User> users, int points) {
        List<User> filtered = new ArrayList<>();
        for(User user : users) {
            if(user.getRole() != Constants.UserRole.BUYER) continue;
            if(((Buyer)user).getPoints() == points) {
                filtered.add(user);
            }
        }
        return filtered;
    }
}
