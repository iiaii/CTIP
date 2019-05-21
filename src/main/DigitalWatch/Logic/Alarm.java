package Logic;

import java.time.LocalDateTime;
import java.util.Timer;

public class Alarm {
    public int currentAlarmPage=0;
    public AlarmTime[] alarms = new AlarmTime[4];
    public Alarm(Timer m_timer,TimeKeeping timeKeeping) {
        for(int i=0;i<4;i++){
            alarms[i] = new AlarmTime(m_timer,timeKeeping);
        }
    }
    public LocalDateTime loadAlarm() { //16 interation diagram 수정필요
        currentAlarmPage=(currentAlarmPage++) % 4;
        return alarms[currentAlarmPage].loadAlarmData();
    }
    public void saveAlarm(int currentAlarmPage, LocalDateTime data) {
        this.alarms[currentAlarmPage].saveAlarmData(data);
    }
    public void enableAlarm(int currentAlarmPage) {
        this.alarms[currentAlarmPage].enable();
    }
    public void disableAlarm(int currentAlarmPage) {
        this.alarms[currentAlarmPage].disable();
    }
//    public void ring() {
//
//    } Deleted in Alarm

}