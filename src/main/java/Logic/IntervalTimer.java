package Logic;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

import java.time.ZoneId;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.time.LocalDate;
import java.time.LocalTime;

public class IntervalTimer extends TimerTask{
    private int iteration;
    private LocalDateTime savedIntervalTimer;
    private Boolean isEnabled;
    private LocalDateTime remainedIntervalTimer;
    private Timer m_timer;

    public IntervalTimer(Timer m_timer) {
        this.m_timer = m_timer;
        this.iteration = 0;
        this.isEnabled = false;
        this.savedIntervalTimer = LocalDateTime.of(0,1,1,0,1,0,0);
        this.remainedIntervalTimer = savedIntervalTimer;
        m_timer.schedule(this, 0, 1000);
    }

    public void enable() {
        if(this.remainedIntervalTimer == null)
            this.remainedIntervalTimer = LocalDateTime.of(this.savedIntervalTimer.toLocalDate(), this.savedIntervalTimer.toLocalTime());
        this.isEnabled = true;
    }

    public void disable() {
        this.isEnabled = false;
        saveIntervalTimer(this.remainedIntervalTimer);
    }

    public void reset() {
        LocalTime tmpTime = LocalTime.of(0,0,0);
        LocalDate tmpDate = LocalDate.of(0,1,1);
        LocalDateTime initDateTime = LocalDateTime.of(tmpDate, tmpTime);

        if(!this.isEnabled){
            this.savedIntervalTimer = initDateTime;
            this.remainedIntervalTimer = initDateTime;
        }
    }
    public String loadIntervalTimer() {
        String data,data2;
        SimpleDateFormat format = new SimpleDateFormat("HHmmss");
        data = format.format(LocaltoDate(remainedIntervalTimer));
        data2 = ""+iteration;
        System.out.println(data2+data);
        return data2+data;
    }
    public void saveIntervalTimer(LocalDateTime data) {
        this.savedIntervalTimer = data;
    }
    public void ring() {
        SimpleDateFormat formatTime = new SimpleDateFormat("HHmmss");
        if(this.isEnabled){
            this.remainedIntervalTimer = this.remainedIntervalTimer.minusSeconds(1);
            if(formatTime.format(LocaltoDate(this.remainedIntervalTimer)).equals("000000")){
                this.remainedIntervalTimer = savedIntervalTimer;
                System.out.println("beep");
            }
        }
    }

    @Override
    public void run(){
           ring();
    }
    public Date LocaltoDate(LocalDateTime time){
        return Date.from(time.atZone(ZoneId.systemDefault()).toInstant());
    }

}