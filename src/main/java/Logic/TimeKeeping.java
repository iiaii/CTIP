package Logic;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Timer;
import java.util.TimerTask;

public class TimeKeeping extends TimerTask {
    private LocalDateTime currentTime;
    private boolean displayFormat = true;

    public boolean getDisplayFormat() {
        return displayFormat;
    }

    public LocalDateTime getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(LocalDateTime currentTime) {
        this.currentTime = currentTime;
    }

    public TimeKeeping(Timer m_timer) {
        currentTime = LocalDateTime.of(LocalDate.of(9999,12,31), LocalTime.of(23,59,59));// LocalDateTime.now();
        m_timer.scheduleAtFixedRate(this, 0, 1000);
    }

    @Override
    public void run() {
        currentTime = currentTime.plusSeconds(1);
        if(currentTime.getYear() > 9999) { // 10000년 넘어가면
            currentTime = currentTime.minusYears(8030);
        }
        System.out.println(currentTime);
    }

    public LocalDateTime loadTime() {
        return currentTime;
    }

    public void saveTime(LocalDateTime data) {
        this.currentTime = data;
    }

    public void setHourformat() {
        this.displayFormat = !this.displayFormat;
    }

}