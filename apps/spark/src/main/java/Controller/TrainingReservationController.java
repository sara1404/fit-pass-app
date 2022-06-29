package Controller;

import Model.TrainingReservation;
import Service.TrainingReservationService;
import spark.Request;
import spark.Response;

import java.util.List;

public class TrainingReservationController extends Controller {

    private static TrainingReservationService trainingReservationService;

    public static void initContext(TrainingReservationService trngReservationService) {
        trainingReservationService = trngReservationService;
    }

    public static String reserveTraining(Request request, Response response) {
        TrainingReservation reservation = gson.fromJson(request.body(), TrainingReservation.class);
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
}
