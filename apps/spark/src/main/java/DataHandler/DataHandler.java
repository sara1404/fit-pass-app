package DataHandler;

import Model.*;
import Utils.RuntimeTypeAdapterFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataHandler <T>{
    private String path;
    private Gson gson;
    private RuntimeTypeAdapterFactory<User> adapter;

    public DataHandler(String path, Class mainClass, Class ...subtypes) {
        this.path = path;
        adapter = RuntimeTypeAdapterFactory.of(mainClass);
        registerSubtypes(subtypes);
        gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapterFactory(adapter).create();
    }

    public void writeToFile(List<T> objects) {
        try {
            FileWriter writer = new FileWriter(path);
            writer.write(gson.toJson(objects));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<T> readFromFile() {

        try (FileInputStream fis = new FileInputStream(path);)
        {
            String content = extractContentFromFile(fis);
            List<T> users = gson.fromJson(content, new TypeToken<List<User>>(){}.getType());
            return users;
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return new ArrayList<T>();
    }

    private String extractContentFromFile(FileInputStream fis) throws IOException{
        File file = new File(path);
        byte[] data = new byte[(int) file.length()];
        fis.read(data);
        return new String(data, "UTF-8");
    }

    private void registerSubtypes(Class ...subtypes) {
        for(Class type : subtypes) {
            adapter.registerSubtype(type);
        }
    }
}
