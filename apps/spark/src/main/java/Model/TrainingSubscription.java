package Model;

import Utils.Constants;

public class TrainingSubscription {

    private Constants.TrainingType type;
    private float price;
    private String coachUsername;
    private int objectId;
    private int appointmentsLeft;

    public TrainingSubscription(Constants.TrainingType type, float price, String coachUsername, int objectId, int appointmentsLeft) {
        this.type = type;
        this.price = price;
        this.coachUsername = coachUsername;
        this.objectId = objectId;
        this.appointmentsLeft = appointmentsLeft;
    }

    public Constants.TrainingType getType() {
        return type;
    }

    public void setType(Constants.TrainingType type) {
        this.type = type;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getCoachUsername() {
        return coachUsername;
    }

    public void setCoachUsername(String coachUsername) {
        this.coachUsername = coachUsername;
    }

    public int getObjectId() {
        return objectId;
    }

    public void setObjectId(int objectId) {
        this.objectId = objectId;
    }

    public int getAppointmentsLeft() {
        return appointmentsLeft;
    }

    public void setAppointmentsLeft(int appointmentsLeft) {
        this.appointmentsLeft = appointmentsLeft;
    }
}
