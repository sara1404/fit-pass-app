package DataHandler;

import Model.SportObject;
import Model.TrainingReservation;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TrainingReservationDataHandler extends TemplateDataHandler<TrainingReservation> {
    private final Gson gson;
    private final String path;

    public TrainingReservationDataHandler(String path) {
        this.path = path;
        this.gson = gsonBuilder.create();
    }

    @Override
    public void writeToFile(List<TrainingReservation> objects) {
        try(FileWriter writer = new FileWriter(path)){
            writer.write(gson.toJson(objects));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<TrainingReservation> readFromFile() {

        try (FileInputStream fis = new FileInputStream(path);)
        {
            String content = extractContentFromFile(fis);
            List<TrainingReservation> objects = gson.fromJson(content, new TypeToken<List<TrainingReservation>>(){}.getType());
            return objects;
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    private String extractContentFromFile(FileInputStream fis) throws IOException{
        File file = new File(path);
        byte[] data = new byte[(int) file.length()];
        fis.read(data);
        return new String(data, "UTF-8");
    }
}
