package Main;

import Controller.AuthController;
import Controller.ErrorController;
import Controller.UserController;
import DataHandler.DataHandler;
import Exceptions.AuthException;
import Model.*;
import Repository.UserRepository;
import Service.AuthService;
import Service.UserService;
import Utils.Constants;

import static spark.Spark.*;


public class Application {


    public static void main(String []args) {
        String projectDir = System.getProperty("user.dir");
        String staticDir = "/src/main/resources/web-content";
        staticFiles.externalLocation(projectDir + staticDir);

        initializeServices();
        port(8000);
        staticFileLocation("/public");
        get("/", (req, res) -> "index");


        path("/api", () -> {
            path("/auth", () -> {
                post("/login", AuthController::login);
                post("/register", AuthController::registerBuyer);
            });

            path("/users", () -> {
                before("/*", AuthController::authenticate);
                before("/all", (req, res) -> AuthController.authorize(req, Constants.UserRole.ADMIN));
                get("/me", UserController::getOne);
                get("/all", UserController::getAll);
                put("/edit", UserController::editOne);

            });

            path("/admin", () -> {
                before("/*", AuthController::authenticate);
                before("/*", (req, res) -> AuthController.authorize(req, Constants.UserRole.ADMIN));
                post("/register", AuthController::adminRegistration);
            });
        });


        exception(AuthException.class, ErrorController::authErrorHandler);
        exception(Exception.class, ErrorController::defaultErrorHandler);

        internalServerError((req, res) -> {
            res.type("application/json");
            return "{\"message\":\"Custom 500 handling\"}";
        });
    }


    private static void initializeServices() {
        DataHandler<User> userDataHandler = new DataHandler<User>(Constants.usersPath, User.class, Manager.class, Buyer.class, Administrator.class, Coach.class, User.class);
        UserRepository userRepository = new UserRepository(userDataHandler);
        UserService userService = new UserService(userRepository);
        AuthService authService = new AuthService(userRepository);

        UserController.initContext(userService);
        AuthController.initContext(authService, userService);
    }

}
