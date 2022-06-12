package Utils.Adapters;

import Model.*;
import Utils.Constants;
import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class UserProfileSerializer implements JsonSerializer<User> {


    @Override
    public JsonElement serialize(User src, Type typeOfSrc, JsonSerializationContext context) {

        Constants.UserRole role = src.getRole();

        if(role.equals(Constants.UserRole.ADMIN)) {
            return context.serialize(src, Administrator.class);
        }
        if(role.equals(Constants.UserRole.COACH)) {
            return context.serialize(src, Coach.class);
        }
        if(role.equals(Constants.UserRole.MANAGER)) {
            return context.serialize(src, Manager.class);
        }
        if(role.equals(Constants.UserRole.BUYER)) {
            return context.serialize(src, Buyer.class);
        }

        return null;
    }
}
