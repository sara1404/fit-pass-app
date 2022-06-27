package Repository;

import DataHandler.TemplateDataHandler;
import Interfaces.IUserRepository;
import Model.*;
import Utils.Constants;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements IUserRepository {

    private List<User> users;
    private TemplateDataHandler<User> userDataHandler;
    private SportObjectRepository sportObjectRepository;


    public UserRepository(TemplateDataHandler<User> userDataHandler, SportObjectRepository sportObjectRepository){
        this.userDataHandler = userDataHandler;
        this.sportObjectRepository = sportObjectRepository;
        users = new ArrayList<>();
        users = userDataHandler.readFromFile();
        mapObjectReferences();
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
    public User create(User user) {
        users.add(user);
        userDataHandler.writeToFile(users);
        return user;
    }

    @Override
    public void deleteByUsername(String username) {
        User user = findByUsername(username);
        users.remove(user);
        userDataHandler.writeToFile(users);
    }

    @Override
    public List<User> findManagersWithoutSportObject() {
        List<User> managers = new ArrayList<>();
        for(Manager manager: findAllManagers()) {
            if(manager.getSportObject() != null)
                managers.add(manager);
        }
        return managers;
    }

    @Override
    public List<User> findCoachesForSportObject(int id) {
        List<User> filteredCoaches = new ArrayList<>();

        for(Coach coach: findAllCoaches()) {
            if(coach.isWorkingInObject(id)) {
                filteredCoaches.add(coach);
            }
        }
        return filteredCoaches;
    }

    @Override
    public List<User> findBuyersThatVisitedObject(int id) {
        List<User> filteredBuyers = new ArrayList<>();
        for(Buyer buyer : findAllBuyers()) {
            if(buyer.hasVisitedObject(id)) {
                filteredBuyers.add(buyer);
            }
        }
        return filteredBuyers;
    }

    @Override
    public void update(User user) {
        User oldUser = findByUsername(user.getUsername());
        oldUser.update(user);
        userDataHandler.writeToFile(users);
    }

    private List<Manager> findAllManagers() {
        List<Manager> managers = new ArrayList<>();
        for (User user : users) {
            if (user.getRole() == Constants.UserRole.MANAGER)
                managers.add((Manager) user);
        }
        return managers;
    }

    private List<Coach> findAllCoaches() {
        List<Coach> coaches = new ArrayList<Coach>();
        for(User user : users) {
            if(user.getRole() == Constants.UserRole.COACH) {
                coaches.add((Coach)user);
            }
        }
        return coaches;
    }

    private List<Buyer> findAllBuyers() {
        List<Buyer> buyers = new ArrayList<Buyer>();
        for(User user : users) {
            if(user.getRole() == Constants.UserRole.BUYER) {
                buyers.add((Buyer)user);
            }
        }
        return buyers;
    }
    @Override
    public List<User> findUsersByRole(Constants.UserRole role){
        ArrayList<User> filteredUsers = new ArrayList<>();
        for (User user : this.users){
            if(user.getRole() == role)
                filteredUsers.add(user);
        }
        return filteredUsers;
    }

    private void mapObjectReferences() {
        for(User user : users) {
            if(user instanceof Manager) {
                mapSportObjectToManager((Manager)user);
            }
            if(user instanceof Buyer) {
                mapSportObjectsToBuyer((Buyer) user);
            }
        }
    }

    private void mapSportObjectToManager(Manager manager) {
        manager.setSportObject(sportObjectRepository.getReferenceById(manager.getSportObject().getId()));
    }

    private void mapSportObjectsToBuyer(Buyer buyer) {
        for(SportObject object : buyer.getVisitedObjects()) {
            object = sportObjectRepository.getReferenceById(object.getId());
        }
    }

}
