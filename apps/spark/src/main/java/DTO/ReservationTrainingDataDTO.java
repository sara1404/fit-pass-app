package DTO;

import Model.SportObject;
import Model.TrainingReservation;

public class ReservationTrainingDataDTO {

    public TrainingReservation reservation;
    public SportObject sportObject;

    public ReservationTrainingDataDTO(TrainingReservation reservation, SportObject sportObject) {
        this.reservation = reservation;
        SportObject object = new SportObject();
        object.setId(sportObject.getId());
        object.setName(sportObject.getName());
        object.setType(sportObject.getType());
        this.sportObject = object;
    }
}
