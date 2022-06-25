package Controller;

import Model.SportObject;
import Model.SportObjectContent;
import Service.SportObjectService;
import spark.Request;
import spark.Response;

import java.util.List;

public class SportObjectController extends Controller{
    private static SportObjectService sportObjectService;


    public static void initContext(SportObjectService sportObjService) {
        sportObjectService = sportObjService;
    }

    public static String getAll(Request request, Response response){
        List<SportObject> sportObjects = sportObjectService.findAll();
        return gson.toJson(sportObjects);
    }

    public static String getOne(Request request, Response response){
        SportObject sportObject = sportObjectService.findOne(request.params(":id"));
        return gson.toJson(sportObject);
    }

    public static String getOneContent(Request request, Response response){
        SportObjectContent content = sportObjectService.findContent(request.params(":id"), request.params(":content"));
        return gson.toJson(content);
    }

    public static String addContent(Request request, Response response) throws Exception{
        SportObjectContent content = gson.fromJson(request.body(), SportObjectContent.class);
        SportObject sportObject = sportObjectService.addContent(request.params(":id"), content);
        return gson.toJson(sportObject);
    }

    public static String updateContent(Request request, Response response){
       SportObjectContent content = gson.fromJson(request.body(), SportObjectContent.class);
       SportObject sportObject = sportObjectService.updateContent(request.params(":id"), request.params(":content"), content);
       return gson.toJson(sportObject);
    }

    public static String filterSportObjects(Request request, Response response){
        List<SportObject> filteredSportObjects = sportObjectService.filterSportObjects(request.queryMap().toMap());
        return gson.toJson(filteredSportObjects);
    }


}
