package Logic;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

import java.time.ZoneId;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.time.LocalDate;
import java.time.LocalTime;

import  sun.audio.*;    //import the sun.audio package
import  java.io.*;


public class IntervalTimer extends TimerTask{
    private int iteration;
    private LocalDateTime savedIntervalTimer;
    private Boolean isEnabled;
    private LocalDateTime remainedIntervalTimer;
    private Timer m_timer;

    public LocalDateTime getSavedIntervalTimer() {
        return savedIntervalTimer;
    }

    public void setSavedIntervalTimer(LocalDateTime savedIntervalTimer) {
        this.savedIntervalTimer = savedIntervalTimer;
    }

    public LocalDateTime getRemainedIntervalTimer() {
        return remainedIntervalTimer;
    }

    public void setRemainedIntervalTimer(LocalDateTime remainedIntervalTimer) {
        this.remainedIntervalTimer = remainedIntervalTimer;
    }

    public IntervalTimer(Timer m_timer) {
        this.m_timer = m_timer;
        this.iteration = 0;
        this.isEnabled = false;
        this.savedIntervalTimer = LocalDateTime.of(2019,1,1,0,0,2,0);
        this.remainedIntervalTimer = savedIntervalTimer;
        m_timer.schedule(this, 0, 1000);
    }
    public Boolean getIsEnabled(){
        return this.isEnabled;
    }
    public void enable() {
        if(this.remainedIntervalTimer == null)
            this.remainedIntervalTimer = LocalDateTime.of(this.savedIntervalTimer.toLocalDate(), this.savedIntervalTimer.toLocalTime());
        this.isEnabled = true;
    }

    public void disable() {
        this.isEnabled = false;
//        saveIntervalTimer(this.remainedIntervalTimer);
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
    public LocalDateTime loadIntervalTimer(){
        return remainedIntervalTimer;
    }
    public void saveIntervalTimer(LocalDateTime data) {
        this.savedIntervalTimer = data;
    }

    public Boolean getEnabled() {
        return isEnabled;
    }

    public void setEnabled(Boolean enabled) {
        isEnabled = enabled;
    }

    public void ring() {
        SimpleDateFormat formatTime = new SimpleDateFormat("HHmmss");
        if(this.isEnabled){
            this.remainedIntervalTimer = this.remainedIntervalTimer.minusSeconds(1);
            if(formatTime.format(LocaltoDate(this.remainedIntervalTimer)).equals("235959")){
                this.remainedIntervalTimer = savedIntervalTimer;
                this.iteration+=1;
                InputStream in = null;
                try {
                    in = new FileInputStream("beep-4.wav");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                AudioStream as = null;
                try {
                    as = new AudioStream(in);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                // Use the static class member "player" from class AudioPlayer to play
                // clip.
                AudioPlayer.player.start(as);

                // Similarly, to stop the audio.
                AudioPlayer.player.stop(as);
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
    public int getIteration(){
        return iteration;
    }

}