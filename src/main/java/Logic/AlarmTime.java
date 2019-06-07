package Logic;

import GUI.DigitalWatch;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Timer;
import java.util.TimerTask;

public class AlarmTime extends TimerTask {
    public LocalTime currentAlarm;
    public boolean isEnabled;
    private TimeKeeping timeKeeping; // new in this class


    public AlarmTime(Timer m_timer, TimeKeeping timeKeeping) {
        currentAlarm = LocalTime.of(0, 0, 0, 0);
        this.timeKeeping = timeKeeping;
        this.isEnabled = false;
        m_timer.schedule(this, 0, 1000);
    }

    public LocalDateTime loadAlarmData() {
        return LocalDateTime.of(LocalDate.now(), this.currentAlarm);
    }

    public void saveAlarmData(LocalDateTime data) {
        this.currentAlarm = data.toLocalTime();
    }

    public void enable() {
        this.isEnabled = true;
    }

    public void disable() {
        this.isEnabled = false;
    }

    public LocalDateTime getCurrentAlarm() {
        return LocalDateTime.of(LocalDate.now(), currentAlarm);
    }

    public void setCurrentAlarm(LocalDateTime currentAlarm) {
        this.currentAlarm = currentAlarm.toLocalTime();
    }

    public void ring() {
        DigitalWatch.getInstance().beep();
    }

    public boolean getEnabled() {
        return isEnabled;
    }

    @Override
    public void run() {
        LocalDateTime currentTime = timeKeeping.getCurrentTime();
        if (currentAlarm.getHour() == currentTime.getHour() &&
                currentAlarm.getMinute() == currentTime.getMinute() &&
                currentAlarm.getSecond() == currentTime.getSecond() &&
                isEnabled
        ) {
            ring();
        }
    }

}