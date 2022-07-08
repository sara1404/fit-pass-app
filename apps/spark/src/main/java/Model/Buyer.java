package Model;

import Utils.Constants;
import com.google.gson.annotations.Expose;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static Utils.Constants.BuyerTypeName.BRONZE;

public class Buyer extends User{
    @Expose
    private ArrayList<SportObject> visitedObjects;
    @Expose
    private BuyerType buyerType;
    @Expose
    private double points;
    @Expose
    private List<TrainingHistory> trainingHistory;

    public Buyer(String username, String password, String name, String surname, Constants.Sex sex, LocalDate birthDate, Constants.UserRole role, int points, BuyerType buyerType) {
        super(username, password, name, surname, sex, birthDate, role);
        this.visitedObjects = new ArrayList<>();
        this.trainingHistory = new ArrayList<>();
        this.points = points;
        this.buyerType = buyerType;
    }

    public ArrayList<SportObject> getVisitedObjects() {
        if(visitedObjects == null) return new ArrayList<>();
        return visitedObjects;
    }

    public void setVisitedObjects(ArrayList<SportObject> visitedObjects) {
        this.visitedObjects = visitedObjects;
    }

    public BuyerType getBuyerType() {
        if(buyerType == null) {
            System.out.println("Returning default type value !");
            return new BuyerType(BRONZE, 0, 3000);
        }
        return buyerType;
    }

    public void setBuyerType(BuyerType buyerType) {
        System.out.println(buyerType);
        this.buyerType = buyerType;
    }

    public double getPoints() {
        return points;
    }

    public void setPoints(double points) {
        this.points = points;
    }

    public void addFinishedTraining(TrainingSession session, TrainingReservation reservation) {
        TrainingHistory finishedTraining = new TrainingHistory(reservation.getReservedAt(), session, reservation.getBuyerUsername(), reservation.getCoachUsername());
        trainingHistory.add(finishedTraining);
    }

    public List<TrainingHistory> getTrainingHistory() {
        return trainingHistory;
    }

    public void setTrainingHistory(List<TrainingHistory> trainingHistory) {
        this.trainingHistory = trainingHistory;
    }

    public void addVisitedObject(SportObject sportObject) {
        for(SportObject so : visitedObjects) {
            if(sportObject.getId() == sportObject.getId()) return;
        }
        visitedObjects.add(sportObject);
    }

    public boolean hasVisitedObject(int sportObjectId) {
        for(SportObject object : visitedObjects) {
            if(object.getId() == sportObjectId) {
                return true;
            }
        }
        return false;
    }

    public void update(Buyer buyer) {
        super.update(buyer);
        setVisitedObjects(buyer.visitedObjects);
        setBuyerType(buyer.getBuyerType());
        setPoints(buyer.points);
    }
}
