package Main;

import Controller.AuthController;
import Controller.ErrorController;
import Controller.UserController;
import Exceptions.AuthException;
import Repository.UserRepository;
import Service.AuthService;
import Service.UserService;
import Utils.Constants;

import static spark.Spark.*;


public class Application {


    public static void main(String []args) {

        initializeServices();
        port(8000);
        get("/user/:username", UserController::getOne);


        path("/auth", () -> {
            post("/login", AuthController::login);
        });

        path("/users", () -> {
            before("/*", AuthController::authenticate);
            before("/all", (req, res) -> AuthController.authorize(req, res, Constants.UserRole.ADMIN));

            get("/me", UserController::getOne);
            get("/all", UserController::getAll);
        });

        exception(AuthException.class, ErrorController::authErrorHandler);
        exception(Exception.class, ErrorController::defaultErrorHandler);

        internalServerError((req, res) -> {
            res.type("application/json");

            return "{\"message\":\"Custom 500 handling\"}";
        });
    }


    private static void initializeServices() {
        UserRepository userRepository = new UserRepository();
        UserService userService = new UserService(userRepository);
        AuthService authService = new AuthService(userRepository);

        UserController.initContext(userService);
        AuthController.initContext(authService, userService);
    }

}
