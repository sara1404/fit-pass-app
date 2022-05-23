package Model;

import java.time.LocalDateTime;

public class TrainingHistory {
    private LocalDateTime application;
    private TrainingSession trainingSession;
    private Buyer buyer;
    private Coach coach;

    public TrainingHistory(LocalDateTime application, TrainingSession trainingSession, Buyer buyer, Coach coach) {
        this.application = application;
        this.trainingSession = trainingSession;
        this.buyer = buyer;
        this.coach = coach;
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

    public Coach getCoach() {
        return coach;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }
}
