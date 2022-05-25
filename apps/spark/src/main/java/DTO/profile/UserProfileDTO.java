package DTO.profile;

import Model.*;
import Utils.Constants;

import java.time.LocalDate;

public abstract class UserProfileDTO {

    public String username;
    public String name;
    public String surname;
    public Constants.Sex sex;
    public LocalDate birthDate;
    public Constants.UserRole role;

    protected UserProfileDTO(User user) {
        username = user.getUsername();
        name = user.getName();
        surname = user.getSurname();
        sex = user.getSex();
        birthDate = user.getBirthDate();
        role = user.getRole();
    }

    public static UserProfileDTO createProfile(User user) {
        if(user instanceof Manager) {
            return new ManagerProfileDTO((Manager)user);
        }

        if(user instanceof Coach) {
            return new CoachProfileDTO((Coach) user);
        }

        if(user instanceof Administrator) {
            return new AdministratorProfileDTO((Administrator) user);
        }

        return new BuyerProfileDTO((Buyer) user);
    }

}
