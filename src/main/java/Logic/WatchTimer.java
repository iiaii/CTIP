package Logic;

import GUI.DigitalWatch;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class WatchTimer extends TimerTask {
    private LocalDateTime savedTimer;
    private LocalDateTime remainedTimer;
    private boolean isActived;

    public boolean getActived() {
        return isActived;
    }

    public WatchTimer(Timer m_timer) {
        this.isActived = false;
        this.savedTimer = LocalDateTime.of(LocalDate.now(), LocalTime.of(0, 0, 0));
        this.remainedTimer = this.savedTimer;
        m_timer.schedule(this, 0, 1000);
    }

    public LocalDateTime getSavedTimer(){
        return this.savedTimer;
    }

    public LocalDateTime getRemainedTimer() {
        return this.remainedTimer;
    }

    public void setRemainedTimer(LocalDateTime remainedTimer) {
        this.remainedTimer = remainedTimer;
    }
    public void setActived(boolean actived) {
        isActived = actived;
    }


    @Override
    public void run() {
        SimpleDateFormat formatTime = new SimpleDateFormat("HHmmss");
        if (this.isActived == true) {
            if (formatTime.format(Date.from(this.remainedTimer.atZone(ZoneId.systemDefault()).toInstant())).equals("000000") == true) {
                this.isActived = false;
                this.remainedTimer = LocalDateTime.of(this.savedTimer.toLocalDate(), this.savedTimer.toLocalTime());
            } else {

                this.remainedTimer = this.remainedTimer.minusSeconds(1);
                if (formatTime.format(Date.from(this.remainedTimer.atZone(ZoneId.systemDefault()).toInstant())).equals("000000") == true) {
                    ring();
                }
            }
        }
    }

    public void activate() {
        SimpleDateFormat formatTime = new SimpleDateFormat("HHmmss");
        this.isActived = !formatTime.format(Date.from(this.remainedTimer.atZone(ZoneId.systemDefault()).toInstant())).equals("000000");
    }

    public void pause() {
        this.isActived = false;
    }

    public void reset() {
        LocalTime tmpTime = LocalTime.of(0, 0, 0);
        LocalDateTime initDateTime = LocalDateTime.of(LocalDate.now(), tmpTime);
        if(!this.isActived){
            this.savedTimer = initDateTime;
            this.remainedTimer = initDateTime;
        }
    }

    public LocalDateTime loadTimer() {
        return remainedTimer;
    }

    public void saveTimer(LocalDateTime data) {
        this.savedTimer = data;
        this.remainedTimer = savedTimer;
        this.isActived = false;
    }

    public void ring() {
        DigitalWatch.getInstance().beep();
    }


}

