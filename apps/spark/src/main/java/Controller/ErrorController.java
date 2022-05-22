package Controller;

import Exceptions.AuthException;
import Service.AuthService;
import Service.UserService;
import spark.Request;
import spark.Response;

import static spark.Spark.halt;

public class ErrorController {


    public static void authErrorHandler(AuthException exception, Request request, Response response) {
        response.status(exception.getStatusCode());
        response.body(exception.getMessage());
    }

    public static void defaultErrorHandler(Exception exception, Request request, Response response) {
        response.type("text/html");
        response.status(400);
        response.body(exception.getMessage() == null ? "Bad request!" : exception.getMessage());
    }

}
