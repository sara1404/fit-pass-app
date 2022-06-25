package Interfaces;

import Interfaces.Base.IGenericCRUD;
import Model.User;

import java.util.List;

public interface IUserRepository extends IGenericCRUD<User> {

    User findByUsername(String username);
    void deleteByUsername(String username);
    List<User> findManagersWithoutSportObject();


}
