package Model;

import Utils.Constants;

public class TrainingSession extends SportObjectContent{
    private int objectId;
    private int trainingDuration;
    private String coachUsername;
    private String description;

    public TrainingSession(String name, Constants.TrainingType  type, String flag, int object, int trainingDuration, String coach, String description, String picUrl) {
        super(name, type, picUrl, flag);
        this.objectId = object;
        this.trainingDuration = trainingDuration;
        this.coachUsername = coach;
        this.description = description;
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
