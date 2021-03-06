package Controller;

import DTO.profile.UserProfileDTO;
import Model.*;
import Service.UserService;
import Utils.Constants;
import spark.Request;
import spark.Response;

import java.util.List;

public class UserController extends Controller {
    private static UserService userService;

    public static void initContext(UserService usrService) {
        userService = usrService;
    }

    public static String getOne(Request request, Response response) {
        String username = request.attribute("username");
        User user = userService.findByUsername(username);
        return gson.toJson(UserProfileDTO.createProfile(user));
    }

    public static String getAll(Request request, Response response) {
        List<User> users = userService.findAll(request.queryMap().toMap());
        return gson.toJson(mapUsersToProfiles(users));
    }

    public static String getCoaches(Request request, Response response){
        List<User> coaches = userService.getUsersByRole(Constants.UserRole.COACH);
        return gson.toJson(mapUsersToProfiles(coaches));
    }

    public static String editOne(Request request, Response response) throws Exception{
        String username = request.attribute("username");
        User user = gson.fromJson(request.body(), userService.getClassByUsername(username));
        user.setUsername(username);
        userService.update(user);
        return successResponse();
    }

    public static String getRole(Request request, Response response) throws Exception {
        String username = request.attribute("username");
        User user = userService.findByUsername(username);
        if(user == null) throw new Exception("User does not exist");
        return successfulDataResponse("role", user.getRole().toString());
    }

    public static String registerObjectToManager(Request request, Response response) throws Exception{
        String username = request.params(":username");
        userService.registerObjectToManager(username, Integer.parseInt(request.params(":id")));
        return successResponse();
    }

    public static String deleteUser(Request request, Response response) {
        String username = request.params(":username");
        userService.deleteByUsername(username);
        return statusDeletedResponse(response);
    }



}
