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
    private Timer m_timer;
    private WatchTimer newTimer;
    public Boolean getActived() {
        return isActived;
    }

    public WatchTimer(Timer m_timer){
        this.isActived = false;
        this.m_timer = m_timer;
        this.savedTimer = LocalDateTime.of(LocalDate.now(), LocalTime.of(0,0,0));
        this.remainedTimer = this.savedTimer;
        this.m_timer.schedule(this, 0, 1000);
    }
    public WatchTimer(){
        this.isActived = false;
        this.m_timer = m_timer;
        this.savedTimer = LocalDateTime.of(LocalDate.now(), LocalTime.of(0,0,0));
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
            if(formatTime.format(LocaltoDate(this.remainedTimer)).equals("000000") == true){
                this.isActived = false;
                this.remainedTimer = LocalDateTime.of(this.savedTimer.toLocalDate(), this.savedTimer.toLocalTime());
                ring();
            } else {

                this.remainedTimer = this.remainedTimer.minusSeconds(1);
            }
        }
    }

    public void activate() {
        SimpleDateFormat formatTime = new SimpleDateFormat("HHmmss");
        if(formatTime.format(LocaltoDate(this.remainedTimer)).equals("000000") == false)
            this.isActived = true;
    }

    public void pause() {
//        saveTimer(this.remainedTimer);
        this.isActived = false;
    }

    public void reset() {
        LocalTime tmpTime = LocalTime.of(0,0,0);
        LocalDateTime initDateTime = LocalDateTime.of(LocalDate.now(), tmpTime);
        SimpleDateFormat formatTime = new SimpleDateFormat("HHmmss");
        if(!this.isActived){
            this.savedTimer = initDateTime;
            this.remainedTimer = initDateTime;
        }
    }

    public LocalDateTime loadTimer(){
        return remainedTimer;
    }

    public void saveTimer(LocalDateTime data) {
        this.savedTimer = data;
        this.remainedTimer = savedTimer;
        this.isActived = false;
    }

    public void ring() {
        System.out.println("beep");
    }
    public Date LocaltoDate(LocalDateTime time){
        return Date.from(time.atZone(ZoneId.systemDefault()).toInstant());
    }


}

