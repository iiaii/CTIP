package Logic;

public class AlarmTime {
    public Time currentAlarm;
    public Boolean isEnabled;

    public AlarmTime() {
    }

    public Time loadAlarmData() {
        return this.currentAlarm;
    }
    public void saveAlarmData(Time data) {
        this.currentAlarm = data;
    }
    public void enable() {
        this.isEnabled = true;
    }
    public void disable() {
        this.isEnabled = false;
    }

    public Time getCurrentAlarm() {
        return currentAlarm;
    }

    public void setCurrentAlarm(Time currentAlarm) {
        this.currentAlarm = currentAlarm;
    }

    public Boolean getEnabled() {
        return this.isEnabled;
    }

    public void setEnabled(Boolean enabled) {
        this.isEnabled = enabled;
    }
}