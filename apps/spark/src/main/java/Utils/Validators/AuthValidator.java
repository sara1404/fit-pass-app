package Utils.Validators;

import Model.User;
import Repository.UserRepository;
import Utils.Constants;

public class AuthValidator {

    private UserRepository userRepository;

    public AuthValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void validateRegistration(User user) throws Exception {
        usernameNull(user);
        usernameEmpty(user);
        administratorNotAllowed(user);
        usernameAlreadyExists(user);
    }

    public void validateLogin(String username, String password) throws Exception{
        userWithUsernameDoesntExist(username);
        wrongPassword(username, password);
    }

    private void usernameNull(User user) throws Exception {
        if(user.getUsername() == null)
            throw new Exception("Username should be provided");
    }

    private void administratorNotAllowed(User user) throws Exception {
        if(user.getRole().equals(Constants.UserRole.ADMIN))
            throw new Exception("You can't create admin");
    }

    private void usernameAlreadyExists(User user) throws Exception{
        if(userRepository.findByUsername(user.getUsername()) != null)
            throw new Exception("User with this username already exists!");
    }

    private void userWithUsernameDoesntExist(String username) throws Exception {
        if(userRepository.findByUsername(username) == null)
            throw new Exception("Wrong password or username!");
    }

    private void wrongPassword(String username, String password) throws Exception {
        User user = userRepository.findByUsername(username);
        if(user == null) throw new Exception("User does not exist!");
        if(!user.getPassword().equals(password)) throw new Exception("Wrong password or username!");
    }

    private void usernameEmpty(User user) throws Exception {
        if(user.getUsername().trim().equals(""))
            throw new Exception("Username can't be empty!");
    }


}
