package Logic;

import GUI.DigitalWatch;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Timer;
import java.util.TimerTask;

public class Dday extends TimerTask {
    private boolean existStartDday = false;
    private boolean existEndDday = false;
    private LocalDateTime startDday;
    private LocalDateTime endDday;
    private LocalDateTime currentDay;
    private double calDday;

    public boolean getExistStartDday() {
        return existStartDday;
    }

    private boolean displayType;
    private TimeKeeping tm;

    public void setExistStartDday(boolean existStartDday) {
        this.existStartDday = existStartDday;
    }

    public boolean getExistEndDday() {
        return existEndDday;
    }

    public Dday(TimeKeeping tm, Timer m_timer) {
        this.tm = tm;
        currentDay = tm.getCurrentTime();
        startDday = LocalDateTime.of(currentDay.toLocalDate(), LocalTime.of(0,0,0));;
        endDday = LocalDateTime.of(currentDay.toLocalDate(), LocalTime.of(0,0,0));;
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
        this.startDday = LocalDateTime.of(startDday.toLocalDate(), LocalTime.of(0,0,0));;
    }

    public LocalDateTime loadEndDday() {
        return endDday;
    }

    public void setEndDday(LocalDateTime endDday) {
        this.endDday = LocalDateTime.of(endDday.toLocalDate(), LocalTime.of(0,0,0));;
    }

    public void run() {
        currentDay = tm.getCurrentTime();
        // endDay설정되어있을때만 조건맞을때 실행
        if (existEndDday && (currentDay.getYear() == endDday.getYear()) && (currentDay.getDayOfYear() == endDday.getDayOfYear())) {
            existEndDday = false; // 한번만 울려주게 하기 위해서 설정함
            ring();
        }
    }

    public void setCalDday(int calDday) {
        this.calDday = calDday;
    }

    public boolean getDisplayType() {
        return displayType;
    }

    public void setDisplayType(boolean displayType) {
        this.displayType = displayType;
    }

    public void saveDday(LocalDateTime startDday, LocalDateTime endDday) {
        if (startDday != null) this.startDday = LocalDateTime.of(startDday.toLocalDate(), LocalTime.of(0,0,0));
        this.existStartDday = (startDday != null);
        this.endDday = LocalDateTime.of(endDday.toLocalDate(), LocalTime.of(0,0,0));;
        this.existEndDday = true;
    }

    public void setCurrentDay(LocalDateTime currentDay) {
        this.currentDay = LocalDateTime.of(currentDay.toLocalDate(), LocalTime.of(0,0,0));;
    }

    public void reset() {
        existStartDday = false;
        existEndDday = false;
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

    public double getCalDday() {
        currentDay = LocalDateTime.of(tm.getCurrentTime().toLocalDate(), LocalTime.of(0,0,0));
        long diffDays = ChronoUnit.DAYS.between(this.startDday, this.endDday);
        long passedDays = ChronoUnit.DAYS.between(startDday, currentDay);
        long remainedDays = ChronoUnit.DAYS.between(currentDay, endDday);

        if (this.displayType) { // D-day
            if(remainedDays < 0) return 0; // D+xx 없으니까 D-0에 고정
            this.calDday = remainedDays;
        } else {
            if(diffDays == 0) { //시작날짜 종료날짜가 같고
                if(passedDays >= 0) // 오늘 날짜가 시작날짜 지났으면 100%
                    return 100;
                else // passedDays < 0 아직 안왔으면 0%
                    return 0;
            } else if(diffDays < 0){
                return -1;
            }
            else{
                if(passedDays < 0) return 0; // 아직 시작날짜가 안됐을 때는 0%
                this.calDday = ((double) passedDays/ (double)diffDays) * 100;
            }
        }
        return calDday;
    }
}
