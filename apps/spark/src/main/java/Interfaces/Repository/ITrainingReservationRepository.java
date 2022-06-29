package Interfaces.Repository;

import Interfaces.Base.IGenericCRUD;
import Model.TrainingReservation;

import java.util.List;

public interface ITrainingReservationRepository extends IGenericCRUD<TrainingReservation> {
    public List<TrainingReservation> findAllByCoachUsername(String username);
    public List<TrainingReservation> findAllByBuyerUsername(String username);
    public TrainingReservation findById(int id);
    public void deleteById(int id);
}
