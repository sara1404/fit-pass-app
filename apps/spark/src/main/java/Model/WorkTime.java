package Model;

import java.time.LocalDateTime;

public class WorkTime {

    private LocalDateTime starts;
    private LocalDateTime ends;

    public WorkTime(LocalDateTime starts, LocalDateTime ends) {
        this.starts = starts;
        this.ends = ends;
    }

    public LocalDateTime getStarts() {
        return starts;
    }

    public void setStarts(LocalDateTime starts) {
        this.starts = starts;
    }

    public LocalDateTime getEnds() {
        return ends;
    }

    public void setEnds(LocalDateTime ends) {
        this.ends = ends;
    }
}
