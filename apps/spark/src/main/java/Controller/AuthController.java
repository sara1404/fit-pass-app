package Controller;

import DTO.profile.BuyerProfileDTO;
import DTO.UserAuthDTO;
import Exceptions.AuthException;
import Model.Buyer;
import Model.User;
import Service.AuthService;
import Service.UserService;
import Utils.Constants;
import spark.Request;
import spark.Response;

import java.util.Arrays;

import static spark.Spark.halt;

public class AuthController extends Controller {

    private static AuthService authService;
    private static UserService userService;

    public static void initContext(AuthService athService, UserService usrService) {
        authService = athService;
        userService = usrService;
    }

    public static void authenticate(Request request, Response response) throws AuthException {
        try {

            String token = authService.extractTokenFromRequest(request);
            String username = authService.verifyToken(token);
            request.attribute("username", username);

        } catch(Exception e) {
            throw new AuthException(401, "Unauthorized");
        }
    }

    public static String login(Request request, Response response) throws Exception {
        UserAuthDTO userAuthDTO = gson.fromJson(request.body(), UserAuthDTO.class);
        User user = authService.login(userAuthDTO.username, userAuthDTO.password);

        String token = authService.signToken(user);
        response.cookie("Authorization", token);
        return tokenResponse(token);
    }

    public static String register(Request request, Response response) throws Exception{
        Buyer buyer = gson.fromJson(request.body(), Buyer.class);
        Buyer newBuyer = authService.register(buyer);

        return gson.toJson(new BuyerProfileDTO(newBuyer));
    }

    public static void authorize(Request request, Response response, Constants.UserRole ...roles) throws AuthException{
        String username = request.attribute("username");
        User user = userService.findByUsername(username);
        if(Arrays.stream(roles).noneMatch(user.getRole()::equals)) throw new AuthException(403, "Forbidden!");
    }




}
