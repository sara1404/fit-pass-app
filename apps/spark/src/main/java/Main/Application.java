package Main;

import Controller.*;
import DataHandler.*;
import Exceptions.AuthException;
import Model.*;
import Repository.CommentRepository;
import Repository.SportObjectRepository;
import Repository.TrainingReservationRepository;
import Repository.UserRepository;
import Repository.*;
import Service.*;
import Utils.BackgroundTaskThread;
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
        before(CORSController::enableCORSOrigin);
        options("/*", CORSController::enableCORSForMethods);


        path("/api", () -> {
            path("/auth", () -> {
                before("/*", CORSController::enableCORSForFilters);
                post("/login", AuthController::login);
                post("/register", AuthController::registerBuyer);
            });
            path("/users", () -> {
                before("/*", CORSController::enableCORSForFilters);
                before("/*", AuthController::authenticate);

                get("/role", UserController::getRole);
                get("/me", UserController::getOne);
                put("/edit", UserController::editOne);

                before("/coaches", (req, res) -> AuthController.authorize(req, Constants.UserRole.MANAGER));
                get("/coaches", UserController::getCoaches);

                before("/all", (req, res) -> AuthController.authorize(req, Constants.UserRole.ADMIN));
                get("/all", UserController::getAll);

                before("/:username/delete", (req, res) -> AuthController.authorize(req, Constants.UserRole.ADMIN));
                delete("/:username/delete", UserController::deleteUser);

                before("/:username/object/:id", (req, res) -> AuthController.authorize(req, Constants.UserRole.ADMIN));
                post("/:username/object/:id", UserController::registerObjectToManager);

                before("/subscription", (req, res) -> AuthController.authorize(req, Constants.UserRole.BUYER));
                before("/subscriptions", (req, res) -> AuthController.authorize(req, Constants.UserRole.BUYER));

                get("/subscriptions", SubscriptionController::getAll);
                post("/subscription", SubscriptionController::createSubscription);
            });
            path("/objects", () ->{
                before("/*", CORSController::enableCORSForFilters);
                get("/all", SportObjectController::filterSportObjects);
                get("/:id", SportObjectController::getOne);

                before("/create", AuthController::authenticate);
                before("/create", (req, res) -> AuthController.authorize(req, Constants.UserRole.ADMIN));
                post("/create", SportObjectController::create);

                before("/:id/logo", AuthController::authenticate);
                before("/:id/logo", (req, res) -> AuthController.authorize(req, Constants.UserRole.ADMIN));
                post("/:id/logo", SportObjectController::uploadSportObjectLogo);

                before("/checkIn", AuthController::authenticate);
                before("/checkIn", (req, res) -> AuthController.authorize(req, Constants.UserRole.BUYER));
                before("/checkIn", SubscriptionController::checkSubscriptionStatus);
                patch("/checkIn", TrainingReservationController::checkInSportObject);

                path("/content", () -> {
                    before("/*", CORSController::enableCORSForFilters);
                    before("/*", AuthController::authenticate);
                    before("/*", (req, res) -> AuthController.authorize(req, Constants.UserRole.MANAGER));
                    post("/add", SportObjectController::addContent);
                    post("/upload/:file-name", SportObjectController::uploadSportObjectContentLogo);
                    get("/:name", SportObjectController::getOneContent);
                    put("/:name", SportObjectController::updateContent);
                });

                path("/training", () -> {
                    before("/*", CORSController::enableCORSForFilters);
                    before("/*", AuthController::authenticate);

                    before("/extras", (req, res) -> AuthController.authorize(req, Constants.UserRole.BUYER));
                    before("/extras", SubscriptionController::checkSubscriptionStatus);
                    post("/extras", TrainingReservationController::buyAdditionalTrainingPackage);

                    before("/buyer", (req, res) -> AuthController.authorize(req, Constants.UserRole.BUYER));
                    get("/buyer", TrainingReservationController::getAllForBuyer);

                    before("/buyer/all", (req, res) -> AuthController.authorize(req, Constants.UserRole.BUYER));
                    get("/buyer/all", TrainingReservationController::getAllReservationsForBuyer);

                    before("/coach", (req, res) -> AuthController.authorize(req, Constants.UserRole.COACH));
                    get("/coach", TrainingReservationController::getAllForCoach);

                    before("/manager", (req, res) -> AuthController.authorize(req, Constants.UserRole.MANAGER));
                    get("/manager", TrainingReservationController::getAllForManager);

                    before("/buyer/history", (req, res) -> AuthController.authorize(req, Constants.UserRole.BUYER));
                    get("/buyer/history", TrainingReservationController::getHistoryForBuyer);

                    before("/coach/history", (req, res) -> AuthController.authorize(req, Constants.UserRole.COACH));
                    get("/coach/history", TrainingReservationController::getHistoryForCoach);

                    before("/reserve", (req, res) -> AuthController.authorize(req, Constants.UserRole.BUYER));
                    before("/reserve", SubscriptionController::checkSubscriptionStatus);
                    post("/reserve", TrainingReservationController::reserveTraining);

                    before("/reserve/group", (req, res) -> AuthController.authorize(req, Constants.UserRole.COACH));
                    post("/reserve/group", TrainingReservationController::reserveGroupTraining);

                    before("/:trainingId/cancel", (req, res) -> AuthController.authorize(req, Constants.UserRole.COACH));
                    patch("/:trainingId/cancel", TrainingReservationController::cancelTraining);

                    before("/:trainingId/checkIn", (req, res) -> AuthController.authorize(req, Constants.UserRole.BUYER));
                    before("/:trainingId/checkIn", SubscriptionController::checkSubscriptionStatus);
                    post("/:trainingId/checkIn", TrainingReservationController::checkInTrainingSession);


                });

                path("/:id", () -> {
                    before("/*", CORSController::enableCORSForFilters);
                    before("/*", AuthController::authenticate);

                    before("/training/additional", (req, res) -> AuthController.authorize(req, Constants.UserRole.BUYER));
                    get("/training/additional", SportObjectController::getObjectAdditionalTrainings);

                    before("/training/additional/group", (req, res) -> AuthController.authorize(req, Constants.UserRole.COACH));
                    get("/training/additional/group", SportObjectController::getObjectGroupTrainings);

                    before("/training/additional/personal", (req, res) -> AuthController.authorize(req, Constants.UserRole.BUYER));
                    get("/training/additional/personal", SportObjectController::getObjectPersonalTrainings);

                    before("/comments/all", CommentController::redirectIfBuyer);
                    before("/comments/all", (req, res) -> AuthController.authorize(req, Constants.UserRole.MANAGER, Constants.UserRole.ADMIN));
                    get("/comments/all", CommentController::getAllForObject);

                    before("/comments/:commentId/*", (req, res) -> AuthController.authorize(req, Constants.UserRole.ADMIN));
                    patch("/comments/:commentId/approve", CommentController::approveComment);
                    patch("/comments/:commentId/decline", CommentController::declineComment);

                    before("/comments", (req, res) -> AuthController.authorize(req, Constants.UserRole.BUYER));
                    get("/comments", CommentController::getAllApprovedForObject);

                    before("/comments/add", (req, res) -> AuthController.authorize(req, Constants.UserRole.BUYER));
                    post("/comments/add", CommentController::addComment);
                });
            });
            path("/admin", () -> {
                before("/*", CORSController::enableCORSForFilters);
                before("/*", AuthController::authenticate);
                before("/*", (req, res) -> AuthController.authorize(req, Constants.UserRole.ADMIN));
                post("/register", AuthController::adminRegistration);
                post("/promos", SubscriptionController::generatePromoCode);
            });
            path("/manager", () -> {
               before("/*", CORSController::enableCORSForFilters);
               before("/*", AuthController::authenticate);
               before("/*", (req, res) -> AuthController.authorize(req, Constants.UserRole.MANAGER));
               get("/view", SportObjectController::getManagerViewData);
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
        TemplateDataHandler<User> userDataHandler = new UserDataHandler(Constants.usersPath);
        TemplateDataHandler<SportObject> sportObjectDataHandler = new SportObjectDataHandler(Constants.sportObjectPath);
        TemplateDataHandler<Comment> commentDataHandler = new CommentDataHandler(Constants.commentsPath);
        TemplateDataHandler<TrainingReservation> trainingReservationTemplateDataHandler = new TrainingReservationDataHandler(Constants.trainingReservationPath);
        TemplateDataHandler<PromoCode> promoCodesDataHandler = new PromoCodesDataHandler(Constants.promoCodesPath);
        TemplateDataHandler<Subscription> subscriptionDataHandler = new SubscriptionDataHandler(Constants.subscriptionPath);
        TemplateDataHandler<SubscriptionPackage> subscriptionPackagesDataHandler = new SubscriptionsPackagesDataHandler(Constants.subscriptionPackagesPath);

        SportObjectRepository sportObjectRepository = new SportObjectRepository(sportObjectDataHandler);
        UserRepository userRepository = new UserRepository(userDataHandler, sportObjectRepository);
        CommentRepository commentRepository = new CommentRepository(commentDataHandler);
        TrainingReservationRepository trainingReservationRepository = new TrainingReservationRepository(trainingReservationTemplateDataHandler);
        PromoCodeRepository promoCodeRepository = new PromoCodeRepository(promoCodesDataHandler);
        SubscriptionRepository subscriptionRepository = new SubscriptionRepository(subscriptionDataHandler);
        SubscriptionPackagesRepository subscriptionPackagesRepository = new SubscriptionPackagesRepository(subscriptionPackagesDataHandler);

        UserService userService = new UserService(userRepository, sportObjectRepository);
        AuthService authService = new AuthService(userRepository);
        SportObjectService sportObjectService = new SportObjectService(sportObjectRepository);
        CommentService commentService = new CommentService(commentRepository, sportObjectRepository);
        TrainingReservationService trainingReservationService = new TrainingReservationService(trainingReservationRepository, sportObjectRepository, userRepository, subscriptionRepository);
        SubscriptionService subscriptionService = new SubscriptionService(promoCodeRepository, subscriptionRepository, userRepository, subscriptionPackagesRepository);

        UserController.initContext(userService);
        AuthController.initContext(authService, userService);
        SportObjectController.initContext(sportObjectService, userService);
        CommentController.initContext(commentService, userService, sportObjectService);
        TrainingReservationController.initContext(trainingReservationService, userService, sportObjectService);
        SubscriptionController.initContext(subscriptionService);

        BackgroundTaskThread thread = new BackgroundTaskThread(sportObjectRepository);
        thread.start();
    }

}
