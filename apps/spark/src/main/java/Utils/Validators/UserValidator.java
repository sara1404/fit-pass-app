package Utils.Validators;

import Interfaces.Repository.IUserRepository;
import Model.Manager;
import Utils.Constants;

public class UserValidator {

    private final IUserRepository userRepository;

    public UserValidator(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void validateManagerObjectRegistration(String username) throws Exception  {
        userDoesntExist(username);
        isNotManager(username);
        managerAlreadyHasSportObject(username);
    }

    private void isNotManager(String username) throws Exception {
        if(userRepository.findByUsername(username).getRole() != Constants.UserRole.MANAGER)  {
            throw new Exception("Not valid manager provided!");
        }
    }

    private void managerAlreadyHasSportObject(String username) throws Exception {
        Manager manager = (Manager) userRepository.findByUsername(username);
        if(manager.getSportObject() != null) {
            throw new Exception("Manager already has sport object!");
        }
    }

    private void userDoesntExist(String username) throws Exception{
        if(userRepository.findByUsername(username) == null) {
            throw new Exception("User doesn't exist!");
        }
    }
}
