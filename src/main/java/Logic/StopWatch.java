package Logic;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.TimerTask;
import java.util.Timer;

public class StopWatch extends TimerTask{
    private LocalTime currentStopwatch;
    private Boolean isActivated;
    private Timer m_timer;

    public StopWatch (Timer m_timer){
        this.m_timer = m_timer;
        currentStopwatch = LocalTime.of(0,0,0);
    }
    @Override
    public void run() {
        this.currentStopwatch = this.currentStopwatch.plusSeconds(1);
        if(this.currentStopwatch.equals(LocalTime.MAX)) {
            pause();
        }
    }

    public void activate() {
        this.isActivated = true;
        m_timer.schedule(this, 0, 1000);
    }
    public void pause() {
        this.isActivated = false;
        cancel();
    }
    public void reset() {
        SimpleDateFormat formatTime = new SimpleDateFormat("HHmmss");
        if(!this.isActivated) {
            currentStopwatch = LocalTime.of(0,0,0);
            this.isActivated = false;
        }
        else
            System.out.println("비활성화 되지 않습니다.");
    }
}