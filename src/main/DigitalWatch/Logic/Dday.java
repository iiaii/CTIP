package Logic;
public class Dday {
    public Time startDday;
    public Time endDday;
    public Boolean displayType;

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
        this.startDday = null;
        this.endDday = null;
    }
    public Boolean changeFormat() {
        if(this.startDday == null)
            return (this.displayType = true);
        else if(this.startDday != null && this.endDday != null)
            return this.displayType = !this.displayType;

        return true;
    }
    public void ring() {
        // TODO implement here
        return ;
    }
}