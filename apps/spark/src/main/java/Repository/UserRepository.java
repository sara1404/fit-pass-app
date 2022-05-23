package Repository;

import DTO.UserProfileDTO;
import DataHandler.DataHandler;
import Model.Administrator;
import Model.Buyer;
import Model.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    private List<User> users;
    private DataHandler<User> userDataHandler;

    public UserRepository(DataHandler<User> userDataHandler) {
        this.userDataHandler = userDataHandler;
        users = new ArrayList<>();
        users = userDataHandler.readFromFile();
    }

    public User findUserByUsername(String username) {
        for(User user : users) {
            if(user.getUsername().equals(username)) {
                return user;
            }
        }

        return null;
    }


    public void create(User user) {
        users.add(user);
    }

    public void deleteByUsername(String username) {
        User user = findUserByUsername(username);
        users.remove(user);
    }

    public void update(User user) {
        User oldUser = findUserByUsername(user.getUsername());
        oldUser.update(user);
    }

    public List<User> findAll() {
        return users;
    }


}
