package Interfaces;

import Interfaces.Base.IGenericCRUD;
import Model.Buyer;
import Model.Coach;
import Model.User;
import Utils.Constants;

import java.util.List;

public interface IUserRepository extends IGenericCRUD<User> {

    User findByUsername(String username);
    void deleteByUsername(String username);
    List<User> findManagersWithoutSportObject();
    List<User> findCoachesForSportObject(int id);
    List<User> findBuyersThatVisitedObject(int id);
    List<User> findUsersByRole(Constants.UserRole role);

}
