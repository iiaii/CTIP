package Logic;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Timer;
import java.util.TimerTask;
import java.time.LocalDateTime;
import java.util.Date;

public class WatchTimer extends TimerTask {
    private LocalDateTime savedTimer;
    private LocalDateTime remainedTimer;
    private Boolean isActived;
    private Timer m_timer = new Timer();

    public WatchTimer(LocalDateTime savedTimer){
        this.savedTimer = savedTimer;
    }
    public WatchTimer(){
    }
    public LocalDateTime getSavedTimer(){
        return this.savedTimer;
    }
    public LocalDateTime getRemainedTimer(){
        return this.remainedTimer;
    }

    public void setSavedTimer(LocalDateTime savedTimer){
        this.savedTimer = savedTimer;
    }
    @Override
    public void run() {
        remainedTimer = remainedTimer.minusSeconds(1);
//        ring(); //추가
        if(remainedTimer.getSecond()== 0){
            cancel();
        }
    }

    public void activate() {
        this.isActived = true;
//        savedTimer.minusSeconds(1);
        if(this.remainedTimer == null)
            this.remainedTimer = LocalDateTime.of(this.savedTimer.toLocalDate(), this.savedTimer.toLocalTime());
        m_timer.schedule(this, 0, 1000);
    }

    public void pause() {
        this.isActived = false;
        cancel();
        saveTimer(this.remainedTimer);
    }

    public void reset() {
        SimpleDateFormat formatTime = new SimpleDateFormat("HHmmss");
        if(!this.isActived && !formatTime.format(this.remainedTimer).equals("000000")){
            this.savedTimer = null;
            this.remainedTimer = null;
            System.out.println("111");
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
        SimpleDateFormat formatTime = new SimpleDateFormat("HHmmss");
        if(this.isActived && formatTime.format(this.remainedTimer).equals("000000")) {
            System.out.println("beep");
        }
    }


}

