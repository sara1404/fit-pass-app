package Repository;

import DataHandler.SubtypeDataHandler;
import Interfaces.IUserRepository;
import Model.*;

import java.util.ArrayList;
import java.util.List;

public class UserRepository implements IUserRepository {

    private List<User> users;
    private SubtypeDataHandler<User> userDataHandler;

    public UserRepository(SubtypeDataHandler<User> userDataHandler){
        this.userDataHandler = userDataHandler;
        users = new ArrayList<>();
        users = userDataHandler.readFromFile();
    }

    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public User findByUsername(String username) {
        for(User user : users) {
            if(user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void create(User user) {
        users.add(user);
        userDataHandler.writeToFile(users);
    }


    @Override
    public void deleteByUsername(String username) {
        User user = findByUsername(username);
        users.remove(user);
        userDataHandler.writeToFile(users);
    }
    @Override
    public void update(User user) {
        User oldUser = findByUsername(user.getUsername());
        oldUser.update(user);
        userDataHandler.writeToFile(users);
    }



}
