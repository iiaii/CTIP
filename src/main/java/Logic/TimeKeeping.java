package Logic;

import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

public class TimeKeeping extends TimerTask {
    private LocalDateTime currentTime;
    private Boolean displayFormat = true;
    private Timer m_timer = null;

    public Boolean getDisplayFormat() {
        return displayFormat;
    }

    public LocalDateTime getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(LocalDateTime currentTime) {
        this.currentTime = currentTime;
    }

    public TimeKeeping(Timer m_timer) {
        currentTime = LocalDateTime.now();
        this.m_timer = m_timer;
        m_timer.scheduleAtFixedRate(this, 0, 1000);
    }

    @Override
    public void run() {
        currentTime = currentTime.plusSeconds(1);
    }

    public LocalDateTime loadTime() {
        return currentTime;
    }

    public void saveTime(LocalDateTime data) {
        this.currentTime = data;
    }

    public void setHourformat() {
        // TODO implement here
        this.displayFormat = !this.displayFormat;
    }

}