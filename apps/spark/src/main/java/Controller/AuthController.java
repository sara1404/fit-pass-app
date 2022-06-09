package Controller;

import DTO.UserAuthDTO;
import DTO.profile.UserProfileDTO;
import Exceptions.AuthException;
import Model.*;
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
        if(request.requestMethod().equals("OPTIONS")) {
           SetupController.enableCORSForFilters(request, response);
        }
        try {
            String token = authService.extractTokenFromRequest(request);
            String username = authService.verifyToken(token);
            request.attribute("username", username);
        } catch(Exception e) {
            e.printStackTrace();
            throw new AuthException(401, "Unauthorized");
        }
    }

    public static String login(Request request, Response response) throws Exception {
        UserAuthDTO userAuthDTO = gson.fromJson(request.body(), UserAuthDTO.class);
        User user = authService.login(userAuthDTO.username, userAuthDTO.password);
        String token = authService.signToken(user);
        return tokenResponse(token);
    }

    public static String registerBuyer(Request request, Response response) throws Exception{
        User user = gson.fromJson(request.body(), Buyer.class);
        System.out.println(user);
        user.setRole(Constants.UserRole.BUYER);
        authService.register(user);
        return statusCreatedResponse(response);
    }

    public static String adminRegistration(Request request, Response response) throws Exception {
        User user = gson.fromJson(request.body(), User.class);
        authService.register(user);
        return statusCreatedResponse(response);
    }

    public static void authorize(Request request, Constants.UserRole ...roles) throws AuthException{
        String username = request.attribute("username");
        User user = userService.findByUsername(username);
        if(Arrays.stream(roles).noneMatch(user.getRole()::equals)) throw new AuthException(403, "Forbidden!");
    }






}
