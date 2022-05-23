package Controller;

import DTO.BuyerProfileDTO;
import DTO.ProfileCreationFactory;
import DTO.UserProfileDTO;
import Model.Buyer;
import Model.User;
import Service.UserService;
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
        return gson.toJson(ProfileCreationFactory.createProfileDTO(user));
//        if(user instanceof Buyer) {
//            return gson.toJson(new BuyerProfileDTO((Buyer)user));
//        }
//        return gson.toJson(new UserProfileDTO(user));
    }

    public static String getAll(Request request, Response response) {
        List<UserProfileDTO> profiles = userService.mapUsersToProfiles();
        return gson.toJson(profiles);
    }

    public static String editOne(Request request, Response response) throws Exception{
        String username = request.attribute("username");
        User user = gson.fromJson(request.body(), User.class);
        if(!username.equals(user.getUsername())) throw new Exception("You can't edit someone else's profiles!");
        userService.update(user);
        return successResponse();
    }


}
