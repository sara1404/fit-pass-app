package Controller;

import Model.User;
import Utils.Adapters.*;
import com.google.gson.*;
import spark.Request;
import spark.Response;

import javax.servlet.MultipartConfigElement;
import javax.servlet.http.Part;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.stream.Collectors;

public class Controller {

    protected static String url = "http://localhost:8000/";
    protected static Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new LocalDateSerializer())
            .registerTypeAdapter(LocalDate.class, new LocalDateDeserializer())
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer())
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer())
            .registerTypeAdapter(LocalTime.class, new LocalTimeSerializer())
            .registerTypeAdapter(LocalTime.class, new LocalTimeDeserializer())
            .registerTypeAdapter(User.class, new UserProfileDeserializer())
            .create();

    protected static String successResponse() {
        return "{ \"status\": \"SUCCESS\"}";
    }

    protected static String tokenResponse(String token) {
        return "{ \"token\": \"" + token +"\"}";
    }

    protected static String statusCreatedResponse(Response response) {
        response.status(201);
        return successResponse();
    }

    protected static String successfulDataResponse(String key, String data) {
        return "{\""+ key +"\": \""+ data + "\"}";
    }

    protected static String uploadFile(Request request, String name) throws Exception{

        String location = "public";
        long maxFileSize = 100000000;
        long maxRequestSize = 100000000;
        int fileSizeThreshold = 1024;

        MultipartConfigElement multipartConfigElement = new MultipartConfigElement(
                location, maxFileSize, maxRequestSize, fileSizeThreshold);
        request.raw().setAttribute("org.eclipse.jetty.multipartConfig",
                multipartConfigElement);

        String extension = extractExtensionFromFile(request.raw().getPart("file").getSubmittedFileName());
        if(!checkIfExtensionIsCorrect(extension)) {
            throw new Exception("Uploaded file should be of types jpg, jpeg or png!");
        }

        Path out = Paths.get("src/main/resources/public/uploads/" + name + extension);
        Part uploadedFile = request.raw().getPart("file");

        try (final InputStream in = uploadedFile.getInputStream()) {
            Files.copy(in, out, StandardCopyOption.REPLACE_EXISTING);
            uploadedFile.delete();
        }
        multipartConfigElement = null;
        uploadedFile = null;
        return "uploads/" + name + extension;
    }

    private static String extractExtensionFromFile(String filename) {
        String[] chunks = filename.split("\\.");
        return "." + chunks[chunks.length - 1];
    }

    private static boolean checkIfExtensionIsCorrect(String extension) {
        return extension.equals(".jpg") || extension.equals(".png") || extension.equals(".jpeg");
    }



}
