package Logic;
import java.time.LocalDate;
import java.util.Timer;
import java.util.TimerTask;
import java.time.temporal.ChronoUnit;

public class Dday {
    private LocalDate startDday;
    private LocalDate endDday;
    private long calDday; //추가 - 계산된 dday, 두 가지 포맷 존재.
    private Boolean displayType;

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

    public long getCalDday() {
        if(this.displayType == true)
            calDday = ChronoUnit.DAYS.between(LocalDate.now(), endDday);
        else {
            long wholeday = ChronoUnit.DAYS.between(startDday, endDday);
            long partday = ChronoUnit.DAYS.between(startDday, LocalDate.now());
            calDday = partday/wholeday*100;
        }
        return calDday;
    }

    public void setCalDday(int calDday) {
        this.calDday = calDday;
    }

    public Boolean getDisplayType() {
        return displayType;
    }

    public void saveDday(LocalDate startDday, LocalDate endDday) {
        this.startDday = startDday;
        this.endDday = endDday;
    }

    public void reset() {
        startDday = LocalDate.now();
        endDday = LocalDate.now();
    }

    public Boolean changeFormat() { //true면 d-day, false면 %
        if(this.displayType) {
            return false;
        }
        else {
            return true;
        }
    }

    public void ring(LocalDate currentTime) {
        // TODO implement here

        return ;
    }
}