package Logic;
import java.time.LocalDateTime;

import java.util.Timer;
import java.util.TimerTask;

public class IntervalTimer extends TimerTask{
    private int iteration;
    private LocalDateTime savedIntervalTimer;
    private Boolean isEnabled;
    private Timer m_timer = new Timer();

    public IntervalTimer() {
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
        m_timer.cancel();
    }
    public void reset() {
        if(!this.isEnabled){
            this.savedIntervalTimer = null;
        }else{
            System.out.println("disable 하고 하셈.");
        }
    }
    public LocalDateTime loadIntervalTimer() {
        return this.savedIntervalTimer;
    }
    public void saveIntervalTimer(LocalDateTime data) {
        this.savedIntervalTimer = data;
    }
    public void ring() {
        System.out.println("beep");
        return ;
    }

    @Override
    public void run(){
        this.iteration++;
        ring();
    }

}