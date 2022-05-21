package Model;

import Utils.Constants;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class User {

    private String username;
    private String password;
    private String name;
    private String surname;
    private Constants.Sex sex;
    private LocalDate birthDate;
    private Constants.UserRole role;


    public User(String username, String password, String name, String surname, Constants.Sex sex, LocalDate birthDate, Constants.UserRole role) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.sex = sex;
        this.birthDate = birthDate;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Constants.Sex getSex() {
        return sex;
    }

    public void setSex(Constants.Sex sex) {
        this.sex = sex;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Constants.UserRole getRole() {
        return role;
    }

    public void setRole(Constants.UserRole role) {
        this.role = role;
    }
}
