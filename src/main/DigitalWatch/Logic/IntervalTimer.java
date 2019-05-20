package Logic;

public class IntervalTimer {
    public int iteration;
    public Time savedIntervalTimer;
    public Time remainedIntervalTimer;
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
    public Time loadIntervalTimer() {
        return this.savedIntervalTimer;
    }
    public void saveIntervalTimer(Time data) {
        this.savedIntervalTimer = data;
    }
    public void ring() {
        return ;
    }

}