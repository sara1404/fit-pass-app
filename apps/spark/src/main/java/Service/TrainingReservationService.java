package Service;

import Interfaces.Repository.ISportObjectRepository;
import Interfaces.Repository.ISubscriptionRepository;
import Interfaces.Repository.ITrainingReservationRepository;
import Interfaces.Repository.IUserRepository;
import Model.*;
import Utils.Constants;
import Utils.Validators.TrainingReservationValidator;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class TrainingReservationService {

    private ITrainingReservationRepository trainingReservationRepository;
    private ISportObjectRepository sportObjectRepository;
    private ISubscriptionRepository subscriptionRepository;
    private TrainingReservationValidator trainingReservationValidator;

    public TrainingReservationService(ITrainingReservationRepository trainingReservationRepository,
                                      ISportObjectRepository sportObjectRepository,
                                      IUserRepository userRepository,
                                      ISubscriptionRepository subscriptionRepository) {
        this.trainingReservationRepository = trainingReservationRepository;
        this.sportObjectRepository = sportObjectRepository;
        this.subscriptionRepository = subscriptionRepository;
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

    public void buyAdditionalTrainingPackage(TrainingSubscription newTrainingSub, String buyerUsername) throws Exception {
        Subscription subscription = subscriptionRepository.findByBuyer(buyerUsername);
        TrainingSession trainingSession = getExtraContent(newTrainingSub);
        newTrainingSub.loadContentData(trainingSession);
        subscription.addAdditionalSub(newTrainingSub);
        subscriptionRepository.update(subscription);
    }

    public void deleteById(int trainingId) {
        trainingReservationRepository.deleteById(trainingId);
    }

    public void checkInSportObject(String buyerUsername) throws Exception {
        Subscription subscription = subscriptionRepository.findByBuyer(buyerUsername);
        if(subscription.getAllowedEntersPerDay() == 0) throw new Exception("Number of enters for this day is 0!");
        subscription.setAllowedEntersPerDay(subscription.getAllowedEntersPerDay() - 1);
        subscriptionRepository.update(subscription);
    }

    public void checkInTraining(TrainingSession content, String buyerUsername) throws Exception {
        Subscription subscription = subscriptionRepository.findByBuyer(buyerUsername);
        subscription.checkIn(content.getName(), content.getObjectId());
        subscriptionRepository.update(subscription);
    }

    public void cancelTraining(int id) throws Exception {
        TrainingReservation reservation = trainingReservationRepository.findById(id);
        if(!isMoreThanTwoDaysAfterNow(reservation.getReservedAt())) throw new Exception("You can't cancel training that is in less than 2 days!");
        reservation.setCanceled(true);
        trainingReservationRepository.update(reservation);
    }

    private boolean isMoreThanTwoDaysAfterNow(LocalDateTime reservedDate) {
        Duration duration = Duration.between(LocalDateTime.now(), reservedDate);
        float days = duration.toDays();
        return days > 2;
    }

    private TrainingSession getTrainingSession(int sportObjectId, String contentName) throws Exception {
        SportObject sportObject = sportObjectRepository.findById(sportObjectId);
        SportObjectContent sportObjectContent = sportObject.findSpecificContent(contentName);
        if(sportObjectContent instanceof TrainingSession) return ((TrainingSession)sportObjectContent);
        throw new Exception("Given content is not training!");
    }

    private TrainingSession getExtraContent(TrainingSubscription newTrainingSub) throws Exception {
       SportObjectContent content = sportObjectRepository.findContent(newTrainingSub.getObjectId(), newTrainingSub.getContentName());
       if(content == null) throw new Exception("Content doesn't exist");
       return (TrainingSession)content;

    }



}
