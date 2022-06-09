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
//
//
//        SportObject sportObject = new SportObject("Objekat1",
//                Constants.SportObjectType.GYM,
//                Constants.SportObjectOffer.GROUP_TRAINING,
//                Constants.SportObjectStatus.OPEN,
//                new Location(1, 1, new Address("janka cmelika", "56", "Novi Sad", 21000)),
//                "logo",
//                9,
//                new WorkTime(LocalDateTime.now(), LocalDateTime.now())
//        );
//
//        for(User usr : users) {
//            if(usr instanceof Manager) {
//                ((Manager) usr).setSportObject(sportObject);
//            }
//        }
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
    }

    public void deleteByUsername(String username) {
        User user = findByUsername(username);
        users.remove(user);
    }

    public void update(User user) {
        User oldUser = findByUsername(user.getUsername());
        oldUser.update(user);
    }

    public List<User> findAll() {
        return users;
    }


}
