package DataHandler;

import Model.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PromoCodesDataHandler extends TemplateDataHandler<PromoCode>{

    protected final Gson gson;
    private final String path;

    public PromoCodesDataHandler(String path) {
        gson = gsonBuilder.create();
        this.path = path;
    }


    @Override
    public void writeToFile(List<PromoCode> promoCodes) {
        try(FileWriter writer = new FileWriter(path)){
            writer.write(gson.toJson(promoCodes));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<PromoCode> readFromFile() {

        try (FileInputStream fis = new FileInputStream(path);)
        {
            String content = extractContentFromFile(fis);
            List<PromoCode> promoCodes = gson.fromJson(content, new TypeToken<List<PromoCode>>(){}.getType());
            return promoCodes;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<PromoCode>();
    }

    private String extractContentFromFile(FileInputStream fis) throws IOException{
        File file = new File(path);
        byte[] data = new byte[(int) file.length()];
        fis.read(data);
        return new String(data, "UTF-8");
    }
}
