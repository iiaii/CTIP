package Logic;
import GUI.DigitalWatch;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

import java.time.ZoneId;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class WatchSystem extends TimerTask {
    private int currentCursor; //연도1 연도2 월 일 시 분 초
    private int currentModeCursor; //모드 커서 - 타이머, 스탑워치, 알람, dday, IT
    private int currentDdayPage; //Startdday, Enddday page
    private LocalDateTime tempTime;
    private LocalDateTime tempTime2;
    private TimeKeeping timekeeping;
    private WatchTimer watchTimer;
    private StopWatch stopwatch;
    private Alarm alarm;
    private Dday dday;
    private IntervalTimer intervaltimer;
    public ModeManager modeManager;

    public LocalDateTime enterEditMode() {
        currentCursor=0; //연도1부터 커서
        currentModeCursor = 0; //모드 커서 타이머부터
        if(modeManager.getCurrentMode()==1 || modeManager.getCurrentMode()==3 || modeManager.getCurrentMode()==5)
            currentCursor = 4; //시간만 건들이는 모드들은 시 부터 커서


//        switch (modeManager.getCurrentMode()) {
//            case 0: //timekeeping
//                tempTime = timekeeping.getCurrentTime();
//                break;
//            case 1: //wahtchTimer
//                tempTime = watchTimer.loadTimer();
//                break;
//            case 3: //alarm
//                tempTime = alarm.loadAlarm();
//                break;
//            case 4: //dday
//                tempTime = timekeeping.getCurrentTime();
//                tempTime2 = timekeeping.getCurrentTime();
//                break;
//            case 5: //interval timer
//                tempTime = intervaltimer.loadIntervalTimer();
//                break;
//            default:
//                System.out.println("Error");
//        }
        return tempTime;
    }
    public LocalDateTime increaseData() {
        switch (modeManager.getCurrentMode()) {
            case 0: //timekeeping
                break;
            case 1: //timer
                break;
            case 3: //alarm
                break;
            case 4: //dday
                if(currentDdayPage==0) {
                    if(currentCursor==0)
                        tempTime.plusYears(100);
                    else if (currentCursor == 1)
                        tempTime.plusYears(1);
                    else if (currentCursor == 2)
                        tempTime.plusMonths(1);
                    else
                        tempTime.plusDays(1);
                } else {
                    if (currentCursor == 0)
                        tempTime2.plusYears(100);
                    else if (currentCursor == 1)
                        tempTime2.plusYears(1);
                    else if (currentCursor == 2)
                        tempTime2.plusMonths(1);
                    else
                        tempTime2.plusDays(1);
                }
                break;
            case 5: //interval timer
                break;
            default:
                System.out.println("Error");

        }
        return tempTime;
    }

    public int changeCursor() {
        switch (modeManager.getCurrentMode()) {
            case 0: //timekeeping
                currentCursor = (currentCursor + 1) % 7;
                break;
            case 1:
            case 3:
            case 5: //timer, alarm, interval timer
                currentCursor = (currentCursor + 1) % 3 + 4;
                break;
            case 4: //dday
                currentCursor = (currentCursor + 1) % 4;
        }
        return currentCursor;
    }

    public void saveTime() {
        return;
    }

    public void pauseTimer() {
        modeManager.getWatchTimer().pause();
    }

    public void resetTimer() {
        modeManager.getWatchTimer().reset();
    }

    public void saveTimer(LocalDateTime data) {
//        modeManager.getWatchTimer().saveTimer(data);
    }

    public void enablentervalTimer() {
        modeManager.getIntervaltimer().enable();
        return;
    }

    public void disableIntervalTimer() {
        modeManager.getIntervaltimer().disable();
        return;
    }

    public void saveIntervalTimer(LocalDateTime data) {
        modeManager.getIntervaltimer().saveIntervalTimer(data);
        return;
    }

    public void resetIntervalTimer() {
        modeManager.getIntervaltimer().reset();
        return;
    }

    public void saveAlarm() {

        return;
    }

    public void resetAlarm() {

        return;
    }

    public void enableAlarm() {

        return;
    }

    public void disableAlarm() {

        return;
    }

    public AlarmTime changeAlarmPage() {

        return null;
    }

    public LocalDateTime changePage() {
        currentDdayPage = (currentDdayPage+1)%2;
        if(currentDdayPage==0) {
            tempTime = dday.loadStartDday().atTime(0,0,0);
            return tempTime;
        }
        else {
            tempTime2 = dday.loadEndDday().atTime(0,0,0);
            return tempTime2;
        }
    }

    public void saveDday() {
        dday.saveDday(tempTime.toLocalDate(), tempTime2.toLocalDate());
        return;
    }

    public LocalDateTime resetDday() {
        dday.reset();
        return dday.loadEndDday().atTime(0, 0, 0);
    }

    public double changeDdayFormat() {
        dday.changeFormat();
        return dday.getCalDday();
    }

    public void activateStopwatch() {
        stopwatch.activate();
        return;
    }

    public void pauseStopwatch() {
        stopwatch.pause();
        return;
    }

    public void resetStopwatch() {
        stopwatch.reset();
        return;
    }

    public int changeMode() {

        return 0;
    }

    public void chooseModes() {

        return;
    }

    public void saveMode() {
        // selected Mode
//        this.watchTimer = modeManager.createTimer();
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
        timekeeping = new TimeKeeping();
        Date date = java.util.Date.from(timekeeping.getCurrentTime().atZone(ZoneId.systemDefault()).toInstant());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        try {
            DigitalWatch.getInstance().showDigit(sdf.format(date));
        } catch(Exception e) {

        }

        Object currentMode = new TimeKeeping();
        if(currentMode instanceof TimeKeeping) {
            TimeKeeping timeKeeping = (TimeKeeping) currentMode;
            timeKeeping.getCurrentTime();
        }

    }

    public WatchSystem() {
        DigitalWatch.getInstance();
    }
    public static void main(String[] args) {
        Timer t = new Timer();
        t.scheduleAtFixedRate(new WatchSystem(), 0, 100);

    }
}