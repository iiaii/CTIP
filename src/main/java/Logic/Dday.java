package Logic;

import GUI.DigitalWatch;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Timer;
import java.util.TimerTask;

public class Dday extends TimerTask {
    private Boolean existStartDday = false;
    private Boolean existEndDday = false;
    private LocalDateTime startDday;
    private LocalDateTime endDday;
    private LocalDateTime currentDay;
    private Timer m_timer;
    private double calDday;

    public Boolean getExistStartDday() {
        return existStartDday;
    }

    private Boolean displayType;
    private TimeKeeping tm;

    public void setExistStartDday(Boolean existStartDday) {
        this.existStartDday = existStartDday;
    }

    public Boolean getExistEndDday() {
        return existEndDday;
    }

    public void setExistEndDday(Boolean existEndDday) {
        this.existEndDday = existEndDday;
    }

    public void setCalDday(double calDday) {
        this.calDday = calDday;
    }

    public Dday(TimeKeeping tm, Timer m_timer) {
        this.m_timer = m_timer;
        this.tm = tm;
        currentDay = tm.getCurrentTime();
        startDday = currentDay;
        endDday = currentDay;
        m_timer.schedule(this, 0, 1000);
        this.displayType = true;
    }

    public LocalDateTime getStartDday() {
        return startDday;
    }

    public LocalDateTime getEndDday() {
        return endDday;
    }

    public LocalDateTime loadStartDday() {
        return startDday;
    }

    public void setStartDday(LocalDateTime startDday) {
        this.startDday = startDday;
    }

    public LocalDateTime loadEndDday() {
        return endDday;
    }

    public void setEndDday(LocalDateTime endDday) {
        this.endDday = endDday;
    }

    public LocalDateTime getCurrentDay() {
        return this.currentDay;
    }

    public void run() {
        currentDay = tm.getCurrentTime();
        // endDay설정되어있을때만 조건맞을때 실행
        if (existEndDday && (currentDay.getYear() == endDday.getYear()) && (currentDay.getDayOfYear() == endDday.getDayOfYear())) {
            existEndDday = false; // 한번만 울려주게 하기 위해서 설정함
            ring();
        }
    }

    public double getCalDday() {
        currentDay = tm.getCurrentTime();
        if (this.displayType) {
            this.calDday = ChronoUnit.DAYS.between(currentDay, this.endDday) + ((currentDay.getYear() == endDday.getYear() && currentDay.getDayOfYear() == endDday.getDayOfYear()) == true ? 0 : 1);
        } else {
            this.calDday = (double) (ChronoUnit.DAYS.between(this.startDday, currentDay)) / (double) (ChronoUnit.DAYS.between(this.startDday, this.endDday)) * 100;

            if (Double.isNaN(this.calDday)) {
                // 0 나누기 0 이 발생함
                // startDday, endDday, currentDay 똑같을 때 발생
                if (ChronoUnit.DAYS.between(this.startDday, currentDay) == ChronoUnit.DAYS.between(this.startDday, this.endDday))
                    return 100;
            }
        }
        return calDday;
    }

    public void setCalDday(int calDday) {
        this.calDday = calDday;
    }

    public Boolean getDisplayType() {
        return displayType;
    }

    public void setDisplayType(Boolean displayType) {
        this.displayType = displayType;
    }

    public void saveDday(LocalDateTime startDday, LocalDateTime endDday) {
        if (startDday != null) this.startDday = startDday;
        this.existStartDday = (startDday != null);
        this.endDday = endDday;
        this.existEndDday = true;
    }

    public void setCurrentDay(LocalDateTime currentDay) {
        this.currentDay = currentDay;
    }

    public void reset() {
        existStartDday = false;
        startDday = this.currentDay;
        endDday = this.currentDay;
        this.existEndDday = false;
        this.displayType = true;
    }

    public void changeFormat() {
        if (this.existStartDday == false) {
            this.displayType = true;
        } else {
            this.displayType = !this.displayType;
        }
    }

    public void ring() {
        DigitalWatch.getInstance().beep();
    }
}