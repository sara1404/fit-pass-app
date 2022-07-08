package Repository;

import DataHandler.TemplateDataHandler;
import DataHandler.TrainingReservationDataHandler;
import Interfaces.Repository.ITrainingReservationRepository;
import Model.Comment;
import Model.TrainingReservation;

import java.util.List;
import java.util.stream.Collectors;

public class TrainingReservationRepository implements ITrainingReservationRepository {
    private TemplateDataHandler<TrainingReservation> trainingReservationDataHandler;
    private List<TrainingReservation> reservations;

    public TrainingReservationRepository(TemplateDataHandler<TrainingReservation> trainingReservationDataHandler) {
        this.trainingReservationDataHandler = trainingReservationDataHandler;
        this.reservations = trainingReservationDataHandler.readFromFile();
    }

    @Override
    public List<TrainingReservation> findAll() {
        return reservations;
    }

    @Override
    public TrainingReservation create(TrainingReservation reservation) {
        reservation.setId(generateId());
        reservations.add(reservation);
        trainingReservationDataHandler.writeToFile(reservations);
        return reservation;
    }

    @Override
    public void update(TrainingReservation object) {
        TrainingReservation reservation = findById(object.getId());
        reservation.update(object);
        trainingReservationDataHandler.writeToFile(reservations);
    }

    @Override
    public List<TrainingReservation> findAllByCoachUsername(String username) {
        for(TrainingReservation res : reservations) {
            System.out.println(res.getCoachUsername());
        }
        return reservations.stream()
                .filter(reservation -> reservation.getCoachUsername().equals(username))
                .collect(Collectors.toList());
    }

    @Override
    public List<TrainingReservation> findAllByBuyerUsername(String username) {
        return reservations.stream()
                .filter(reservation -> reservation.getBuyerUsername() == null || reservation.getBuyerUsername().equals(username))
                .collect(Collectors.toList());
    }

    @Override
    public List<TrainingReservation> findAllBySportObjectId(int sportObjectId) {
        return reservations.stream()
                .filter(reservation -> reservation.getSportObjectId() == sportObjectId)
                .collect(Collectors.toList());
    }

    @Override
    public TrainingReservation findById(int id) {
        return reservations.stream()
                .filter(reservation -> reservation.getId() == id)
                .findAny()
                .orElse(null);
    }

    @Override
    public void deleteById(int id) {
        TrainingReservation reservation = findById(id);
        reservations.remove(reservation);
        trainingReservationDataHandler.writeToFile(reservations);
    }

    private int generateId() {
        int id = 0;
        List<Integer> ids = extractExistingIds();

        while(ids.contains(id))
            id++;
        return id;
    }

    private List<Integer> extractExistingIds() {
        return reservations.stream().map(TrainingReservation::getId).collect(Collectors.toList());
    }
}
