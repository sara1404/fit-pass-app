package DataHandler;

import Model.Subscription;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SubscriptionsPackagesDataHandler extends TemplateDataHandler<Subscription> {

    protected final Gson gson;
    private final String path;

    public SubscriptionsPackagesDataHandler(String path) {
        gson = gsonBuilder.create();
        this.path = path;
    }


    @Override
    public void writeToFile(List<Subscription> objects) {
        try (FileWriter writer = new FileWriter(path)) {
            writer.write(gson.toJson(objects));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Subscription> readFromFile() {
        try (FileInputStream fis = new FileInputStream(path);)
        {
            String content = extractContentFromFile(fis);
            List<Subscription> subscriptions = gson.fromJson(content, new TypeToken<List<Subscription>>(){}.getType());
            return subscriptions;
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return new ArrayList<Subscription>();
    }

    private String extractContentFromFile(FileInputStream fis) throws IOException{
        File file = new File(path);
        byte[] data = new byte[(int) file.length()];
        fis.read(data);
        return new String(data, "UTF-8");
    }
}
