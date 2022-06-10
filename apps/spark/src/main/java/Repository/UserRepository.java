package Repository;

import DataHandler.DataHandler;
import Interfaces.IUserRepository;
import Model.*;
import Utils.Constants;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserRepository implements IUserRepository {

    private List<User> users;
    private DataHandler<User> userDataHandler;

    public UserRepository(DataHandler<User> userDataHandler){
        this.userDataHandler = userDataHandler;
        users = new ArrayList<>();
        users = userDataHandler.readFromFile();
    }

    public User findByUsername(String username) {
        for(User user : users) {
            if(user.getUsername().equals(username)) {
                return user;
            }
        }

        return null;
    }

    public void create(User user) {
        users.add(user);
        userDataHandler.writeToFile(users);
    }

    public void deleteByUsername(String username) {
        User user = findByUsername(username);
        users.remove(user);
        userDataHandler.writeToFile(users);
    }

    public void update(User user) {
        User oldUser = findByUsername(user.getUsername());
        oldUser.update(user);
        userDataHandler.writeToFile(users);
    }

    public List<User> findAll() {
        return users;
    }


}
