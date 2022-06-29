package Service;

import Interfaces.Repository.ITrainingReservationRepository;
import Model.TrainingReservation;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class TrainingReservationService {

    private ITrainingReservationRepository trainingReservationRepository;

    public TrainingReservationService(ITrainingReservationRepository trainingReservationRepository) {
        this.trainingReservationRepository = trainingReservationRepository;
    }


    public void create(TrainingReservation reservation) {
        trainingReservationRepository.create(reservation);
    }

    public void update(TrainingReservation reservation) {
        trainingReservationRepository.update(reservation);
    }

    public List<TrainingReservation> findAll() {
        return trainingReservationRepository.findAll();
    }

    public List<TrainingReservation> findAllByCoachUsername(String username) {
        return trainingReservationRepository.findAllByCoachUsername(username);
    }

    public List<TrainingReservation> findAllByBuyerUsername(String username) {
        return trainingReservationRepository.findAllByBuyerUsername(username);
    }

    public void cancelTraining(int id) throws Exception {
        TrainingReservation reservation = trainingReservationRepository.findById(id);
        if(isMoreThanTwoDaysAfterNow(reservation.getReservedAt())) {
            reservation.setCanceled(true);
            trainingReservationRepository.update(reservation);
            return;
        }
        throw new Exception("You can't cancel training that is in less than 2 days!");
    }

    private boolean isMoreThanTwoDaysAfterNow(LocalDateTime reservedDate) {
        Duration duration = Duration.between(LocalDateTime.now(), reservedDate);
        float days = duration.toDays();
        System.out.println("Duration is " + days);
        return days > 2;
    }

}
