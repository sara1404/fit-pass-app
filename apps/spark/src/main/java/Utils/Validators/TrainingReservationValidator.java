package Utils.Validators;

import Interfaces.Repository.ISportObjectRepository;
import Interfaces.Repository.ISubscriptionRepository;
import Interfaces.Repository.ITrainingReservationRepository;
import Interfaces.Repository.IUserRepository;
import Model.*;
import Utils.Constants;

import java.time.LocalDateTime;
import java.util.List;

public class TrainingReservationValidator {

    private ITrainingReservationRepository trainingReservationRepository;
    private ISportObjectRepository sportObjectRepository;
    private IUserRepository userRepository;
    private ISubscriptionRepository subscriptionRepository;

    public TrainingReservationValidator(ITrainingReservationRepository trainingReservationRepository,
                                        ISportObjectRepository sportObjectRepository,
                                        IUserRepository userRepository,
                                        ISubscriptionRepository subscriptionRepository) {
        this.trainingReservationRepository = trainingReservationRepository;
        this.sportObjectRepository = sportObjectRepository;
        this.userRepository = userRepository;
        this.subscriptionRepository = subscriptionRepository;
    }

    public void validateReservationCreation(TrainingReservation reservation) throws Exception {
        fieldsAreNull(reservation);
        sportObjectDoesntExist(reservation);
        coachDoesntExist(reservation);
        noSubscriptionForGivenContent(reservation);
        contentDoesntExistInSportObject(reservation);
        trainingDoesntExistOnDate(reservation);
        reservedTimeBeforeNow(reservation);
        coachReservedAtTime(reservation);
    }

    public void validateCheckIn(String buyerUsername, int trainingId) throws Exception {
        reservationMatchesUsername(buyerUsername, trainingId);
    }

    private void reservationMatchesUsername(String buyerUsername, int trainingId) throws Exception {
        TrainingReservation reservation = trainingReservationRepository.findById(trainingId);
        if(!reservation.getBuyerUsername().equals(buyerUsername)) throw new Exception("You can't check in for somebody else!");
    }

    private void fieldsAreNull(TrainingReservation reservation) throws Exception {
        if(reservation.getReservedAt() == null) throw new Exception("Reservation date is required field!");
        if(reservation.getDuration() == 0) throw new Exception("Duration should be more than 0!");
    }

    private void sportObjectDoesntExist(TrainingReservation reservation) throws Exception {
        SportObject sportObject = sportObjectRepository.findById(reservation.getSportObjectId());
        if(sportObject == null) throw new Exception("Sport Object doesn't exist");
    }

    private void coachDoesntExist(TrainingReservation reservation) throws Exception {
        User user =  userRepository.findByUsername(reservation.getCoachUsername());
        if(user == null) throw new Exception("Coach doesn't exist!");
        if(user.getRole() != Constants.UserRole.COACH) throw new Exception("User is not coach!");
    }

    private void coachReservedAtTime(TrainingReservation reservation) throws Exception {
        List<TrainingReservation> coachReservations = trainingReservationRepository.findAllByCoachUsername(reservation.getCoachUsername());
        for(TrainingReservation res: coachReservations) {
            if(res.overlaps(reservation)
                    && !(res.getType() == Constants.TrainingType.GROUP_TRAINING
                    && reservation.getType() == Constants.TrainingType.GROUP_TRAINING))
                throw new Exception("Coach is taken in this time interval");
        }
    }

    private void contentDoesntExistInSportObject(TrainingReservation reservation) throws Exception {
        SportObject sportObject = sportObjectRepository.findById(reservation.getSportObjectId());
        if(!sportObject.contentExistInObject(reservation.getType())) {
            throw new Exception("Object doesn't have this kind of content!");
        }
    }

    private void reservedTimeBeforeNow(TrainingReservation reservation) throws Exception {
        if(reservation.getReservedAt().isBefore(LocalDateTime.now())) throw new Exception("You can't reserve time in past!");
    }

    private void trainingDoesntExistOnDate(TrainingReservation reservation) throws Exception {
        TrainingSession session = (TrainingSession) sportObjectRepository.findContent(reservation.getSportObjectId(), reservation.getContentName());
        if(!session.isTrainingOnDate(reservation.getReservedAt())) {
            throw new Exception("There is no training scheduled on this date!");
        }
    }

    private void noSubscriptionForGivenContent(TrainingReservation reservation) throws Exception {
        Subscription subscription = subscriptionRepository.findByBuyer(reservation.getBuyerUsername());
        subscription.findAdditionalSubByContent(reservation.getSportObjectId(), reservation.getContentName());
    }



}
