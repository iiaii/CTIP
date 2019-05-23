package Logic;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Dday {
    private LocalDateTime startDday;
    private LocalDateTime endDday;
    private double calDday; //추가 - 계산된 dday, 두 가지 포맷 존재.
    private Boolean displayType = true;

    public LocalDateTime loadStartDday() {
        return this.startDday;
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

    public double getCalDday() {
        if(this.displayType) { //dday
            this.calDday = ChronoUnit.DAYS.between(LocalDate.now(), this.endDday);
        }
        else { //%
            this.calDday = (double)(ChronoUnit.DAYS.between(this.startDday, LocalDate.now())) / (double)(ChronoUnit.DAYS.between(this.startDday, this.endDday)) * 100;
            // 여휴 자리 설정 필요
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
    }

    public void changeFormat() { //true면 d-day, false면 %
        this.displayType = !this.displayType;
    }

    public void ring(LocalDate currentTime) {
        // TODO implement here
        return ;
    }
}