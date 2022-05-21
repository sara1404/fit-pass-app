package Model;

import Utils.Constants;

public class SportObject {
    private String name;
    private Constants.SportObjectType type;
    private Constants.SportObjectOffer content;
    private Constants.SportObjectStatus status;
    private Location location;
    private String logoUrl;
    private double averageMark;
    private WorkTime workTime;


    public SportObject(String name, Constants.SportObjectType type, Constants.SportObjectOffer content, Constants.SportObjectStatus status, Location location, String logoUrl, double averageMark, WorkTime workTime) {
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

    public Constants.SportObjectOffer getContent() {
        return content;
    }

    public void setContent(Constants.SportObjectOffer content) {
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

    public WorkTime getWorkTime() {
        return workTime;
    }

    public void setWorkTime(WorkTime workTime) {
        this.workTime = workTime;
    }
}
