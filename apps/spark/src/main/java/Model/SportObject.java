package Model;

import Utils.Constants;
import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class SportObject {
    @Expose
    private int id;
    private String name;
    private Constants.SportObjectType type;
    private ArrayList<SportObjectContent> content;
    private Constants.SportObjectStatus status;
    private Location location;
    private String logoUrl;
    private double averageMark;
    private ArrayList<WorkDay> workTime;


    public SportObject(String name, Constants.SportObjectType type, ArrayList<SportObjectContent> content, Constants.SportObjectStatus status, Location location, String logoUrl, double averageMark, ArrayList<WorkDay> workTime) {
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

    public ArrayList<SportObjectContent> getContent() {
        return content;
    }

    public void setContent(ArrayList<SportObjectContent> content) {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public boolean doesContentAlreadyExists(String name){
        for (SportObjectContent content : this.content){
            if(content.getName().toLowerCase().equals(name.toLowerCase()))
                return true;
        }
        return false;
    }

    public void addContent(SportObjectContent content){
        this.content.add(content);
    }

    public SportObjectContent findSpecificContent(String id){
        for(SportObjectContent content : this.content){
            if(content.getName().toLowerCase().equals(id.toLowerCase()))
                return content;
        }
        return null;
    }

}
