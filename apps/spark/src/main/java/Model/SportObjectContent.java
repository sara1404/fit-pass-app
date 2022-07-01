package Model;

import Utils.Constants;
import com.google.gson.annotations.Expose;

import java.time.LocalDate;

public class SportObjectContent {
    @Expose
    private String name;
    @Expose
    private Constants.TrainingType type;
    @Expose
    private String pictureUrl;
    @Expose
    private String flag;


    public SportObjectContent(String name, Constants.TrainingType type, String picturUrl, String flag) {
        this.name = name;
        this.type = type;
        this.pictureUrl = picturUrl;
        this.flag = flag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Constants.TrainingType  getType() {
        return type;
    }

    public void setType(Constants.TrainingType  type) {
        this.type = type;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public void update(SportObjectContent content){
        setType(content.getType());
        setPictureUrl(content.getPictureUrl());
    }
}
