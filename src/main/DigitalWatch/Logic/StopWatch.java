package Logic;
import java.time.LocalDateTime;

public class StopWatch {
    public LocalDateTime currentStopwatch;
    public Boolean isActivated;

    public void activate() {
        this.isActivated = true;
    }
    public void pause() {
        this.isActivated = false;
    }
    public void reset() {
        this.currentStopwatch = null;
        this.isActivated = false;
    }

}