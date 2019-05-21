package Logic;
import java.time.LocalDateTime;

public class Dday {
    public LocalDateTime startDday;
    public LocalDateTime endDday;
    public Boolean displayType;

    public LocalDateTime getStartDday() {
        return this.startDday;
    }

    public void setStartDday(LocalDateTime startDday) {
        this.startDday = startDday;
    }

    public LocalDateTime getEndDday() {
        return endDday;
    }

    public void setEndDday(LocalDateTime endDday) {
        this.endDday = endDday;
    }

    public LocalDateTime loadStartDday() {
        return this.startDday;
    }
    public LocalDateTime loadEndDday() {
        return this.endDday;
    }
    public void saveDday(LocalDateTime startDday, LocalDateTime endDday) {
        this.startDday = startDday;
        this.endDday = endDday;
    }
    public void reset() {
        this.startDday = null;
        this.endDday = null;
    }
    public Boolean changeFormat() {
        if(this.startDday == null)
            return (this.displayType = true);
        else if(this.startDday != null && this.endDday != null)
            return this.displayType = !this.displayType;

        return true;
    }
    public void ring() {
        // TODO implement here
        return ;
    }
}