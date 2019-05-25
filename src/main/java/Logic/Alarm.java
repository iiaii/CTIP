package Logic;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

import java.time.ZoneId;
import java.util.Date;
import java.util.Timer;

public class Alarm {
    //public int currentAlarmPage=0;
    public AlarmTime[] alarms = new AlarmTime[4];
    public Alarm(Timer m_timer,TimeKeeping timeKeeping) {
        for(int i=0;i<4;i++){
            alarms[i] = new AlarmTime(m_timer,timeKeeping);
        }
    }
    /*
    public String loadAlarm(int currentAlarmPage) { //16 interation diagram 수정필요
        //currentAlarmPage = (currentAlarmPage++) % 4;
        String data, data2;
        SimpleDateFormat format = new SimpleDateFormat("HHmmss");
        data = format.format(LocaltoDate(alarms[currentAlarmPage].loadAlarmData()));
        if(alarms[currentAlarmPage].isEnabled == true)
            data2 = "ENzzzzz"+currentAlarmPage;
        else
            data2 = "zzzzzzz"+currentAlarmPage;
        return data2+data;
    }
     */
    public LocalDateTime loadAlarm(int currentAlarmPage){
        return alarms[currentAlarmPage].loadAlarmData();
    }
    public void saveAlarm(int currentAlarmPage, LocalDateTime data) { // Interaction Diagram 수정필요
        this.alarms[currentAlarmPage].saveAlarmData(data);
    }
    public void enableAlarm(int currentAlarmPage) {
        this.alarms[currentAlarmPage].enable();
    }
    public void disableAlarm(int currentAlarmPage) {
        this.alarms[currentAlarmPage].disable();
    }
    public Date LocaltoDate(LocalDateTime time){
        return Date.from(time.atZone(ZoneId.systemDefault()).toInstant());
    }
    public AlarmTime getAlarmTime(int currentAlarmpage){
        return alarms[currentAlarmpage];
    }
//    public void ring() {
//
//    } Deleted in Alarm

}