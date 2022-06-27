package Interfaces.Repository;

import Interfaces.Base.IGenericCRUD;
import Model.Buyer;
import Model.Coach;
import Model.User;

import java.util.List;

public interface IUserRepository extends IGenericCRUD<User> {

    User findByUsername(String username);
    void deleteByUsername(String username);
    List<User> findManagersWithoutSportObject();
    List<User> findCoachesForSportObject(int id);
    List<User> findBuyersThatVisitedObject(int id);

}
