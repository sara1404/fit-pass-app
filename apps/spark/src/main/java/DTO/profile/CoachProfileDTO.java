package DTO.profile;

import Model.Coach;
import Model.TrainingHistory;

import java.util.List;

public class CoachProfileDTO extends UserProfileDTO {
    private List<TrainingHistory> history;
    public CoachProfileDTO(Coach coach) {
        super(coach);
        this.history = coach.getTrainingHistory();
    }
}
