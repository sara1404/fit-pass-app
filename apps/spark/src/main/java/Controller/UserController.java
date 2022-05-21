package Controller;

import Model.Address;
import Model.Location;
import spark.Request;
import spark.Response;

public class UserController extends Controller {

    public static String getOne(Request request, Response response) {
        Address address = new Address("janka cmelika", "20a", "novi sad", 21000);
        return gson.toJson(address);
    }
}
