package Controller;

import DTO.ManagerDataViewDTO;
import DTO.profile.ManagerProfileDTO;
import DTO.profile.UserProfileDTO;
import Model.*;
import Service.SportObjectService;
import Service.UserService;
import spark.Request;
import spark.Response;

import java.util.List;

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
        SportObjectContent content = gson.fromJson(request.body(), SportObjectContent.class);
        SportObject sportObject = sportObjectService.addContent(manager.getSportObject().getId(), content);
        return gson.toJson(sportObject);
    }

    //TODO Popraviti generalno putanju, tip podatka za id ne odgovara, plus sto je izmijnjana putanja
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
        String logoUrl = uploadFile(request, sportObject.getName());
        sportObjectService.updateLogoUrlForObject(sportObject, url + logoUrl);
        return successResponse();
    }

    public static String getManagerViewData(Request request, Response response) throws Exception {
        Manager manager = (Manager) userService.findByUsername(request.attribute("username"));
        if(manager == null) throw new Exception("User doesn't exist!");
        SportObject sportObject = manager.getSportObject();
        return packDataForManager(sportObject);
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
