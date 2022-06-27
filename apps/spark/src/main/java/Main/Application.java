package Main;

import Controller.*;
import DataHandler.*;
import Exceptions.AuthException;
import Model.*;
import Repository.SportObjectRepository;
import Repository.UserRepository;
import Service.AuthService;
import Service.SportObjectService;
import Service.UserService;
import Utils.Constants;
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

                get("/role", UserController::getRole);
                get("/me", UserController::getOne);
                put("/edit", UserController::editOne);

                before("/coaches", (req, res) -> AuthController.authorize(req, Constants.UserRole.MANAGER));
                get("/coaches", UserController::getCoaches);

                before("/all", (req, res) -> AuthController.authorize(req, Constants.UserRole.ADMIN));
                get("/all", UserController::getAll);

                before("/:username/object/:id", (req, res) -> AuthController.authorize(req, Constants.UserRole.ADMIN));
                post("/:username/object/:id", UserController::registerObjectToManager);
            });
            path("/objects", () ->{
                before( SetupController::enableCORSForFilters);
                get("/all", SportObjectController::filterSportObjects);
                get("/:id", SportObjectController::getOne);

                before("/create", AuthController::authenticate);
                before("/create", (req, res) -> AuthController.authorize(req, Constants.UserRole.ADMIN));
                post("/create", SportObjectController::create);

                before("/:id/logo", AuthController::authenticate);
                before("/:id/logo", (req, res) -> AuthController.authorize(req, Constants.UserRole.ADMIN));
                post("/:id/logo", SportObjectController::uploadSportObjectLogo);
            });
            path("/admin", () -> {
                before("/*", SetupController::enableCORSForFilters);
                before("/*", AuthController::authenticate);
                before("/*", (req, res) -> AuthController.authorize(req, Constants.UserRole.ADMIN));
                post("/register", AuthController::adminRegistration);
            });
            path("/manager", () -> {
               before("/*", SetupController::enableCORSForFilters);
               before("/*", AuthController::authenticate);
               before("/*", (req, res) -> AuthController.authorize(req, Constants.UserRole.MANAGER));
               get("/objects/:content", SportObjectController::getOneContent);
               get("/view", SportObjectController::getManagerViewData);
               post("/objects/content", SportObjectController::addContent);
               put("/objects/:id/:content", SportObjectController::updateContent);

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
//        SportObjectDataHandler sportObjectDataHandler = new SportObjectDataHandler(Constants.sportObjectPath);
//        SubtypeDataHandler<User> userDataHandler = new SubtypeDataHandler<User>(Constants.usersPath, User.class, Manager.class, Buyer.class, Administrator.class, Coach.class);
        TemplateDataHandler<User> userDataHandler = new UserDataHandler(Constants.usersPath);
        TemplateDataHandler<SportObject> sportObjectDataHandler = new SportObjectDataHandler(Constants.sportObjectPath);

        SportObjectRepository sportObjectRepository = new SportObjectRepository(sportObjectDataHandler);
        UserRepository userRepository = new UserRepository(userDataHandler, sportObjectRepository);

        UserService userService = new UserService(userRepository, sportObjectRepository);
        AuthService authService = new AuthService(userRepository);
        SportObjectService sportObjectService = new SportObjectService(sportObjectRepository);


        UserController.initContext(userService);
        AuthController.initContext(authService, userService);
        SportObjectController.initContext(sportObjectService, userService);


    }

}
