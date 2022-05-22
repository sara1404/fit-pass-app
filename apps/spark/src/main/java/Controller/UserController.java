package Controller;

import DTO.UserProfileDTO;
import Model.Address;
import Model.Location;
import Model.User;
import Service.UserService;
import spark.Request;
import spark.Response;

public class UserController extends Controller {
    private static UserService userService;

    public static void initContext(UserService usrService) {
        userService = usrService;
    }


    public static String getOne(Request request, Response response) {
        String username = request.attribute("username");
        User user = userService.findByUsername(username);
        return gson.toJson(new UserProfileDTO(user));
    }
}
