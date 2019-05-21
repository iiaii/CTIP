package Logic;
import java.time.LocalDateTime;

public class AlarmTime {
    public LocalDateTime currentAlarm;
    public Boolean isEnabled;

    public AlarmTime() {
    }

    public LocalDateTime loadAlarmData() {
        return this.currentAlarm;
    }
    public void saveAlarmData(LocalDateTime data) {
        this.currentAlarm = data;
    }
    public void enable() {
        this.isEnabled = true;
    }
    public void disable() {
        this.isEnabled = false;
    }

    public LocalDateTime getCurrentAlarm() {
        return currentAlarm;
    }

    public void setCurrentAlarm(LocalDateTime currentAlarm) {
        this.currentAlarm = currentAlarm;
    }

    public Boolean getEnabled() {
        return this.isEnabled;
    }

    public void setEnabled(Boolean enabled) {
        this.isEnabled = enabled;
    }
}