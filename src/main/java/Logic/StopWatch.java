package Logic;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.TimerTask;
import java.util.Timer;

public class StopWatch extends TimerTask{
    private LocalTime currentStopwatch;
    private Boolean isActivated = false;
    private Timer m_timer;
    private int countDay=0;
    private StopWatch tempTimer;

    public StopWatch (Timer m_timer){
        this.m_timer = m_timer;
        currentStopwatch = LocalTime.of(0,0,0);
        m_timer.schedule(this, 0, 1000);
    }
    @Override
    public void run() {
        if(this.isActivated == true){
            this.currentStopwatch = this.currentStopwatch.plusSeconds(1);
            if(this.currentStopwatch.equals(LocalTime.MAX.plusNanos(1))) {
                countDay+=1;
                System.out.println(countDay);
                //pause();
            }
        }
    }

    public LocalTime getCurrentStopwatch() {
        return currentStopwatch;
    }

    public void setCurrentStopwatch(LocalTime currentStopwatch) {
        this.currentStopwatch = currentStopwatch;
    }

    public Boolean getActivated() {
        return isActivated;
    }

    public void activate() {
        this.isActivated = true;

    }
    public void pause() {
        this.isActivated = false;
    }
    public void reset() {
        if(!this.isActivated) {
            currentStopwatch = LocalTime.of(0,0,0);
            this.isActivated = false;
            this.countDay = 0;
        }
        else
            System.out.println("비활성화 되지 않습니다.");
    }
    public LocalTime loadStopWatch(){
        return currentStopwatch;
    }

    public int getCountDay() {
        return countDay;
    }
}