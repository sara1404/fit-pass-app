package Model;

import Utils.Constants;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Manager extends User {
    private SportObject sportObject;

    public Manager(String username, String password, String name, String surname, Constants.Sex sex, LocalDate birthDate, Constants.UserRole role, SportObject sportObject) {
        super(username, password, name, surname, sex, birthDate, role);
        this.sportObject = sportObject;
    }

    public SportObject getSportObject() {
        return sportObject;
    }

    public void setSportObject(SportObject sportObject) {
        this.sportObject = sportObject;
    }
}
