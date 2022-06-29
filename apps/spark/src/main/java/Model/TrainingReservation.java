package Model;

import Utils.Constants;

import java.time.LocalDateTime;

public class TrainingReservation {
    private int id;
    private String coachUsername;
    private String buyerUsername;
    private int sportObjectId;
    private LocalDateTime reservedAt;
    private Constants.TrainingType type;
    private boolean isCanceled;

    public TrainingReservation(String coachUsername, String buyerUsername, int sportObjectId, LocalDateTime reservedAt, Constants.TrainingType type) {
        this.coachUsername = coachUsername;
        this.buyerUsername = buyerUsername;
        this.sportObjectId = sportObjectId;
        this.reservedAt = reservedAt;
        this.type = type;
        this.isCanceled = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCoachUsername() {
        return coachUsername;
    }

    public void setCoachUsername(String coachUsername) {
        this.coachUsername = coachUsername;
    }

    public String getBuyerUsername() {
        return buyerUsername;
    }

    public void setBuyerUsername(String buyerUsername) {
        this.buyerUsername = buyerUsername;
    }

    public int getSportObjectId() {
        return sportObjectId;
    }

    public void setSportObjectId(int sportObjectId) {
        this.sportObjectId = sportObjectId;
    }

    public LocalDateTime getReservedAt() {
        return reservedAt;
    }

    public void setReservedAt(LocalDateTime reservedAt) {
        this.reservedAt = reservedAt;
    }

    public Constants.TrainingType getType() {
        return type;
    }

    public void setType(Constants.TrainingType type) {
        this.type = type;
    }

    public boolean isCanceled() {
        return isCanceled;
    }

    public void setCanceled(boolean canceled) {
        isCanceled = canceled;
    }

    public void update(TrainingReservation reservation) {
        setReservedAt(reservation.reservedAt);
        setCoachUsername(reservation.coachUsername);
        setSportObjectId(reservation.sportObjectId);
        setType(reservation.type);
    }
}
