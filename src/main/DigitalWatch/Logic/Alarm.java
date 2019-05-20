package Logic;
public class Alarm {

    /**
     * Default constructor
     */
    public Alarm() {
    }

    /**
     * 
     */
    public int currentAlarmPage;

    /**
     * 
     */
    public AlarmTime[] alarms = new AlarmTime[4];





    /**
     * @param currentAlarmPage 
     * @return
     */
    public Time loadAlarm(int currentAlarmPage) {
        // TODO implement here
        return null;
    }

    /**
     * @param currentAlarmPage 
     * @param data 
     * @return
     */
    public void saveAlarm(int currentAlarmPage, Time data) {
        // TODO implement here
        return ;
    }

    /**
     * @param currentAlarmPage 
     * @return
     */
    public void enableAlarm(int currentAlarmPage) {
        // TODO implement here
        return ;
    }

    /**
     * @param currentAlarmPage 
     * @return
     */
    public void disableAlarm(int currentAlarmPage) {
        // TODO implement here
        return ;
    }

    /**
     * @return
     */
    public void Notify() {
        // TODO implement here
        return ;
    }

}