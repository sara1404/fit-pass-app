package Model;

import java.time.LocalDate;

public class SportObjectContent {
    private String name;
    private String type;
    private String pictureUrl;
    private String flag;


    public SportObjectContent(String name, String type, String picturUrl, String flag) {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
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
