package DataHandler;

import Model.Comment;
import Model.SportObject;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CommentDataHandler extends TemplateDataHandler<Comment> {
    private Gson gson;
    private final String path;

    public CommentDataHandler(String path) {
        this.path = path;
        gson = gsonBuilder
                .excludeFieldsWithoutExposeAnnotation()
                .create();
    }

    @Override
    public void writeToFile(List<Comment> objects) {
        try(FileWriter writer = new FileWriter(path)){
            writer.write(gson.toJson(objects));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Comment> readFromFile() {

        try (FileInputStream fis = new FileInputStream(path);)
        {
            String content = extractContentFromFile(fis);
            List<Comment> objects = gson.fromJson(content, new TypeToken<List<Comment>>(){}.getType());
            return objects;
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return new ArrayList<Comment>();
    }

    private String extractContentFromFile(FileInputStream fis) throws IOException{
        File file = new File(path);
        byte[] data = new byte[(int) file.length()];
        fis.read(data);
        return new String(data, "UTF-8");
    }
}
