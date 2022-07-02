package Model;

import Utils.Constants;
import com.google.gson.annotations.Expose;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TrainingSession extends SportObjectContent{
    @Expose
    private int objectId;
    @Expose
    private int trainingDuration;
    @Expose
    private String coachUsername;
    @Expose
    private String description;
    @Expose
    private int appointments;
    @Expose
    private float price;

    public TrainingSession(String name, Constants.TrainingType  type, String flag,
                           int object, int trainingDuration, String coach, String description,
                           String picUrl, List<WorkDay> workTime, int appointments, float price) {
        super(name, type, picUrl, flag, workTime);
        this.objectId = object;
        this.trainingDuration = trainingDuration;
        this.coachUsername = coach;
        this.description = description;
        this.appointments = appointments;
        this.price = price;
    }

    public int getObjectId() {
        return objectId;
    }

    public void setObjectId(int objectId) {
        this.objectId = objectId;
    }

    public int getTrainingDuration() {
        return trainingDuration;
    }

    public void setTrainingDuration(int trainingDuration) {
        this.trainingDuration = trainingDuration;
    }

    public String getCoachUsername() {
        return coachUsername;
    }

    public void setCoachUsername(String coachUsername) {
        this.coachUsername = coachUsername;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    public int getAppointments() {
        return appointments;
    }

    public void setAppointments(int appointments) {
        this.appointments = appointments;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public boolean isTrainingOnDate(LocalDateTime reservedAt) {
        DayOfWeek dayOfWeek = reservedAt.getDayOfWeek();
        for(WorkDay workDay: workTime) {
            if(workDay.getDay().equalsIgnoreCase(dayOfWeek.toString())) {
                return true;
            }
        }
        return false;
    }

    public void update(TrainingSession training){
        setType(training.getType());
        setCoachUsername(training.getCoachUsername());
        setFlag(training.getFlag());
        setDescription(training.getDescription());
        setTrainingDuration(training.getTrainingDuration());
        setObjectId(training.getObjectId());
        setPictureUrl(training.getPictureUrl());
    }
}
