package Controller;

import Model.User;
import Utils.Adapters.*;
import com.google.gson.*;
import spark.QueryParamsMap;
import spark.Response;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

public class Controller {

    protected static Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new LocalDateSerializer())
            .registerTypeAdapter(LocalDate.class, new LocalDateDeserializer())
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer())
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer())
            .registerTypeAdapter(User.class, new UserProfileDeserializer())
            .create();

    protected static String successResponse() {
        return "{ \"status\": \"SUCCESS\"}";
    }

    protected static String tokenResponse(String token) {
        return "{ \"token\": \"" + token +"\"}";
    }

    protected static String statusCreatedResponse(Response response) {
        response.status(201);
        return successResponse();
    }



}
