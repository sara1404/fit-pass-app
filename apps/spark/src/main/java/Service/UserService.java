package Service;

import DTO.profile.UserProfileDTO;
import Model.*;
import Repository.UserRepository;
import Utils.Constants;

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
        return userRepository.findByUsername(username);
    }


    public List<User> findAll() {
        return userRepository.findAll();
    }

    public List<UserProfileDTO> mapUsersToProfiles() {
        List<UserProfileDTO> profiles = new ArrayList<UserProfileDTO>();
        List<User> users = findAll();

        for(User user : users) {
            profiles.add(UserProfileDTO.createProfile(user));
        }
        return profiles;
    }

    public void deleteByUsername(String username){
        userRepository.deleteByUsername(username);
    }

    private Constants.UserRole findRoleByUsername(String username) throws Exception {
        User user = findByUsername(username);
        if(user == null) throw new Exception("User does not exist!");
        return user.getRole();
    }
    private Class<? extends User> determineClassByRole(Constants.UserRole role) {
        if(role == Constants.UserRole.BUYER) return Buyer.class;
        if(role == Constants.UserRole.ADMIN) return Administrator.class;
        if(role == Constants.UserRole.MANAGER) return Manager.class;
        return Coach.class;
    }

    public Class<? extends User> getClassByUsername(String username) throws Exception {
        Constants.UserRole role = findRoleByUsername(username);
        return determineClassByRole(role);
    }
}
