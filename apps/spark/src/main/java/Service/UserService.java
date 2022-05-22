package Service;

import DTO.UserProfileDTO;
import Model.User;
import Repository.UserRepository;

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

    public List<UserProfileDTO> findAll() {
        return userRepository.findAll();
    }

    public void deleteByUsername(String username){
        userRepository.deleteByUsername(username);
    }

}
