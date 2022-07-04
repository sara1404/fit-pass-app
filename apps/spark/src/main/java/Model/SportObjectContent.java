package Model;

import Utils.Constants;
import com.google.gson.annotations.Expose;

import java.time.LocalDate;
import java.util.List;

public class SportObjectContent {
    @Expose
    protected String name;
    @Expose
    protected Constants.TrainingType type;
    @Expose
    protected String pictureUrl;
    @Expose
    protected String flag;
    @Expose
    protected List<WorkDay> workTime;


    public SportObjectContent(String name, Constants.TrainingType type, String picturUrl, String flag, List<WorkDay> workTime) {
        this.name = name;
        this.type = type;
        this.pictureUrl = picturUrl;
        this.flag = flag;
        this.workTime = workTime;
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

    public List<WorkDay> getWorkTime() {
        return workTime;
    }

    public void setWorkTime(List<WorkDay> workTime) {
        this.workTime = workTime;
    }

    public void update(SportObjectContent content){
        setType(content.getType());
        setPictureUrl(content.getPictureUrl());
    }
}
