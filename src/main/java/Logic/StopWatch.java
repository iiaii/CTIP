package Logic;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.util.TimerTask;
import java.util.Timer;

public class StopWatch extends TimerTask{
    private LocalTime currentStopwatch;
    private Boolean isActivated = false;
    private Timer m_timer;
    private int countDay=0;

    public StopWatch (Timer m_timer){
        this.m_timer = m_timer;
        currentStopwatch = LocalTime.of(23,59,58);
        //currentStopwatch = LocalTime.of(0,0,0);
        //example
    }
    @Override
    public void run() {
        this.currentStopwatch = this.currentStopwatch.plusSeconds(1);
        if(this.currentStopwatch.equals(LocalTime.MAX.plusNanos(1))) {
            countDay+=1;
            System.out.println(countDay);
            //pause();
        }
    }

    public Boolean getActivated() {
        return isActivated;
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
        //SimpleDateFormat formatTime = new SimpleDateFormat("HHmmss");
        if(!this.isActivated) {
            currentStopwatch = LocalTime.of(0,0,0);
            this.isActivated = false;
        }
        else
            System.out.println("비활성화 되지 않습니다.");
    }
    public String LoadStopWatch() {
        String data;
        SimpleDateFormat format = new SimpleDateFormat("HHmmss");
        data = format.format(Date.from(currentStopwatch.atDate(LocalDate.now()).atZone((ZoneId.systemDefault())).toInstant()));
        return "dzzzzzz"+countDay+data;
    }
}