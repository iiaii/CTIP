package Logic;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.TimerTask;
import java.util.Timer;

public class Dday extends TimerTask{
    private LocalDate startDday;
    private LocalDate endDday;
    private LocalDate currentDay; //추가 - 현재 날짜 TimeKeeping에서 정보 입력 해줘야할듯
    private double calDday; //추가 - 계산된 dday, 두 가지 포맷 존재.
    private Boolean displayType = true;

    public LocalDate loadStartDday() {
        return this.startDday;
    }

    public void setStartDday(LocalDate startDday) {
        this.startDday = startDday;
    }

    public LocalDate loadEndDday() {
        return endDday;
    }

    public void setEndDday(LocalDate endDday) {
        this.endDday = endDday;
    }

    public LocalDate getCurrentDay() {
        return currentDay;
    }

    public void setCurrentDay(LocalDate currentDay) {
        this.currentDay = currentDay;
    }

    public void run() {
        this.currentDay = this.currentDay.plusDays(1);
        if(this.currentDay == this.endDday) {
            cancel();
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

    public void saveDday(LocalDate startDday, LocalDate endDday) {
        this.startDday = startDday;
        this.endDday = endDday;
    }

    public void reset() {
        startDday = LocalDate.now();
        endDday = LocalDate.now();
    }

    public void changeFormat() { //true면 d-day, false면 %
        this.displayType = !this.displayType;
    }

    public void ring(LocalDate currentTime) {
        // TODO implement here
        if(this.currentDay == this.endDday) {
            System.out.println("ring ring ring");
        }
        return ;
    }
}