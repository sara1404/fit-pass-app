package Controller;

import Model.SportObject;
import Model.SportObjectContent;
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


    public static String updateContent(Request request, Response response) throws Exception{
        SportObjectContent content = gson.fromJson(request.body(), SportObjectContent.class);
        SportObject sportObject = sportObjectService.updateContent(request.params(":id"), content);
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
        return succesfullyCreatedObjectResponse(sportObject, response);
    }

    public static String uploadSportObjectLogo(Request request, Response response) throws Exception {
        SportObject sportObject = sportObjectService.findById(Integer.parseInt(request.params(":id")));
        String logoUrl = uploadFile(request, sportObject.getName());
        sportObjectService.updateLogoUrlForObject(sportObject, url + logoUrl);
        return successResponse();
    }

    public static String succesfullyCreatedObjectResponse(SportObject sportObject, Response response) {
        response.status(201);
        return gson.toJson(sportObject);
    }




}
