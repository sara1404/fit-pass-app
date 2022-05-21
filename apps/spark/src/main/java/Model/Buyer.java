package Model;

import Utils.Constants;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Buyer extends User{

    private ArrayList<SportObject> visitedObjects;
    private BuyerType buyerType;
    private int points;

    public Buyer(String username, String password, String name, String surname, Constants.Sex sex, LocalDate birthDate, Constants.UserRole role, int points, BuyerType buyerType) {
        super(username, password, name, surname, sex, birthDate, role);
        visitedObjects = new ArrayList<>();
        this.points = points;
        this.buyerType = buyerType;
    }

    public ArrayList<SportObject> getVisitedObjects() {
        return visitedObjects;
    }

    public void setVisitedObjects(ArrayList<SportObject> visitedObjects) {
        this.visitedObjects = visitedObjects;
    }

    public BuyerType getBuyerType() {
        return buyerType;
    }

    public void setBuyerType(BuyerType buyerType) {
        this.buyerType = buyerType;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
