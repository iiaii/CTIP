package Logic;

import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;
import java.time.LocalDateTime;

public class WatchTimer extends TimerTask {
    private LocalDateTime savedTimer;
    private LocalDateTime remainedTimer;
    private Boolean isActived;
    private Timer m_timer = new Timer();

    public WatchTimer(LocalDateTime savedTimer){
        this.savedTimer = savedTimer;
    }

    @Override
    public void run() {
        this.savedTimer = this.savedTimer.minusSeconds(1);
    }

    public void activate() {
        this.isActived = true;
        m_timer.schedule(this, 0, 1000);
    }

    public void pause() {
        this.isActived = false;
        m_timer.cancel();
    }

    public void reset() {
        SimpleDateFormat formatTime = new SimpleDateFormat("HHmmss");

        if(!this.isActived && formatTime.format(this.remainedTimer).equals("000000")){
//            this.remainedTimer.getHour() = 0;
//            this.savedTimer.;
        }else{
            System.out.println("비활성화 안돼.");
        }
    }

    public LocalDateTime loadTimer() {
        return this.savedTimer;
    }

    public void saveTimer(LocalDateTime data) {
        this.savedTimer = data;
    }

    public void ring() {

        return;
    }


}

