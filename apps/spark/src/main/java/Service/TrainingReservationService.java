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
import java.util.stream.Collectors;

public class TrainingReservationService {

    private ITrainingReservationRepository trainingReservationRepository;
    private ISportObjectRepository sportObjectRepository;
    private ISubscriptionRepository subscriptionRepository;
    private IUserRepository userRepository;
    private final TrainingReservationValidator trainingReservationValidator;

    public TrainingReservationService(
            ITrainingReservationRepository trainingReservationRepository,
            ISportObjectRepository sportObjectRepository,
            IUserRepository userRepository,
            ISubscriptionRepository subscriptionRepository
    ) {
        this.trainingReservationRepository = trainingReservationRepository;
        this.sportObjectRepository = sportObjectRepository;
        this.subscriptionRepository = subscriptionRepository;
        this.userRepository = userRepository;

        this.trainingReservationValidator = new TrainingReservationValidator(
                trainingReservationRepository,
                sportObjectRepository,
                userRepository,
                subscriptionRepository
        );
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

    public void deleteById(int trainingId) {
        trainingReservationRepository.deleteById(trainingId);
    }

    public void buyAdditionalTrainingPackage(TrainingSubscription newTrainingSub, String buyerUsername) throws Exception {
        Subscription subscription = subscriptionRepository.findByBuyer(buyerUsername);
        TrainingSession trainingSession = getExtraContent(newTrainingSub);
        newTrainingSub.loadContentData(trainingSession);
        subscription.addAdditionalSub(newTrainingSub);
        subscriptionRepository.update(subscription);
    }

    public void executeTrainingCheckInLogic(TrainingSession content, String buyerUsername, int trainingId) throws Exception{
        trainingReservationValidator.validateCheckIn(buyerUsername, trainingId);
        TrainingSession existingContent = (TrainingSession)sportObjectRepository.findContent(content.getObjectId(), content.getName());
        checkInTraining(existingContent, buyerUsername);
        addVisitedToBuyer(buyerUsername, existingContent.getObjectId());
        addHistoryToBuyer(existingContent, trainingId);
        addHistoryToCoach(existingContent, trainingId);
        deleteById(trainingId);
    }

    public void checkInSportObject(String buyerUsername) throws Exception {
        Subscription subscription = subscriptionRepository.findByBuyer(buyerUsername);
        if(subscription.getAllowedEntersPerDay() == 0) throw new Exception("Number of enters for this day is 0!");
        subscription.setAllowedEntersPerDay(subscription.getAllowedEntersPerDay() - 1);
        subscriptionRepository.update(subscription);
    }

    public void cancelTraining(int id) throws Exception {
        TrainingReservation reservation = trainingReservationRepository.findById(id);
        if(reservation.getType() == Constants.TrainingType.GROUP_TRAINING) throw new Exception("Group training can't be canceled");
        if(!isMoreThanTwoDaysAfterNow(reservation.getReservedAt())) throw new Exception("You can't cancel training that is in less than 2 days!");
        reservation.setCanceled(true);
        trainingReservationRepository.update(reservation);
    }

    public List<TrainingReservation> filterLastMonthTrainings(List<TrainingReservation> reservations) {
        LocalDateTime monthAgoDate = LocalDateTime.now().minusMonths(1);
        return reservations.stream()
                .filter(reservation -> reservation.getReservedAt().isAfter(monthAgoDate))
                .collect(Collectors.toList());
    }

    public List<TrainingReservation> findAllBySportObjectId(int sportObjectId) {
        return trainingReservationRepository.findAllBySportObjectId(sportObjectId);
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

    private void addVisitedToBuyer(String username, int sportObjectId) {
        Buyer buyer = (Buyer)userRepository.findByUsername(username);
        SportObject sportObject = sportObjectRepository.findById(sportObjectId);
        buyer.addVisitedObject(sportObject);
        userRepository.update(buyer);
    }

    private void checkInTraining(TrainingSession content, String buyerUsername) throws Exception {
        Subscription subscription = subscriptionRepository.findByBuyer(buyerUsername);
        subscription.checkIn(content.getName(), content.getObjectId());
        subscriptionRepository.update(subscription);
    }

    private void addHistoryToCoach(TrainingSession session, int trainingId) {
        TrainingReservation reservation = trainingReservationRepository.findById(trainingId);
        Coach coach = (Coach) userRepository.findByUsername(session.getCoachUsername());
        coach.addTrainingToHistory(session, reservation);
        userRepository.update(coach);
    }

    private void addHistoryToBuyer(TrainingSession session, int trainingId) {
        TrainingReservation reservation = trainingReservationRepository.findById(trainingId);
        Buyer buyer = (Buyer) userRepository.findByUsername(reservation.getBuyerUsername());
        buyer.addFinishedTraining(session, reservation);
        userRepository.update(buyer);
    }

}
