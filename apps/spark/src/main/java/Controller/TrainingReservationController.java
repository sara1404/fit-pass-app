package Controller;

import Model.*;
import Service.TrainingReservationService;
import Service.UserService;
import spark.Request;
import spark.Response;

import java.util.List;

public class TrainingReservationController extends Controller {

    private static TrainingReservationService trainingReservationService;
    private static UserService userService;

    public static void initContext(TrainingReservationService trngReservationService, UserService usrService) {
        trainingReservationService = trngReservationService;
        userService = usrService;
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
        return gson.toJson(reservations);
    }

    public static String getHistoryForBuyer(Request request, Response response) {
        String username = request.attribute("username");
        List<TrainingHistory> history = userService.getBuyerTrainingHistory(username, request.queryMap().toMap());
        return gson.toJson(history);
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
        return gson.toJson(history);
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


}
