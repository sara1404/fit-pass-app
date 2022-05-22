package Repository;

import DTO.UserProfileDTO;
import Exceptions.AuthException;
import Model.User;
import Utils.Constants;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    private ArrayList<User> users;

    public UserRepository() {

        users = new ArrayList<>();
        users.add(new User("srdjan123", "123", "srdjan", "stjepanovic", Constants.Sex.MALE, LocalDate.of(2000, 10, 12), Constants.UserRole.ADMIN));
        users.add(new User("sara123", "123", "Sara", "Sinjeri", Constants.Sex.MALE, LocalDate.of(2000, 4, 14), Constants.UserRole.BUYER));
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

    public List<UserProfileDTO> findAll() {
        List<UserProfileDTO> userProfiles = new ArrayList<>();
        for(User user : users) {
            userProfiles.add(new UserProfileDTO(user));
        }
        return userProfiles;
    }


}
