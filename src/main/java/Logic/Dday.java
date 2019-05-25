package Logic;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.TimerTask;
import java.util.Timer;

public class Dday extends TimerTask{
    private LocalDateTime startDday;
    private LocalDateTime endDday;
    private LocalDateTime currentDay; //추가 - 하루 지날 때마다 현재 날짜 TimeKeeping에서 정보 입력 해줘야할듯
    private double calDday; //추가 - 계산된 dday, 두 가지 포맷 존재.
    private Boolean displayType = true;
    /*
    public String loadStartDday() {
        String data, data2;
        SimpleDateFormat format = new SimpleDateFormat("yyMMdd");
        data = format.format(LocaltoDate(startDday));
        if(displayType==true)
            data2 = "d-"+(int)getCalDday()%10000;
        else
            data2 = (Math.round(100*getCalDday())/100)+"PE";
        return data+data2;
    }
     */
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
        return currentDay;
    }

    public void setCurrentDay(LocalDateTime currentDay) {
        this.currentDay = currentDay;
    }

    public void run() {
        this.currentDay = this.currentDay.plusDays(1);
        this.ring(this.currentDay);
        if(this.currentDay == this.endDday) {
//            cancel();
        }
    }

    public double getCalDday() {
        if(this.displayType) { //dday
            this.calDday = ChronoUnit.DAYS.between(currentDay, this.endDday);
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
        this.startDday = startDday;
        this.endDday = endDday;
    }

    public void reset() {
        startDday = LocalDateTime.now();
        endDday = LocalDateTime.now();
        cancel();
    }

    public void changeFormat() { //true면 d-day, false면 %
        this.displayType = !this.displayType;
    }

    public void ring(LocalDateTime currentTime) {
        // TODO implement here
        if(this.currentDay == this.endDday) {
            System.out.println("ring ring ring");
        }
        return ;
    }
    public Date LocaltoDate(LocalDateTime time){
        return Date.from(time.atZone(ZoneId.systemDefault()).toInstant());
    }
}