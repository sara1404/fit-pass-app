package Service;

import Interfaces.ISportObjectRepository;
import Interfaces.IUserRepository;
import Model.*;
import Utils.Constants;
import Utils.SearchImpl.FilterImpl.SortByAscDesc;
import Utils.SearchImpl.FilterImpl.UserFilters.*;
import Utils.SearchImpl.UserPipeline;
import Utils.Validators.UserValidator;

import java.util.List;
import java.util.Map;

public class UserService {

    private IUserRepository userRepository;
    private ISportObjectRepository sportObjectRepository;
    private final UserValidator userValidator;
    private final UserPipeline userPipeline;

    public UserService(IUserRepository userRepository, ISportObjectRepository sportObjectRepository) {
        userPipeline = new UserPipeline();
        userPipeline
                .addFilter(new UserRoleTypeFilter())
                .addFilter(new UserUsernameFilter())
                .addFilter(new UserNameFilter())
                .addFilter(new UserSurnameFilter())
                .addFilter(new UserSortByName())
                .addFilter(new UserSortBySurname())
                .addFilter(new UserSortByUsername())
                .addFilter(new UserSortByPoints())
                .addFilter(new SortByAscDesc<>());
        this.userValidator = new UserValidator(userRepository);
        this.userRepository = userRepository;
        this.sportObjectRepository = sportObjectRepository;
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

    public List<User> findAll(Map<String, String[]> params) {
        return userPipeline.filterAll(userRepository.findAll(), params);
    }

    public List<User> getUsersByRole(Constants.UserRole role){
        return userRepository.findUsersByRole(role);
    }

    public void deleteByUsername(String username){
        userRepository.deleteByUsername(username);
    }

    public void registerObjectToManager(String username, int objectId) throws Exception {
        userValidator.validateManagerObjectRegistration(username);
        Manager manager = (Manager)findByUsername(username);
        SportObject sportObject = sportObjectRepository.findById(objectId);
        manager.setSportObject(sportObject);
        userRepository.update(manager);
    }

    public List<User> findCoachesForSportObject(int id) {
        return userRepository.findCoachesForSportObject(id);
    }

    public List<User> findBuyersThatVisitedSportObject(int id) {
        return userRepository.findBuyersThatVisitedObject(id);
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
