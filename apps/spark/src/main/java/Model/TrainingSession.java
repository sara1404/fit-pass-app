package Model;

public class TrainingSession extends SportObjectContent{
    private int objectId;
    private int trainingDuration;
    private Coach coach;
    private String description;

    public TrainingSession(String name, String type, String flag, int object, int trainingDuration, Coach coach, String description, String picUrl) {
        super(name, type, picUrl, flag);
        this.objectId = object;
        this.trainingDuration = trainingDuration;
        this.coach = coach;
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

    public void update(TrainingSession training){
        setType(training.getType());
        setCoach(training.getCoach());
        setFlag(training.getFlag());
        setDescription(training.getDescription());
        setTrainingDuration(training.getTrainingDuration());
        setObjectId(training.getObjectId());
        setPictureUrl(training.getPictureUrl());
    }
}
