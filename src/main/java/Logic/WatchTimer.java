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
    private TimeKeeping timeKeeping;
    private Timer m_timer;
    private WatchTimer newTimer;
    public Boolean getActived() {
        return isActived;
    }

    public WatchTimer(Timer m_timer, TimeKeeping timeKeeping){
        this.isActived = false;
        this.m_timer = m_timer;
        this.savedTimer = LocalDateTime.of(timeKeeping.getCurrentTime().toLocalDate(), LocalTime.of(0,0,9));
        this.timeKeeping = timeKeeping;
        this.remainedTimer = this.savedTimer;
        this.m_timer.schedule(this, 0, 1000);
    }
    public WatchTimer(){
        this.isActived = false;
        this.m_timer = m_timer;
        this.savedTimer = LocalDateTime.of(timeKeeping.getCurrentTime().toLocalDate(), LocalTime.of(0,0,9));
        this.timeKeeping = timeKeeping;
        this.remainedTimer = this.savedTimer;
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

    public void setRemainedTimer(LocalDateTime remainedTimer){
        this.remainedTimer = remainedTimer;
    }
    @Override
    public void run() {
        SimpleDateFormat formatTime = new SimpleDateFormat("HHmmss");
        if(this.isActived == true){
            this.remainedTimer = this.remainedTimer.minusSeconds(1);
        }
        if(formatTime.format(LocaltoDate(this.remainedTimer)).equals("000000")){
            this.isActived = false;
        }
    }

    public void activate() {
        this.isActived = true;
        if(this.remainedTimer == null)
            this.remainedTimer = LocalDateTime.of(this.savedTimer.toLocalDate(), this.savedTimer.toLocalTime());
    }

    public void pause() {
//        saveTimer(this.remainedTimer);
        this.isActived = false;
    }

    public void reset() {
        LocalTime tmpTime = LocalTime.of(0,0,0);
        LocalDateTime initDateTime = LocalDateTime.of(timeKeeping.getCurrentTime().toLocalDate(), tmpTime);
        SimpleDateFormat formatTime = new SimpleDateFormat("HHmmss");
        if(!this.isActived && !formatTime.format(LocaltoDate(this.remainedTimer)).equals("000000")){
            this.savedTimer = initDateTime;
            this.remainedTimer = initDateTime;
        }else{
        }
    }

    public LocalDateTime loadTimer(){
        return remainedTimer;
    }

    public void saveTimer(LocalDateTime data) {
        this.savedTimer = data;
    }

    public void ring() {
        SimpleDateFormat formatTime = new SimpleDateFormat("HHmmss");
        if(this.isActived && formatTime.format(LocaltoDate(this.remainedTimer)).equals("000000")) {
            System.out.println("beep");
            this.isActived = false;
        }
    }
    public Date LocaltoDate(LocalDateTime time){
        return Date.from(time.atZone(ZoneId.systemDefault()).toInstant());
    }


}

