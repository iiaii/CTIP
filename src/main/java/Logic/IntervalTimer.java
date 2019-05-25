package Logic;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

import java.time.ZoneId;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class IntervalTimer extends TimerTask{
    private int iteration;
    private LocalDateTime savedIntervalTimer;
    private Boolean isEnabled;
    private Timer m_timer;

    public IntervalTimer(Timer m_timer) {
        this.m_timer = m_timer;
        this.iteration = 0;
        this.isEnabled = false;
    }
    public void enable() {
        this.isEnabled = true;
        int tmpHour = savedIntervalTimer.getHour() * 3600;
        int minutes = savedIntervalTimer.getMinute() * 60;
        int sec = savedIntervalTimer.getSecond();
        int scheduleTime = tmpHour + minutes + sec;
        m_timer.schedule(this, scheduleTime*1000 , scheduleTime*1000);
    }
    public void disable() {
        this.isEnabled = false;
        cancel();
    }
    public void reset() {
        if(!this.isEnabled){
            this.savedIntervalTimer = null;
        }else{
            System.out.println("disable 하고 하셈.");
        }
    }/*
    public String loadIntervalTimer() {
        String data,data2;
        SimpleDateFormat format = new SimpleDateFormat("HHmmss");
        data = format.format(LocaltoDate(savedIntervalTimer));
        data2 = ""+iteration;
        return data2+data;
    }
    */
    public LocalDateTime loadIntervalTimer(){
        return savedIntervalTimer;
    }
    public void saveIntervalTimer(LocalDateTime data) {
        this.savedIntervalTimer = data;
    }
    public void ring() {
        System.out.println("beep");
    }

    @Override
    public void run(){
        this.iteration++;
        ring();
    }
    public Date LocaltoDate(LocalDateTime time){
        return Date.from(time.atZone(ZoneId.systemDefault()).toInstant());
    }
    public int getIteration(){
        return iteration;
    }

}