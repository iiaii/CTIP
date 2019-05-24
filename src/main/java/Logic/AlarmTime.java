package Logic;
import java.time.LocalDate;
import java.time.LocalDateTime;

import java.time.LocalTime;
import java.time.Month;
import java.util.Timer;
import java.util.TimerTask;

public class AlarmTime extends TimerTask {
    public LocalTime currentAlarm;
    public Boolean isEnabled=false;
    private Timer m_timer;//new in this class
    private TimeKeeping timeKeeping; // new in this class

    public AlarmTime(Timer m_timer,TimeKeeping timeKeeping) {
        currentAlarm = LocalTime.of(0, 0,0,0);
        this.timeKeeping = timeKeeping;
        this.m_timer = m_timer;
    }

    public LocalDateTime loadAlarmData() {
        return LocalDateTime.of(LocalDate.now(),this.currentAlarm);
    }
    public void saveAlarmData(LocalDateTime data) {
        this.currentAlarm = data.toLocalTime();
    }
    public void enable() {
        this.isEnabled = true;
        m_timer.schedule(this,0,60000);
    }
    public void disable() {
        this.isEnabled = false;
        cancel();
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
    @Override
    public void run(){
        LocalDateTime currentTime = timeKeeping.getCurrentTime();
        if(currentAlarm.getHour() == currentTime.getHour() &&
                currentAlarm.getMinute() == currentTime.getMinute()
        ){
            //Beep action hear
            System.out.println("BEEP!");
        }
    }
}