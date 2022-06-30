package Controller;

import Model.SportObjectContent;
import Model.TrainingReservation;
import Model.TrainingSession;
import Model.TrainingSubscription;
import Service.TrainingReservationService;
import spark.Request;
import spark.Response;

import java.util.List;

public class TrainingReservationController extends Controller {

    private static TrainingReservationService trainingReservationService;

    public static void initContext(TrainingReservationService trngReservationService) {
        trainingReservationService = trngReservationService;
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
        return gson.toJson(reservations);
    }

    public static String getAllForCoach(Request request, Response response) {
        String username = request.attribute("username");
        List<TrainingReservation> reservations = trainingReservationService.findAllByCoachUsername(username);
        return gson.toJson(reservations);
    }

    public static String checkInSportObject(Request request, Response response) throws Exception {
        String username = request.attribute("username");
        trainingReservationService.checkInSportObject(username);
        return successResponse();
    }

    public static String checkInTrainingSession(Request request, Response response) throws Exception {
        String username = request.attribute("username");
        TrainingSession content = gson.fromJson(request.body(), TrainingSession.class);
        trainingReservationService.checkInTraining(content, username);
        trainingReservationService.deleteById(Integer.parseInt(request.params(":trainingId")));
        return successResponse();
    }

    public static String buyAdditionalTrainingPackage(Request request, Response response) throws Exception {
        String username = request.attribute("username");
        TrainingSubscription trainingPackage = gson.fromJson(request.body(), TrainingSubscription.class);
        trainingReservationService.buyAdditionalTrainingPackage(trainingPackage, username);
        return successResponse();
    }
}
