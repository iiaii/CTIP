package Logic;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Date;

public class TimeKeeping extends TimerTask {
    private LocalDateTime currentTime;
    private Boolean displayFormat = true; // true 24H false AMPM
    private Timer m_timer = null; // new instance

    public Boolean getDisplayFormat() {
        return displayFormat;
    }

    public LocalDateTime getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(LocalDateTime currentTime) {
        this.currentTime = currentTime;
    }

    public TimeKeeping(Timer m_timer) {
        currentTime = LocalDateTime.now();
        this.m_timer = m_timer;
        m_timer.scheduleAtFixedRate(this, 0, 1000);
    }// 생성자

    @Override
    public void run() {
        currentTime = currentTime.plusSeconds(1);
    }


    public LocalDateTime loadTime() {
        return this.currentTime;
    }

    public void saveTime(LocalDateTime data) {
        this.currentTime = data;
    }

    public void setHourformat() {
        // TODO implement here
        this.displayFormat = !this.displayFormat;
    }

    public void stopTimeKeeping() {
        if (m_timer != null)
            cancel();
    }
}

//    public void initCurrentTime(){
//        currentDateTime = LocalDateTime.now();
//        int year,month,day,hour,minute,second,secs;
////        long time  = System.currentTimeMillis();
////        Date date = new Date(time);
////        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
////        SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
////        SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
////        SimpleDateFormat hourFormat = new SimpleDateFormat("HH");
////        SimpleDateFormat minuteFormat = new SimpleDateFormat("mm");
////        SimpleDateFormat secondFormat = new SimpleDateFormat("ss");
////
////        year = Integer.valueOf(yearFormat.format(date));
////        month = Integer.valueOf(monthFormat.format(date));
////        day = Integer.valueOf(dayFormat.format(date));
////        hour = Integer.valueOf(hourFormat.format(date));
////        minute = Integer.valueOf(minuteFormat.format(date));
////        second = Integer.valueOf(secondFormat.format(date));
//        //        secs = day*86400+3600*hour + minute*60 + second;
//
//        year = currentDateTime.getYear();
//        month = currentDateTime.getMonthValue();
//        day = currentDateTime.getDayOfMonth();
//        hour = currentDateTime.getHour();
//        minute = currentDateTime.getMinute();
//        second = currentDateTime.getSecond();
//
//
//        currentDateTime.plusSeconds(1);
//    }
//
//}