package Logic;
import java.time.LocalDateTime;
public class TimeKeeping {
    public LocalDateTime currentTime;
    public Boolean displayFormat = true;

    public LocalDateTime loadTime() {
        return this.currentTime;
    }

    public void saveTime(LocalDateTime data) {
        // TODO implement here
        this.currentTime = data;
    }

    public void setHourformat() {
        // TODO implement here
        this.displayFormat = !this.displayFormat;
    }
}