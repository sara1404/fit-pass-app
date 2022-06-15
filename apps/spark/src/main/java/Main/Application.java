package Main;

import Controller.*;
import DataHandler.SubtypeDataHandler;
import Exceptions.AuthException;
import Model.*;
import Repository.SportObjectRepository;
import Repository.UserRepository;
import Service.AuthService;
import Service.SportObjectService;
import Service.UserService;
import Utils.Constants;
import Utils.SearchImpl.SportObjectsPipeline;
import DataHandler.SportObjectDataHandler;
import static spark.Spark.*;


public class Application {

    public static void main(String []args) {

        String projectDir = System.getProperty("user.dir");
        String staticDir = "/src/main/resources/public";
        staticFiles.externalLocation(projectDir + staticDir);
        initializeServices();
        port(8000);

        get("/",  (req, res) -> "index");
        before(SetupController::enableCORSOrigin);
        options("/*", SetupController::enableCORSForMethods);


        path("/api", () -> {
            path("/auth", () -> {
                before("/*", SetupController::enableCORSForFilters);
                post("/login", AuthController::login);
                post("/register", AuthController::registerBuyer);
            });
            path("/users", () -> {
                before("/*", SetupController::enableCORSForFilters);
                before("/*", AuthController::authenticate);
                before("/all", (req, res) -> AuthController.authorize(req, Constants.UserRole.ADMIN));
                get("/me", UserController::getOne);
                get("/all", UserController::getAll);
                put("/edit", UserController::editOne);

            });
            path("/objects", () ->{
                before( SetupController::enableCORSForFilters);
                get("/all", SportObjectController::getAll);
                get("/filter", SportObjectController::filterSportObjects);
            });

            path("/admin", () -> {
                before("/*", SetupController::enableCORSForFilters);
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
//        DataHandler<SportObject> sportObjectDataHandler = new DataHandler<SportObject>(Constants.sportObjectPath);
        SportObjectDataHandler sportObjectDataHandler = new SportObjectDataHandler(Constants.sportObjectPath);
        SubtypeDataHandler<User> userDataHandler = new SubtypeDataHandler<User>(Constants.usersPath, User.class, Manager.class, Buyer.class, Administrator.class, Coach.class, User.class);

        UserRepository userRepository = new UserRepository(userDataHandler);
        SportObjectRepository sportObjectRepository = new SportObjectRepository(sportObjectDataHandler);

        UserService userService = new UserService(userRepository);
        AuthService authService = new AuthService(userRepository);
        SportObjectService sportObjectService = new SportObjectService(sportObjectRepository);


        UserController.initContext(userService);
        AuthController.initContext(authService, userService);
        SportObjectController.initContext(sportObjectService);


    }

}
