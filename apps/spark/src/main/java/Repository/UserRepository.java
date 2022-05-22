package Repository;

import Exceptions.AuthException;
import Model.User;
import Utils.Constants;

import java.time.LocalDate;
import java.util.ArrayList;

public class UserRepository {

    private ArrayList<User> users;

    public UserRepository() {

        users = new ArrayList<>();
        users.add(new User("srdjan123", "123", "srdjan", "stjepanovic", Constants.Sex.MALE, LocalDate.of(2000, 10, 12), Constants.UserRole.ADMIN));
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

    public User login(String username, String password) throws Exception{
        User user = findUserByUsername(username);
        if(user == null) throw new Exception("User doesn't exist!");
        if(!user.getPassword().equals(password)) throw new Exception("Password is wrong!");
        return user;
    }

}
