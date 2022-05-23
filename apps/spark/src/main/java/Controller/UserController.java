package Controller;

import DTO.BuyerDTO;
import DTO.UserProfileDTO;
import Model.Address;
import Model.Buyer;
import Model.Location;
import Model.User;
import Service.UserService;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.List;

public class UserController extends Controller {
    private static UserService userService;

    public static void initContext(UserService usrService) {
        userService = usrService;
    }


    public static String getOne(Request request, Response response) {
        String username = request.attribute("username");
        User user = userService.findByUsername(username);
        if(user instanceof Buyer) {
            return gson.toJson(new BuyerDTO((Buyer)user));
        }
        return gson.toJson(new UserProfileDTO(user));
    }

    public static String getAll(Request request, Response response) {
        List<UserProfileDTO> profiles = userService.mapUsersToProfiles();
        return gson.toJson(profiles);
    }
}
