package Logic;

import java.util.Timer;
import java.util.TimerTask;

public class WatchTimer extends TimerTask {
    public Time savedTimer;
    public Time remainedTimer;
    public Boolean isActived;
    private Timer m_timer;


    public WatchTimer(Timer m_timer){
        this.m_timer = m_timer;
    }
    public WatchTimer(){

    }
    public void activate() {
        this.isActived = true;
        m_timer.schedule(this, 0,2000);
    }

    public void pause() {
        this.isActived = false;
    }

    public void reset() {
        this.savedTimer = null;
        this.remainedTimer = null;
        this.isActived = false;
    }

    public Time loadTimer() {
        return this.savedTimer;
    }

    public void saveTimer(Time data) {
        this.savedTimer = data;
    }

    public void ring() {

        return;
    }
    @Override
    public void run(){
        System.out.println("WatchTimer!!!\n");
    }

}