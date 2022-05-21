package Model;

import Utils.Constants;

public class BuyerType {

    private Constants.BuyerTypeName name;
    private double discount;
    private int goalPoints;

    public BuyerType(Constants.BuyerTypeName name, double discount, int goalPoints) {
        this.name = name;
        this.discount = discount;
        this.goalPoints = goalPoints;
    }

    public Constants.BuyerTypeName getName() {
        return name;
    }

    public void setName(Constants.BuyerTypeName name) {
        this.name = name;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getGoalPoints() {
        return goalPoints;
    }

    public void setGoalPoints(int goalPoints) {
        this.goalPoints = goalPoints;
    }
}
