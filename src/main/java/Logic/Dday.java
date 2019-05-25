package Logic;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.TimerTask;
import java.util.Timer;

public class Dday extends TimerTask{
    private Boolean existStartDday = false;
    private LocalDateTime startDday;
    private LocalDateTime endDday;
    private LocalDateTime currentDay; //추가 - 하루 지날 때마다 현재 날짜 TimeKeeping에서 정보 입력 해줘야할듯
    private Timer m_timer;
    private double calDday; //추가 - 계산된 dday, 두 가지 포맷 존재.

    public Boolean getExistStartDday() {
        return existStartDday;
    }

    private Boolean displayType;
    private TimeKeeping tm;

    public Dday(TimeKeeping tm, Timer m_timer) {
        this.m_timer = m_timer;
        this.tm = tm;
        currentDay = tm.getCurrentTime();
        startDday = currentDay; // 초기화는 currentdDay로 하고 increasedata없으면 null 넣어주자
        endDday = currentDay; //예시
        m_timer.schedule(this, 0, 1000);
        this.displayType = true;
    }

    public LocalDateTime loadStartDday(){
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
        if(currentDay.getYear() == endDday.getYear() && currentDay.getDayOfYear() == endDday.getDayOfYear()) {
            cancel();
        }
    }

    public double getCalDday() {
        currentDay = tm.getCurrentTime();
        if(this.displayType) { //dday
            this.calDday = ChronoUnit.DAYS.between(currentDay, this.endDday) + ((currentDay.getYear() == endDday.getYear() && currentDay.getDayOfYear() == endDday.getDayOfYear()) == true ? 0 :1);
        }
        else { //%
            this.calDday = (double)(ChronoUnit.DAYS.between(this.startDday, currentDay)) / (double)(ChronoUnit.DAYS.between(this.startDday, this.endDday)) * 100;
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
        if(startDday != null) this.startDday = startDday;
        this.existStartDday = (startDday != null);
        this.endDday = endDday;
    }

    public void reset() {
        existStartDday = false;
        startDday = this.currentDay;
        endDday = this.currentDay;
        cancel();
    }

    public void changeFormat() { //true면 d-day, false면 %
        if(this.existStartDday == false) {
            this.displayType = true;
        } else {
            this.displayType = !this.displayType;
        }
    }

    public Date LocaltoDate(LocalDateTime time){
        return Date.from(time.atZone(ZoneId.systemDefault()).toInstant());
    }
}