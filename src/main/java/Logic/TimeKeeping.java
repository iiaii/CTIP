package Logic;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Date;

public class TimeKeeping extends TimerTask {
    private LocalDateTime currentTime;
    private Boolean displayFormat = true;
    private Timer m_timer = null; // new instance
    private boolean hourFormat = true; // true menas 24 hours

    public LocalDateTime getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(LocalDateTime currentTime) {
        this.currentTime = currentTime;
    }

    public TimeKeeping(Timer m_timer) {
        currentTime = LocalDateTime.now();
        this.m_timer = m_timer;
        m_timer.schedule(this, 0, 1000);
    }// 생성자

    public TimeKeeping() {
        currentTime = LocalDateTime.now().plusHours(3);
    }

    @Override
    public void run() {
        currentTime.plusSeconds(1);
    }


    public String loadTime() {
        String data;
        SimpleDateFormat format = new SimpleDateFormat(displayFormat== true ? "yyyyMMddHHmmss" : "yyyyMMddhhmmss");
        data = format.format(currentTime);
        return data;
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