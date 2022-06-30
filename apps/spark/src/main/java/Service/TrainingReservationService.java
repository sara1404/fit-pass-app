package Service;

import Interfaces.Repository.ISportObjectRepository;
import Interfaces.Repository.ITrainingReservationRepository;
import Interfaces.Repository.IUserRepository;
import Model.SportObject;
import Model.SportObjectContent;
import Model.TrainingReservation;
import Model.TrainingSession;
import Utils.Validators.TrainingReservationValidator;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class TrainingReservationService {

    private ITrainingReservationRepository trainingReservationRepository;
    private ISportObjectRepository sportObjectRepository;
    private TrainingReservationValidator trainingReservationValidator;

    public TrainingReservationService(ITrainingReservationRepository trainingReservationRepository,
                                      ISportObjectRepository sportObjectRepository,
                                      IUserRepository userRepository) {
        this.trainingReservationRepository = trainingReservationRepository;
        this.sportObjectRepository = sportObjectRepository;
        trainingReservationValidator = new TrainingReservationValidator(trainingReservationRepository, sportObjectRepository, userRepository);
    }


    public void create(TrainingReservation reservation) throws Exception {
        reservation.loadContentData(getTrainingSession(reservation.getSportObjectId(), reservation.getContentName()));
        trainingReservationValidator.validateReservationCreation(reservation);
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
        if(!isMoreThanTwoDaysAfterNow(reservation.getReservedAt())) {
            throw new Exception("You can't cancel training that is in less than 2 days!");
        }
        reservation.setCanceled(true);
        trainingReservationRepository.update(reservation);
    }

    private boolean isMoreThanTwoDaysAfterNow(LocalDateTime reservedDate) {
        Duration duration = Duration.between(LocalDateTime.now(), reservedDate);
        float days = duration.toDays();
        System.out.println("Duration is " + days);
        return days > 2;
    }

    private TrainingSession getTrainingSession(int sportObjectId, String contentName) throws Exception {
        SportObject sportObject = sportObjectRepository.findById(sportObjectId);
        SportObjectContent sportObjectContent = sportObject.findSpecificContent(contentName);
        if(sportObjectContent instanceof TrainingSession) return ((TrainingSession)sportObjectContent);
        throw new Exception("Given content is not training!");
    }



}
