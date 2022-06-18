package Controller;

import Model.SportObject;
import Service.SportObjectService;
import spark.Request;
import spark.Response;

import java.util.Collection;
import java.util.List;
import java.util.Map;

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

    public static String filterSportObjects(Request request, Response response){
        List<SportObject> filteredSportObjects = sportObjectService.filterSportObjects(request.queryMap().toMap());
        return gson.toJson(filteredSportObjects);
    }


}
