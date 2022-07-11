package Controller;

import DTO.ManagerDataViewDTO;
import DTO.profile.UserProfileDTO;
import Model.*;
import Service.SportObjectService;
import Service.UserService;
import Utils.Constants;
import spark.Request;
import spark.Response;

import java.util.List;
import java.util.Locale;

public class SportObjectController extends Controller{
    private static SportObjectService sportObjectService;
    private static UserService userService;


    public static void initContext(SportObjectService sportObjService, UserService usrService) {
        sportObjectService = sportObjService;
        userService = usrService;
    }

    public static String getOne(Request request, Response response) {
        SportObject sportObject = sportObjectService.findOne(request.params(":id"));
        return gson.toJson(sportObject);
    }

    public static String getOneContent(Request request, Response response) throws Exception {
        Manager manager = (Manager) userService.findByUsername(request.attribute("username"));
        if(manager.getSportObject() == null) throw new Exception("Manager has no object assigned!");
        SportObjectContent content = sportObjectService.findContent(manager.getSportObject().getId(), request.params(":name"));
        return gson.toJson(content);
    }

    public static String addContent(Request request, Response response) throws Exception{
        Manager manager = (Manager)userService.findByUsername(request.attribute("username"));
        TrainingSession training = gson.fromJson(request.body(), TrainingSession.class);
        SportObjectContent content = training.getFlag().toLowerCase().equals("training")? training : (SportObjectContent) training;
        SportObject sportObject = sportObjectService.addContent(manager.getSportObject().getId(), content);
        return gson.toJson(sportObject);
    }

    public static String updateContent(Request request, Response response){
       TrainingSession content = gson.fromJson(request.body(), TrainingSession.class);
       SportObject sportObject = sportObjectService.updateContent(request.params(":name"), content);
       return gson.toJson(sportObject);
    }

    public static String filterSportObjects(Request request, Response response){
        List<SportObject> filteredSportObjects = sportObjectService.filterSportObjects(request.queryMap().toMap());
        return gson.toJson(filteredSportObjects);
    }

    public static String create(Request request, Response response) throws Exception{
        SportObject sportObject = gson.fromJson(request.body(), SportObject.class);
        sportObject = sportObjectService.create(sportObject);
        userService.registerObjectToManager(request.queryParams("username"), sportObject.getId());
        return successfullyCreatedObjectResponse(sportObject, response);
    }

    public static String uploadSportObjectLogo(Request request, Response response) throws Exception {
        SportObject sportObject = sportObjectService.findById(Integer.parseInt(request.params(":id")));
        String logoUrl = uploadFile(request, sportObject.getName() + "-" + sportObject.getId());
        sportObjectService.updateLogoUrlForObject(sportObject, url + logoUrl);
        return successResponse();
    }

    public static String uploadSportObjectContentLogo(Request request, Response response) throws Exception {
        Manager manager = (Manager)userService.findByUsername(request.attribute("username"));
        String name = request.params(":file-name");
        return url + uploadFile(request, name + "-" + manager.getSportObject().getId());
    }

    public static String getManagerViewData(Request request, Response response) throws Exception {
        Manager manager = (Manager) userService.findByUsername(request.attribute("username"));
        if(manager == null) throw new Exception("User doesn't exist!");
        SportObject sportObject = manager.getSportObject();
        return packDataForManager(sportObject);
    }

    public static String getObjectAdditionalTrainings(Request request, Response response) throws Exception {
        int objectId = Integer.parseInt(request.params(":id"));
        List<TrainingSession> privateTrainings = sportObjectService.findAdditionalTrainingContent(objectId);
        return gson.toJson(privateTrainings);
    }

    public static String getObjectGroupTrainings(Request request, Response response) {
        int objectId = Integer.parseInt(request.params(":id"));
        List<TrainingSession> groupTrainings = sportObjectService.findTrainingContentByType(objectId, Constants.TrainingType.GROUP_TRAINING);
        return gson.toJson(groupTrainings);
    }

    public static String getObjectPersonalTrainings(Request request, Response response) {
        int objectId = Integer.parseInt(request.params(":id"));
        List<TrainingSession> personalTrainings = sportObjectService.findTrainingContentByType(objectId, Constants.TrainingType.PERSONAL_TRAINING);
        return gson.toJson(personalTrainings);
    }

    private static String packDataForManager(SportObject sportObject) {
        List<User> sportObjectCoaches = userService.findCoachesForSportObject(sportObject.getId());
        List<User> sportObjectVisited = userService.findBuyersThatVisitedSportObject(sportObject.getId());
        List<UserProfileDTO> coachProfiles = mapUsersToProfiles(sportObjectCoaches);
        List<UserProfileDTO> buyerProfiles = mapUsersToProfiles(sportObjectVisited);
        return gson.toJson(new ManagerDataViewDTO(sportObject, coachProfiles, buyerProfiles));
    }

    private static String successfullyCreatedObjectResponse(SportObject sportObject, Response response) {
        response.status(201);
        return gson.toJson(sportObject);
    }





}
