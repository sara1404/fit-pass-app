package Utils.Adapters;

import Model.*;
import Utils.RuntimeTypeAdapterFactory;
import com.google.gson.*;

import java.lang.reflect.Type;

public class UserProfileDeserializer implements JsonDeserializer<User> {

    @Override
    public User deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        String role = json.getAsJsonObject().get("role").getAsString();

        if(role.equals("ADMIN")) {
            return context.deserialize(json, Administrator.class);
        }
        if(role.equals("COACH")) {
            return context.deserialize(json, Coach.class);
        }
        if(role.equals("MANAGER")) {
            return context.deserialize(json, Manager.class);
        }
        if(role.equals("BUYER")) {
            return context.deserialize(json, Buyer.class);
        }

        return null;
    }
}
