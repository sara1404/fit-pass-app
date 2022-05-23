package Model;

import Utils.Constants;

public class TrainingSession {
    private String name;
    private Constants.TrainingType type;
    private SportObject object;
    private int trainingDuration;
    private Coach coach;
    private String description;
    private String picUrl;

    public TrainingSession(String name, Constants.TrainingType type, SportObject object, int trainingDuration, Coach coach, String description, String picUrl) {
        this.name = name;
        this.type = type;
        this.object = object;
        this.trainingDuration = trainingDuration;
        this.coach = coach;
        this.description = description;
        this.picUrl = picUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Constants.TrainingType getType() {
        return type;
    }

    public void setType(Constants.TrainingType type) {
        this.type = type;
    }

    public SportObject getObject() {
        return object;
    }

    public void setObject(SportObject object) {
        this.object = object;
    }

    public int getTrainingDuration() {
        return trainingDuration;
    }

    public void setTrainingDuration(int trainingDuration) {
        this.trainingDuration = trainingDuration;
    }

    public Coach getCoach() {
        return coach;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }
}
