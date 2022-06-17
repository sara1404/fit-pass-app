package Model;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class WorkTime {

    private LocalTime starts;
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
