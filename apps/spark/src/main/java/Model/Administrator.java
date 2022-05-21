package Model;

import Utils.Constants;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Administrator extends User {

    public Administrator(String username, String password, String name, String surname, Constants.Sex sex, LocalDate birthDate, Constants.UserRole role) {
        super(username, password, name, surname, sex, birthDate, role);
    }
}
