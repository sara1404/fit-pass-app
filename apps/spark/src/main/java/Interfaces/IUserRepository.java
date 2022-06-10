package Interfaces;

import Interfaces.Base.IGenericCRUD;
import Model.User;

public interface IUserRepository extends IGenericCRUD<User> {

    User findByUsername(String username);
    void deleteByUsername(String username);


}
