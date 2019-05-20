package Logic;
public class Timer {
    public Time savedTimer;
    public Time remainedTimer;
    public Boolean isActived;

    public void activate() {
        this.isActived = true;
    }

    public void pause() {
        this.isActived = false;
    }

    public void reset() {
        this.savedTimer = null;
        this.remainedTimer = null;
        this.isActived = false;
    }

    public Time loadTimer() {
        return this.savedTimer;
    }

    public void saveTimer(Time data) {
        this.savedTimer = data;
    }

    public void ring() {

        return;
    }

}