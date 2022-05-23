package Service;

import DTO.UserProfileDTO;
import Model.User;
import Repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userService) {
        this.userRepository = userService;
    }

    public void create(User user) {
        userRepository.create(user);
    }

    public void update(User user) {
        userRepository.update(user);
    }

    public User findByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public List<UserProfileDTO> mapUsersToProfiles() {
        List<UserProfileDTO> profiles = new ArrayList<UserProfileDTO>();
        List<User> users = findAll();

        for(User user : users) {
            profiles.add(new UserProfileDTO(user));
        }
        return profiles;
    }

    public void deleteByUsername(String username){
        userRepository.deleteByUsername(username);
    }

}
