package Main;

import Controller.AuthController;
import Controller.ErrorController;
import Controller.SetupController;
import Controller.UserController;
import DataHandler.DataHandler;
import Exceptions.AuthException;
import Model.*;
import Repository.UserRepository;
import Service.AuthService;
import Service.UserService;
import Utils.Constants;
import spark.Filter;
import spark.Request;
import spark.Response;
import spark.Spark;

import java.util.HashMap;

import static spark.Spark.*;


public class Application {
    private static final HashMap<String, String> corsHeaders = new HashMap<String, String>();

    static {
        corsHeaders.put("Access-Control-Allow-Methods", "GET,PUT,POST,DELETE,OPTIONS");
        corsHeaders.put("Access-Control-Allow-Origin", "*");
        corsHeaders.put("Access-Control-Allow-Headers", "Content-Type,Authorization,X-Requested-With,Content-Length,Accept,Origin,");
        corsHeaders.put("Access-Control-Allow-Credentials", "true");
    }

    public final static void apply() {
        Filter filter = new Filter() {
            @Override
            public void handle(Request request, Response response) throws Exception {
                corsHeaders.forEach((key, value) -> {
                    response.header(key, value);
                });
            }
        };
        Spark.after(filter);
    }

    public static void main(String []args) {
        String projectDir = System.getProperty("user.dir");
        String staticDir = "/src/main/resources/public";
        staticFiles.externalLocation(projectDir + staticDir);
        initializeServices();
        port(8000);

        get("/",  (req, res) -> "index");
        before(SetupController::enableCORSOrigin);

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
        DataHandler<User> userDataHandler = new DataHandler<User>(Constants.usersPath, User.class, Manager.class, Buyer.class, Administrator.class, Coach.class, User.class);
        UserRepository userRepository = new UserRepository(userDataHandler);
        UserService userService = new UserService(userRepository);
        AuthService authService = new AuthService(userRepository);

        UserController.initContext(userService);
        AuthController.initContext(authService, userService);
    }

}
