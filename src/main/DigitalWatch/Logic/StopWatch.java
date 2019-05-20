package Logic;
public class StopWatch {
    public Time currentStopwatch;
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