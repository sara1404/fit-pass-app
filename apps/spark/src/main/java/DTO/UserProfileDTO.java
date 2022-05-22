package DTO;

import Model.User;
import Utils.Constants;

import java.time.LocalDate;

public class UserProfileDTO {

    public String username;
    public String name;
    public String surname;
    public Constants.Sex sex;
    public LocalDate birthDate;
    public Constants.UserRole role;

    public UserProfileDTO(User user) {
        username = user.getUsername();
        name = user.getName();
        surname = user.getSurname();
        sex = user.getSex();
        birthDate = user.getBirthDate();
        role = user.getRole();
    }

}
