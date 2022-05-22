package DTO;

import Model.Buyer;
import Model.BuyerType;
import Utils.Constants;

import java.time.LocalDate;

public class BuyerDTO {
    public String username;
    public String name;
    public String surname;
    public Constants.Sex sex;
    public LocalDate birthDate;
    public Constants.UserRole role;
    public BuyerType buyerType;
    public int points;

    public BuyerDTO(Buyer buyer) {
        this.username = buyer.getUsername();
        this.name = buyer.getName();
        this.surname = buyer.getSurname();
        this.sex = buyer.getSex();
        this.birthDate = buyer.getBirthDate();
        this.role = buyer.getRole();
        this.buyerType = buyer.getBuyerType();
        this.points = buyer.getPoints();
    }
}
