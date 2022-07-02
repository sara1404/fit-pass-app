package Interfaces.Repository;

import Interfaces.Base.IGenericCRUD;
import Model.TrainingReservation;

import java.util.List;

public interface ITrainingReservationRepository extends IGenericCRUD<TrainingReservation> {
     List<TrainingReservation> findAllByCoachUsername(String username);
     List<TrainingReservation> findAllByBuyerUsername(String username);
     List<TrainingReservation> findAllBySportObjectId(int sportObjectId);
     TrainingReservation findById(int id);
     void deleteById(int id);
}
