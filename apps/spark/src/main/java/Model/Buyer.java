package Model;

import Utils.Constants;
import com.google.gson.annotations.Expose;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Buyer extends User{
    @Expose
    private ArrayList<SportObject> visitedObjects;
    @Expose
    private BuyerType buyerType;
    @Expose
    private int points;
    @Expose
    private List<TrainingHistory> finishedTrainings;

    public Buyer(String username, String password, String name, String surname, Constants.Sex sex, LocalDate birthDate, Constants.UserRole role, int points, BuyerType buyerType) {
        super(username, password, name, surname, sex, birthDate, role);
        this.visitedObjects = new ArrayList<>();
        this.finishedTrainings = new ArrayList<>();
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

    public void addFinishedTraining(TrainingHistory finishedTraining) {
        finishedTrainings.add(finishedTraining);
    }

    public List<TrainingHistory> getFinishedTrainings() {
        return finishedTrainings;
    }

    public void setFinishedTrainings(List<TrainingHistory> finishedTrainings) {
        this.finishedTrainings = finishedTrainings;
    }

    public void addVisitedObject(SportObject sportObject) {
        for(SportObject so : visitedObjects) {
            if(sportObject.getId() == sportObject.getId()) return;
        }
        visitedObjects.add(sportObject);
    }

    public boolean hasVisitedObject(int sportObjectId) {
        for(SportObject object : visitedObjects) {
            System.out.println(object);
            if(object.getId() == sportObjectId) {
                return true;
            }
        }
        return false;
    }

    public void update(Buyer buyer) {
        super.update(buyer);
        setVisitedObjects(buyer.visitedObjects);
        setPoints(buyer.points);
    }
}
