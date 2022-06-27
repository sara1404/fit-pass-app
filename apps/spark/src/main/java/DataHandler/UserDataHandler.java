package DataHandler;

import Model.User;
import Utils.Adapters.UserProfileDeserializer;
import Utils.Adapters.UserProfileSerializer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserDataHandler extends TemplateDataHandler<User>{
    private final String path;
    private final Gson gson;

    public UserDataHandler(String path) {
        gson = gsonBuilder
                .registerTypeAdapter(User.class, new UserProfileSerializer())
                .registerTypeAdapter(User.class, new UserProfileDeserializer())
                .create();
        this.path = path;
    }

    @Override
    public void writeToFile(List<User> objects) {
        try (FileWriter writer = new FileWriter(path)) {
            writer.write(gson.toJson(objects));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> readFromFile() {

        try (FileInputStream fis = new FileInputStream(path);)
        {
            String content = extractContentFromFile(fis);
            List<User> users = gson.fromJson(content, new TypeToken<List<User>>(){}.getType());
            return users;
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return new ArrayList<User>();
    }

    private String extractContentFromFile(FileInputStream fis) throws IOException{
        File file = new File(path);
        byte[] data = new byte[(int) file.length()];
        fis.read(data);
        return new String(data, "UTF-8");
    }
}
