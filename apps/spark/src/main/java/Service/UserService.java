package Service;

import Interfaces.Repository.ISportObjectRepository;
import Interfaces.Repository.IUserRepository;
import Model.*;
import Utils.Constants;
import Utils.SearchImpl.FilterImpl.SportObjectFilters.SortByAscDesc;
import Utils.SearchImpl.FilterImpl.TrainingHistoryFilters.*;
import Utils.SearchImpl.FilterImpl.UserFilters.*;
import Utils.SearchImpl.TrainingHistoryPipeline;
import Utils.SearchImpl.UserPipeline;
import Utils.Validators.UserValidator;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UserService {

    private IUserRepository userRepository;
    private ISportObjectRepository sportObjectRepository;
    private final UserValidator userValidator;
    private final UserPipeline userPipeline;
    private TrainingHistoryPipeline trainingHistoryPipeline;

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

        trainingHistoryPipeline = new TrainingHistoryPipeline();
        trainingHistoryPipeline
                .addFilter(new TrainingHistoryPriceFilter())
                .addFilter(new TrainingHistoryReservedAtFilter())
                .addFilter(new TrainingHistoryObjectFilter(sportObjectRepository))
                .addFilter(new TrainingHistoryObjectTypeFilter(sportObjectRepository))
                .addFilter(new TrainingHistoryTrainingTypeFilter())
                .addFilter(new TrainingHistorySortByDate())
                .addFilter(new TrainingHistorySortByObject(sportObjectRepository))
                .addFilter(new TrainingHistorySortByPrice())
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

    public List<TrainingHistory> getBuyerTrainingHistory(String buyerUsername, Map<String, String[]> params) {
        Buyer buyer = (Buyer)userRepository.findByUsername(buyerUsername);
        List<TrainingHistory> history = trainingHistoryPipeline.filterAll(buyer.getTrainingHistory(), params);
        return filterLastMonthTrainings(history);
    }

    public List<TrainingHistory> getCoachTrainingHistory(String coachUsername, Map<String, String[]> params) {
        Coach coach = (Coach) userRepository.findByUsername(coachUsername);
        List<TrainingHistory> history = trainingHistoryPipeline.filterAll(coach.getTrainingHistory(), params);
        return filterLastMonthTrainings(history);
    }

    public List<User> findCoachesForSportObject(int id) {
        return userRepository.findCoachesForSportObject(id);
    }

    public List<User> findBuyersThatVisitedSportObject(int id) {
        return userRepository.findBuyersThatVisitedObject(id);
    }

    private List<TrainingHistory> filterLastMonthTrainings(List<TrainingHistory> history) {
        LocalDateTime monthAgoDate = LocalDateTime.now().minusMonths(1);
        return history.stream()
                .filter(hist -> hist.getApplication().isAfter(monthAgoDate))
                .collect(Collectors.toList());
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
