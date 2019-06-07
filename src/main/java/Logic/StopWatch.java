package Logic;

import java.time.LocalTime;
import java.util.Timer;
import java.util.TimerTask;

public class StopWatch extends TimerTask {
    private LocalTime currentStopwatch;
    private boolean isActivated = false;
    private int countDay = 0;

    public StopWatch(Timer m_timer) {
        currentStopwatch = LocalTime.of(0, 0, 0);
        m_timer.schedule(this, 0, 1000);
    }

    @Override
    public void run() {
        if (this.isActivated == true) {
            this.currentStopwatch = this.currentStopwatch.plusSeconds(1);
            if (this.currentStopwatch.equals(LocalTime.MAX.plusNanos(1))) {
                countDay += 1;
            }
        }
    }

    public void setActivated(boolean activated) {
        isActivated = activated;
    }

    public LocalTime getCurrentStopwatch() {
        return currentStopwatch;
    }

    public void setCurrentStopwatch(LocalTime currentStopwatch) {
        this.currentStopwatch = currentStopwatch;
    }

    public boolean getActivated() {
        return isActivated;
    }

    public void activate() {
        this.isActivated = true;

    }

    public void pause() {
        this.isActivated = false;
    }

    public void reset() {
        if (!this.isActivated) {
            currentStopwatch = LocalTime.of(0, 0, 0);
            this.isActivated = false;
            this.countDay = 0;
        }
    }

    public LocalTime loadStopWatch() {
        return currentStopwatch;
    }

    public int getCountDay() {
        return countDay;
    }
}