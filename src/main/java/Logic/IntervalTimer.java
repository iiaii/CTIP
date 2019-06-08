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


public class IntervalTimer extends TimerTask {
    private int iteration;
    private LocalDateTime savedIntervalTimer;
    private boolean isEnabled;
    private LocalDateTime remainedIntervalTimer;

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
        this.iteration = 0;
        this.isEnabled = false;
        LocalDateTime initDateTime = LocalDateTime.of(LocalDate.now(), LocalTime.of(0, 0, 0));
        this.savedIntervalTimer = initDateTime;
        this.remainedIntervalTimer = savedIntervalTimer;
        m_timer.schedule(this, 0, 1000);
    }

    public boolean getIsEnabled() {
        return this.isEnabled;
    }

    public void enable() {
        SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");

        if (!sdf.format(Date.from(this.savedIntervalTimer.atZone(ZoneId.systemDefault()).toInstant())).equals("000000")) { // 저장된 intervaltimer이 000000이면 동작하지않음 말도안돼 십라
            this.isEnabled = true;
        }
        if (sdf.format(Date.from(this.remainedIntervalTimer.atZone(ZoneId.systemDefault()).toInstant())).equals("000000"))
            this.remainedIntervalTimer = LocalDateTime.of(this.savedIntervalTimer.toLocalDate(), this.savedIntervalTimer.toLocalTime());
    }

    public void disable() {
        this.isEnabled = false;
    }

    public LocalDateTime loadIntervalTimer() {
        return remainedIntervalTimer;
    }

    public void saveIntervalTimer(LocalDateTime data) {
        if (!isEnabled) {
            this.savedIntervalTimer = data;
            this.remainedIntervalTimer = data;
            this.iteration = 0;
        }
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public void ring() {
        DigitalWatch.getInstance().beep();
    }

    @Override
    public void run() {
        SimpleDateFormat formatTime = new SimpleDateFormat("HHmmss");
        if (this.isEnabled) {
            if (formatTime.format(Date.from(this.remainedIntervalTimer.atZone(ZoneId.systemDefault()).toInstant())).equals("000000")) {
                this.remainedIntervalTimer = savedIntervalTimer.minusSeconds(1);
                iteration++;
            } else {
                this.remainedIntervalTimer = this.remainedIntervalTimer.minusSeconds(1);
                if (formatTime.format(Date.from(this.remainedIntervalTimer.atZone(ZoneId.systemDefault()).toInstant())).equals("000000")) { // 깎인 것이 0이면
                    ring();
                }
            }
        }
    }

    public int getIteration() {
        return iteration;
    }

}