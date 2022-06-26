package Model;

import java.time.LocalDateTime;

public class TrainingHistory {
    private LocalDateTime application;
    private TrainingSession trainingSession;
    private Buyer buyer;
    private String coachUsername;

    public TrainingHistory(LocalDateTime application, TrainingSession trainingSession, Buyer buyer, String coach) {
        this.application = application;
        this.trainingSession = trainingSession;
        this.buyer = buyer;
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

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public String getCoachUsername() {
        return coachUsername;
    }

    public void setCoachUsername(String coachUsername) {
        this.coachUsername = coachUsername;
    }
}
