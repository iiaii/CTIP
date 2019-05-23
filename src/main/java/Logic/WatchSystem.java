package Logic;
import GUI.DigitalWatch;
import com.sun.org.apache.bcel.internal.generic.LoadClass;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import java.time.ZoneId;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class WatchSystem extends TimerTask {
    private int currentCursor; //연도1 연도2 월 일 시 분 초
    private int currentModeCursor; //모드 커서 - 타이머, 스탑워치, 알람, dday, IT
    private int currentDdayPage=0;
    private int currentAlarmPage = 0;
    private LocalDateTime tempTime;
    private LocalDateTime tempTime2;
    public ModeManager modeManager;
    private Boolean isEditMode;
    private Boolean isSetMode;



    public LocalDateTime enterEditMode() {
        LocalDateTime data;
        Object currentMode = modeManager.getCurrentMode();

        if(currentMode instanceof TimeKeeping){
            data = ((TimeKeeping) currentMode).loadTime();
            currentCursor = 0;
        }
        else if(currentMode instanceof WatchTimer){
            data = ((WatchTimer) currentMode).loadTimer();
            currentCursor = 4;
        }
        else if(currentMode instanceof Alarm){
            data = ((Alarm) currentMode).loadAlarm(currentAlarmPage);
            currentCursor = 4;
        }
        else if(currentMode instanceof Dday){
            data = ((Dday) currentMode).loadStartDday();
            currentCursor = 0;
        }
        else if(currentMode instanceof IntervalTimer){
            data = ((IntervalTimer) currentMode).loadIntervalTimer();
            currentCursor = 4;
        }
        else{
            return null;//error
        }
        tempTime = data;

        return data;
    }

    /* IN Timekeeping
        currentCursor
            0 -> Year1
            1 -> year2
            2 -> month
            3 -> day
            4 -> hour
            5 -> min
            6 -> second
     */
    public LocalDateTime increaseData() {
        Object currentMode = modeManager.getCurrentMode();
        if(currentCursor==0){
            if(currentMode instanceof TimeKeeping || currentMode instanceof Dday){
                tempTime.plusYears(10);
            }
            else{
                return null; //error
            }
        }
        else if(currentCursor==1){
            if(currentMode instanceof TimeKeeping || currentMode instanceof Dday){
                tempTime.plusYears(1);
            }
            else{
                return null; //error
            }
        }
        else if(currentCursor==2){
            if(currentMode instanceof TimeKeeping || currentMode instanceof Dday){
                tempTime.plusMonths(1);
            }
            else{
                return null; //error
            }
        }
        else if(currentCursor==3){
            if(currentMode instanceof TimeKeeping || currentMode instanceof Dday){
                tempTime.plusDays(1);
            }
            else{
                return null; //error
            }
        }
        else if(currentCursor==4){
            if(!(currentMode instanceof Dday || currentMode instanceof Stopwatch)){
                tempTime.plusHours(1);
            }
            else{
                return null; //error
            }
        }
        else if(currentCursor==5){
            if(!(currentMode instanceof Dday || currentMode instanceof StopWatch)){
                tempTime.plusMinutes(1);
            }
            else{
                return null;
            }
        }
        else if(currentCursor==6){
            if(!(currentMode instanceof Dday || currentMode instanceof StopWatch)){
                tempTime.plusSeconds(1);
            }
        }
        return tempTime;
    }

    public void changeCursor() {
        // is editmode
        Object currentMode = modeManager.getCurrentMode();
        if(currentMode instanceof TimeKeeping){
            currentCursor = currentCursor++ % 7;
        }
        else if(currentMode instanceof Dday){
            currentCursor = currentCursor++ %4;
        }
        else if(currentMode instanceof Alarm || currentMode instanceof WatchTimer ||
                currentMode instanceof IntervalTimer){
            currentCursor = currentCursor++ % 3 + 4;
        }
        else{
            //error
        }
    }

    public void saveTime() {
        modeManager.getTimekeeping().saveTime(tempTime);
        tempTime = null;
    }

    public void pauseTimer() {
        modeManager.getWatchTimer().pause();
    }

    public void resetTimer() {
        modeManager.getWatchTimer().reset();
    }

    public void saveTimer() {
        modeManager.getWatchTimer().saveTimer(tempTime);
        tempTime = null;
    }

    public void enablentervalTimer() {
        modeManager.getIntervaltimer().enable();
    }

    public void disableIntervalTimer() {
        modeManager.getIntervaltimer().disable();
    }

    public void saveIntervalTimer() {
        modeManager.getIntervaltimer().saveIntervalTimer(tempTime);
        tempTime = null;
    }

    public void resetIntervalTimer() {
        modeManager.getIntervaltimer().reset();
    }

    public void saveAlarm() {
        modeManager.getAlarm().saveAlarm(tempTime);
        tempTime = null;
    }

    public LocalDateTime resetAlarm() {
        tempTime = null;
        return tempTime;
    }

    public void enableAlarm() {
        modeManager.getAlarm().enableAlarm(currentAlarmPage);
    }

    public void disableAlarm() {
        modeManager.getAlarm().disableAlarm(currentAlarmPage);
    }

    public LocalDateTime changeAlarmPage() {
        currentAlarmPage = (currentAlarmPage++) % 4;
        return modeManager.getAlarm().loadAlarm(currentAlarmPage);
    }

    public LocalDateTime changePage() {
        currentDdayPage = (currentDdayPage+1)%2;
        if(currentDdayPage==0){
            temp1 = modeManager.getDday().loadStartDday();
            return tempTime;
        }
        else{
            temp2 = modeManager.getDday().loadEndDday();
            return tempTime2;
        }
    }

    public void saveDday() {
        modeManager.getDday().saveDday(tempTime,tempTime2);
    }

    public LocalDateTime resetDday() {
        modeManager.getDday().reset();;
        return dday.loadEndDday().atTime(0,0,0);
    }

    public double changeDdayFormat() {
        modeManager.getDday().changeFormat();
        return modeManager.getDday().getCalDday();
    }

    public void activateStopwatch() {
        modeManager.getStopwatch().activate();
    }

    public void pauseStopwatch() {
        modeManager.getStopwatch().pause();
    }

    public void resetStopwatch() {
        modeManager.getStopwatch().reset();
    }

    public int changeMode() {
        int selectedMode = modeManager.getNextMode();
        return selectedMode;
    }

    public void chooseModes(int modeNum) {
        modeManager.toggleMode(modeNum);
    }

    public void saveMode() {
         selected Mode
        this.watchTimer = modeManager.createTimer();
        return;
    }

    public LocalDateTime changeHourFormat() {

        return null;
    }

    public void muteBeep() {

        return;
    }

    public int enterSetMode() {

        return 0;
    }

    public void activateTimer() {
        modeManager.getWatchTimer().activate();
    }

//    public static void main(String[]args) throws Exception {
//        Dday dd = new Dday();
//        dd.saveDday(LocalDateTime.now().toLocalDate(), LocalDateTime.of(2019,12,9,0,0,0).toLocalDate());
//        if(dd.getDisplayType())
//            System.out.println("d-"+dd.getCalDday());
//        else
//            System.out.println(+dd.getCalDday()+"%");
//        dd.changeFormat();
//        dd.saveDday(LocalDateTime.of(2019,1,1,0,0,0).toLocalDate(), LocalDateTime.of(2019,12,9,0,0,0).toLocalDate());
//        DecimalFormat dec = new DecimalFormat("#0.00");
//        if(dd.getDisplayType())
//            System.out.println("d-"+dd.getCalDday());
//        else
//            System.out.println(dec.format(dd.getCalDday())+"%");
//    }
    public void run() {
//        timekeeping = new TimeKeeping();
//        Date date = java.util.Date.from(timekeeping.getCurrentTime().atZone(ZoneId.systemDefault()).toInstant());
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
//        try {
//            DigitalWatch.getInstance().showDigit(sdf.format(date));
//        } catch(Exception e) {
//
//        }
//
//        Object currentMode = new TimeKeeping();
//        if(currentMode instanceof TimeKeeping) {
//            TimeKeeping timeKeeping = (TimeKeeping) currentMode;
//            timeKeeping.getCurrentTime();
//        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = java.util.Date.from(watchTimer.getRemainedTimer().atZone(ZoneId.systemDefault()).toInstant());
        try {

            DigitalWatch.getInstance().showDigit(sdf.format(date));
        }catch (Exception e){

        }
    }

    public WatchSystem() {
        DigitalWatch.getInstance();
        LocalDate tmpDate = LocalDate.now();
        LocalTime tmpTime = LocalTime.of(0,0,9);
        LocalDateTime tmp = LocalDateTime.of(tmpDate, tmpTime);
        this.watchTimer = new WatchTimer(tmp);
        watchTimer.activate();
    }
    public static void main(String[] args) {
        Timer t = new Timer();
        t.scheduleAtFixedRate(new WatchSystem(), 0, 100);

    }
}