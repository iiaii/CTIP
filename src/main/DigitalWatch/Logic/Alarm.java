package Logic;
import java.time.LocalDateTime;

public class Alarm {
    public int currentAlarmPage;
    public AlarmTime[] alarms = new AlarmTime[4];
    public Alarm() {
        for(int i=0;i<4;i++){
            alarms[i] = new AlarmTime();
        }
    }
    public LocalDateTime loadAlarm(int currentAlarmPage) {
        // TODO implement here
        return null;
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
    public void ring() {

    }

}