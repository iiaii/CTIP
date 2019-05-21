package Logic;
public class Dday {
    private Time startDday;
    private Time endDday;
    private int calDday; //추가 - 계산된 dday, 두 가지 포맷 존재.
    private Boolean displayType;

    public Time getStartDday() {
        return this.startDday;
    }

    public void setStartDday(Time startDday) {
        this.startDday = startDday;
    }

    public Time getEndDday() {
        return endDday;
    }

    public void setEndDday(Time endDday) {
        this.endDday = endDday;
    }

    public int getCalDday() {
        return calDday;
    }

    public void setCalDday(int calDday) {
        this.calDday = calDday;
    }

    public Boolean getDisplayType() {
        return displayType;
    }

    public Time loadStartDday() {
        return this.startDday;
    }
    public Time loadEndDday() {
        return this.endDday;
    }

    public void saveDday(Time startDday, Time endDday) {
        this.startDday = startDday;
        this.endDday = endDday;
    }

    public void reset() {
        startDday = null;
        endDday = null;
    }

    public Boolean changeFormat(Time currentTime) { //true면 d-day, false면 %
        String start = "";
        String end = String.valueOf(endDday.getYear()) + String.valueOf(endDday.getMonth()) + String.valueOf(endDday.getDay());
        if (this.startDday == null) {
            start = String.valueOf(currentTime.getYear()) + String.valueOf(currentTime.getMonth()) + String.valueOf(currentTime.getDay());
            return (this.displayType = true);
        } else if (this.startDday != null && this.endDday != null) {
            start = String.valueOf(startDday.getYear()) + String.valueOf(startDday.getMonth()) + String.valueOf(startDday.getDay());
            return (this.displayType = !this.displayType);
        }
        else
            return true;
    }
    public void ring(Time currentTime) {
        // TODO implement here
        if(currentTime == endDday) {
            //ring 3번 후 더 이상 수행X
        }
        return ;
    }
}