package Logic;
public class TimeKeeping {
    public Time currentTime;
    public Boolean displayFormat = true;

    public Time loadTime() {
        return this.currentTime;
    }

    public void saveTime(Time data) {
        // TODO implement here
        this.currentTime = data;
    }

    public void setHourformat() {
        // TODO implement here
        this.displayFormat = !this.displayFormat;
    }
}