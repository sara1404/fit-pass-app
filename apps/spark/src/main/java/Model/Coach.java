package Model;

import Utils.Constants;
import com.google.gson.annotations.Expose;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Coach extends User {

    @Expose
    private ArrayList<TrainingHistory> trainingHistory;

    public Coach(String username, String password, String name, String surname, Constants.Sex sex, LocalDate birthDate, Constants.UserRole role) {
        super(username, password, name, surname, sex, birthDate, role);
        this.trainingHistory = new ArrayList<>();
    }

    public ArrayList<TrainingHistory> getTrainingHistory() {
        return trainingHistory;
    }

    public void setTrainingHistory(ArrayList<TrainingHistory> trainingHistory) {
        this.trainingHistory = trainingHistory;
    }

    public boolean isWorkingInObject(int sportObjectId) {
        for(TrainingHistory session : trainingHistory) {
            if(session.getTrainingSession().getObjectId() == sportObjectId) {
                return true;
            }
        }
        return false;
    }

    public void addTrainingToHistory(TrainingSession session, TrainingReservation reservation) {
        System.out.println(reservation + " reservation");
        TrainingHistory history = new TrainingHistory(reservation.getReservedAt(), session, reservation.getBuyerUsername(), getUsername());
        trainingHistory.add(history);
    }
}
