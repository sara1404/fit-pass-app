package Model;

import com.google.gson.annotations.Expose;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class WorkTime {

    @Expose
    private LocalTime starts;
    @Expose
    private LocalTime ends;

    public WorkTime(LocalTime starts, LocalTime ends) {
        this.starts = starts;
        this.ends = ends;
    }

    public LocalTime getStarts() {
        return starts;
    }

    public void setStarts(LocalTime starts) {
        this.starts = starts;
    }

    public LocalTime getEnds() {
        return ends;
    }

    public void setEnds(LocalTime ends) {
        this.ends = ends;
    }
}
