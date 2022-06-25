package Model;

import Utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class SportObject {
    private String name;
    private Constants.SportObjectType type;
    private String content;
    private Constants.SportObjectStatus status;
    private Location location;
    private String logoUrl;
    private double averageMark;
    private ArrayList<WorkDay> workTime;


    public SportObject(String name, Constants.SportObjectType type, String content, Constants.SportObjectStatus status, Location location, String logoUrl, double averageMark, ArrayList<WorkDay> workTime) {
        this.name = name;
        this.type = type;
        this.content = content;
        this.status = status;
        this.location = location;
        this.logoUrl = logoUrl;
        this.averageMark = averageMark;
        this.workTime = workTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Constants.SportObjectType getType() {
        return type;
    }

    public void setType(Constants.SportObjectType type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Constants.SportObjectStatus getStatus() {
        return status;
    }

    public void setStatus(Constants.SportObjectStatus status) {
        this.status = status;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public double getAverageMark() {
        return averageMark;
    }

    public void setAverageMark(double averageMark) {
        this.averageMark = averageMark;
    }

    public List<WorkDay> getWorkTime() {
        return workTime;
    }

    public void setWorkTime(ArrayList<WorkDay> workTime) {
        this.workTime = workTime;
    }

    public boolean isFromCountry(String country) {
        return location.isFromCountry(country);
    }

    public boolean isFromCity(String city) {
        return location.isFromCity(city);
    }

    public void update(SportObject object) {
        setStatus(object.status);
        setLogoUrl(object.logoUrl);
        setContent(object.content);
        setLocation(object.location);
        setAverageMark(object.averageMark);
        setType(object.type);
        setWorkTime(object.workTime);
    }
}
