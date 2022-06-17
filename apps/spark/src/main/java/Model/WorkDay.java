package Model;

public class WorkDay {
    private String day;
    private WorkTime time;

    public WorkDay(String day, WorkTime workTime) {
        this.day = day;
        this.time = workTime;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public WorkTime getTime() {
        return time;
    }

    public void setTime(WorkTime time) {
        this.time = time;
    }
}
