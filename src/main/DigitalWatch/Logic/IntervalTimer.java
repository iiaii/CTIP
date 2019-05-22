package Logic;
import java.time.LocalDateTime;
public class IntervalTimer {
    public int iteration;
    public LocalDateTime savedIntervalTimer;
    public LocalDateTime remainedIntervalTimer;
    public Boolean isEnabled;

    public IntervalTimer() {
        this.iteration = 0;
        this.isEnabled = false;
    }
    public void enable() {
        this.isEnabled = true;
    }
    public void disable() {
        this.isEnabled = false;
    }
    public void reset() {
        this.savedIntervalTimer = null;
        this.remainedIntervalTimer = null;
        this.isEnabled = false;
    }
    public LocalDateTime loadIntervalTimer() {
        return this.savedIntervalTimer;
    }
    public void saveIntervalTimer(LocalDateTime data) {
        this.savedIntervalTimer = data;
    }
    public void ring() {
        return ;
    }

}