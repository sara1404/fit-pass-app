package DataHandler;

import Model.SportObject;
import Model.SportObjectContent;
import Model.TrainingSession;
import Model.User;
import Utils.Adapters.*;
import Utils.RuntimeTypeAdapterFactory;
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

public class SportObjectDataHandler extends TemplateDataHandler<SportObject>{
    protected final Gson gson;
    private final String path;

    public SportObjectDataHandler(String path) {
        RuntimeTypeAdapterFactory<SportObjectContent> socFactory = RuntimeTypeAdapterFactory.of(SportObjectContent.class);
        socFactory.registerSubtype(TrainingSession.class);
        socFactory.registerSubtype(SportObjectContent.class);
        gson = gsonBuilder
                .registerTypeAdapterFactory(socFactory)
                .registerTypeAdapter(User.class, new UserProfileDeserializer())
                .registerTypeAdapter(User.class, new UserProfileSerializer())
                .create();
        this.path = path;
    }

    @Override
    public void writeToFile(List<SportObject> objects) {
        try(FileWriter writer = new FileWriter(path)){
            writer.write(gson.toJson(objects));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
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
