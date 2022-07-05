package Controller;

import DTO.BuyerTrainingDataDTO;
import DTO.ReservationTrainingDataDTO;
import Model.*;
import Service.SportObjectService;
import Service.TrainingReservationService;
import Service.UserService;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.List;

public class TrainingReservationController extends Controller {

    private static TrainingReservationService trainingReservationService;
    private static UserService userService;
    private static SportObjectService sportObjectService;

    public static void initContext(TrainingReservationService trngReservationService, UserService usrService, SportObjectService sprtObjectService) {
        trainingReservationService = trngReservationService;
        userService = usrService;
        sportObjectService = sprtObjectService;
    }

    public static String reserveTraining(Request request, Response response) throws Exception {
        TrainingReservation reservation = gson.fromJson(request.body(), TrainingReservation.class);
        reservation.setBuyerUsername(request.attribute("username"));
        trainingReservationService.create(reservation);
        return successResponse();
    }

    public static String cancelTraining(Request request, Response response) throws Exception {
        int trainingId = Integer.parseInt(request.params(":trainingId"));
        trainingReservationService.cancelTraining(trainingId);
        return successResponse();
    }

    public static String getAllForBuyer(Request request, Response response) {
        String username = request.attribute("username");
        List<TrainingReservation> reservations = trainingReservationService.findAllByBuyerUsername(username);
        reservations = trainingReservationService.filterLastMonthTrainings(reservations);
        return gson.toJson(reservations);
    }

    public static String getAllForCoach(Request request, Response response) {
        String username = request.attribute("username");
        List<TrainingReservation> reservations = trainingReservationService.findAllByCoachUsername(username);
        return gson.toJson(mapAllReservationsToDTO(reservations));
    }

    public static String getHistoryForBuyer(Request request, Response response) {
        String username = request.attribute("username");
        List<TrainingHistory> history = userService.getBuyerTrainingHistory(username, request.queryMap().toMap());
        return gson.toJson(mapAllTrainingDataFromHistory(history));
    }

    public static String getAllForManager(Request request, Response response) {
        String username = request.attribute("username");
        Manager manager = (Manager)userService.findByUsername(username);
        SportObject object = manager.getSportObject();
        List<TrainingReservation> reservations = trainingReservationService.findAllBySportObjectId(object.getId());
        return gson.toJson(reservations);
    }

    public static String getHistoryForCoach(Request request, Response response) {
        String username = request.attribute("username");
        List<TrainingHistory> history = userService.getCoachTrainingHistory(username, request.queryMap().toMap());
        return gson.toJson(mapAllTrainingDataFromHistory(history));
    }

    public static String checkInSportObject(Request request, Response response) throws Exception {
        String username = request.attribute("username");
        trainingReservationService.checkInSportObject(username);
        return successResponse();
    }

    public static String checkInTrainingSession(Request request, Response response) throws Exception {
        String username = request.attribute("username");
        TrainingSession content = gson.fromJson(request.body(), TrainingSession.class);
        int trainingId = Integer.parseInt(request.params(":trainingId"));
        trainingReservationService.executeTrainingCheckInLogic(content, username, trainingId);
        return successResponse();
    }

    public static String buyAdditionalTrainingPackage(Request request, Response response) throws Exception {
        String username = request.attribute("username");
        TrainingSubscription trainingPackage = gson.fromJson(request.body(), TrainingSubscription.class);
        trainingReservationService.buyAdditionalTrainingPackage(trainingPackage, username);
        return successResponse();
    }

    private static List<BuyerTrainingDataDTO> mapAllTrainingDataFromHistory(List<TrainingHistory> histories) {
        List<BuyerTrainingDataDTO> mapped = new ArrayList<>();
        for(TrainingHistory history: histories) {
            mapped.add(mapTrainingDataFromHistory(history));
        }
        return mapped;
    }

    private static BuyerTrainingDataDTO mapTrainingDataFromHistory(TrainingHistory history) {
        SportObject sportObject = sportObjectService.findById(history.getTrainingSession().getObjectId());
        return new BuyerTrainingDataDTO(history, sportObject);
    }

    private static List<ReservationTrainingDataDTO> mapAllReservationsToDTO(List<TrainingReservation> reservations) {
        List<ReservationTrainingDataDTO> mappedReservations = new ArrayList<>();
        for(TrainingReservation reservation : reservations) {
            mappedReservations.add(mapTrainingReservationToDTO(reservation));
        }
        return mappedReservations;
    }

    private static ReservationTrainingDataDTO mapTrainingReservationToDTO(TrainingReservation reservation) {
        SportObject object = sportObjectService.findById(reservation.getSportObjectId());
        return new ReservationTrainingDataDTO(reservation, object);
    }


}
