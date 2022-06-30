package Model;

import java.time.LocalDateTime;

public class TrainingHistory {
    private LocalDateTime application;
    private TrainingSession trainingSession;
    private String buyerUsername;
    private String coachUsername;

    public TrainingHistory(LocalDateTime application, TrainingSession trainingSession, String buyer, String coach) {
        this.application = application;
        this.trainingSession = trainingSession;
        this.buyerUsername = buyer;
        this.coachUsername = coach;
    }

    public LocalDateTime getApplication() {
        return application;
    }

    public void setApplication(LocalDateTime application) {
        this.application = application;
    }

    public TrainingSession getTrainingSession() {
        return trainingSession;
    }

    public void setTrainingSession(TrainingSession trainingSession) {
        this.trainingSession = trainingSession;
    }

    public String getBuyer() {
        return buyerUsername;
    }

    public void setBuyer(String buyer) {
        this.buyerUsername = buyer;
    }

    public String getCoachUsername() {
        return coachUsername;
    }

    public void setCoachUsername(String coachUsername) {
        this.coachUsername = coachUsername;
    }

}
