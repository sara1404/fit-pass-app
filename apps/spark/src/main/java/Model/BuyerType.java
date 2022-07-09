package Model;

import Utils.Constants;
import com.google.gson.annotations.Expose;

public class BuyerType {
    @Expose
    private Constants.BuyerTypeName name;
    @Expose
    private double discount;
    @Expose
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
