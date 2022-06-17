package DataHandler;

import Model.SportObject;
import Model.User;
import Utils.Adapters.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class SportObjectDataHandler {
    protected static Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(LocalDate.class, new LocalDateSerializer())
            .registerTypeAdapter(LocalDate.class, new LocalDateDeserializer())
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer())
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer())
            .registerTypeAdapter(LocalTime.class, new LocalTimeSerializer())
            .registerTypeAdapter(LocalTime.class, new LocalTimeDeserializer())
            .registerTypeAdapter(User.class, new UserProfileDeserializer())
            .create();

    private String path;

    public SportObjectDataHandler(String path) {
        this.path = path;
    }

    public void writeToFile(List<SportObject> objects) {
        try(FileWriter writer = new FileWriter(path)){
            writer.write(gson.toJson(objects));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<SportObject> readFromFile() {

        try (FileInputStream fis = new FileInputStream(path);)
        {
            String content = extractContentFromFile(fis);
            List<SportObject> objects = gson.fromJson(content, new TypeToken<List<SportObject>>(){}.getType());
            return objects;
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return new ArrayList<SportObject>();
    }

    private String extractContentFromFile(FileInputStream fis) throws IOException{
        File file = new File(path);
        byte[] data = new byte[(int) file.length()];
        fis.read(data);
        return new String(data, "UTF-8");
    }
}
