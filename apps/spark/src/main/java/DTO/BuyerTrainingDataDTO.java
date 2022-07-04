package DTO;

import Model.SportObject;
import Model.TrainingHistory;
import Model.TrainingSession;

import java.time.LocalDateTime;

public class BuyerTrainingDataDTO {
    public TrainingHistory history;
    public SportObject object;

    public BuyerTrainingDataDTO(TrainingHistory history, SportObject sportObject) {
        this.history = history;
        this.object = sportObject;
    }


}
