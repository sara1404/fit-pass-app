package Controller;

import spark.Request;
import spark.Response;

import static spark.Spark.halt;

public class SetupController {

    private static boolean corsEnabled = true;
    public static void enableCORSOrigin(Request request, Response response) {
        if(corsEnabled)
            response.header("Access-Control-Allow-Origin", "*");
    }

    public static void enableCORSForFilters(Request request, Response response) {
        if(request.requestMethod().equals("OPTIONS") && corsEnabled) {
            enableCORS(request, response);
            halt(200, "OK");
        }

    }

    public static String enableCORSForMethods(Request request, Response response) {
        enableCORS(request, response);
        return "OK";
    }

    private static void enableCORS(Request request,  Response response) {
        String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
        if (accessControlRequestHeaders != null) {
            response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
        }

        String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
        if (accessControlRequestMethod != null) {
            response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
        }
    }
}
