package Controller;

import Utils.Adapters.LocalDateDeserializer;
import Utils.Adapters.LocalDateSerializer;
import Utils.Adapters.LocalDateTimeDeserializer;
import Utils.Adapters.LocalDateTimeSerializer;
import com.google.gson.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Controller {

    protected static Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new LocalDateSerializer())
            .registerTypeAdapter(LocalDate.class, new LocalDateDeserializer())
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer())
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer())
            .create();

    protected static String successResponse() {
        return "{ \"status\": \"SUCCESS\"}";
    }

    protected static String tokenResponse(String token) {
        return "{ \"token\": \"" + token +"\"}";
    }


}
