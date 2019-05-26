package Logic;
import GUI.DigitalWatch;

import java.time.LocalDate;
import java.time.LocalDateTime;

import java.time.LocalTime;
import java.time.Month;
import java.util.Timer;
import java.util.TimerTask;

public class AlarmTime extends TimerTask {
    public LocalTime currentAlarm;
    public Boolean isEnabled;
    private Timer m_timer;//new in this class
    private TimeKeeping timeKeeping; // new in this class


    public AlarmTime(Timer m_timer, TimeKeeping timeKeeping) {
        currentAlarm = LocalTime.of(0, 0,0,0);
        this.timeKeeping = timeKeeping;
        this.m_timer = m_timer;
        this.isEnabled = false;
        m_timer.schedule(this,0,1000);
    }

    public LocalDateTime loadAlarmData() {
        return LocalDateTime.of(LocalDate.now(),this.currentAlarm);
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
        return LocalDateTime.of(LocalDate.now(),currentAlarm);
    }

    public void setCurrentAlarm(LocalDateTime currentAlarm) {
        this.currentAlarm = currentAlarm.toLocalTime();
    }

    public Boolean getIsEnabled() {
        return this.isEnabled;
    }

    public void setEnabled(Boolean enabled) {
        this.isEnabled = enabled;
    }

    public void ring() {
        DigitalWatch.getInstance().beep();
    }
    public Boolean getEnabled() {
        return isEnabled;
    }

    @Override
    public void run(){
        LocalDateTime currentTime = timeKeeping.getCurrentTime();
        if(currentAlarm.getHour() == currentTime.getHour() &&
                currentAlarm.getMinute() == currentTime.getMinute() &&
                currentAlarm.getSecond() == currentTime.getSecond() &&
                isEnabled
        ){
            ring();
        }
    }

}