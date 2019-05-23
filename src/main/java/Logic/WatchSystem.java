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

public class WatchSystem {
    private int currentCursor; //연도1 연도2 월 일 시 분 초
    private int currentModeCursor; //모드 커서 - 타이머, 스탑워치, 알람, dday, IT
    private int currentDdayPage=0;
    private int currentAlarmPage = 0;
    public Boolean[] setMode = new Boolean[5];
    private LocalDateTime tempTime;
    private LocalDateTime tempTime2;
    public ModeManager modeManager;
    private Boolean isEditMode = false;
    private Boolean isSetMode = false;
    private Object currentMode = null;
    private Timer m_timer;

    public WatchSystem(Timer m_timer) {
        this.m_timer = m_timer;
        this.modeManager = new ModeManager(m_timer);
        this.currentMode = this.modeManager.getTimekeeping();
        this.isSetMode = false;
        this.isEditMode = false;
        this.currentCursor = 0;
        this.currentModeCursor = 0;
        this.currentDdayPage = 0;
        this.currentAlarmPage = 0;
        this.tempTime = null;
        this.tempTime2 = null;
    }

    public void enterEditMode() {
        DigitalWatch gui = DigitalWatch.getInstance();
        String data = "zzzzzzzzzzzzzzzzz";
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
            System.out.println("errror");
        }
        gui.showDigit(data);
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
            if(!(currentMode instanceof Dday || currentMode instanceof StopWatch)){
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
        Object currentMode = modeManager.getCurrentMode();
        if(currentMode instanceof TimeKeeping){
            currentCursor = (currentCursor+1) % 7;
        }
        else if(currentMode instanceof Dday){
            currentCursor = (currentCursor+1) % 4;
        }
        else if(currentMode instanceof Alarm || currentMode instanceof WatchTimer ||
                currentMode instanceof IntervalTimer){
            currentCursor = (currentCursor+1) % 3 + 4;
        }
        else if(currentMode == null){
            currentCursor = (currentCursor+1) %5;
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
        modeManager.getAlarm().saveAlarm(currentAlarmPage,tempTime);
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

    public String changeAlarmPage() {
        currentAlarmPage = (currentAlarmPage+1) % 4;
        return modeManager.getAlarm().loadAlarm(currentAlarmPage);
    }

    public LocalDateTime changePage() {
        currentDdayPage = (currentDdayPage+1)%2;
        String data;
        if(currentDdayPage==0){
            tempTime = modeManager.getDday().getCurrentDay();
//            data =
            return tempTime;
        }
        else{
            tempTime2 = modeManager.getDday().loadEndDday();
            return tempTime2;
        }
    }

    public void saveDday() {
        modeManager.getDday().saveDday(tempTime,tempTime2);
    }

    public LocalDateTime resetDday() {
        modeManager.getDday().reset();
        return modeManager.getDday().loadEndDday();
    }

    public double changeDdayFormat() {
        modeManager.getDday().changeFormat();
        return modeManager.getDday().getCalDday();
    }

    public int getCurrentDdayPage() {
        return currentDdayPage;
    }

    public int getCurrentAlarmPage() {
        return currentAlarmPage;
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

    public void changeMode() {
        this.currentMode = modeManager.getNextMode();
    }

    public void chooseModes() {
        if(setMode[currentModeCursor]==true)
            setMode[currentModeCursor] = false;
        else
            setMode[currentModeCursor] = true;
    }

    public void saveMode() {
        //selected Mode
        //this.watchTimer = modeManager.createTimer();
        return;
    }

    public void changeHourFormat() {
        ((TimeKeeping)this.currentMode).setHourformat();
    }

    public void muteBeep() {

        return;
    }

    public Boolean[] enterSetMode() {
        currentMode = null;
        System.arraycopy(modeManager.loadSetMode(),0,setMode,0,5);
        return setMode;
    }

    public void activateTimer() {
        modeManager.getWatchTimer().activate();
    }

    public void run() {
        //Date date = java.util.Date.from(watchTimer.getRemainedTimer().atZone(ZoneId.systemDefault()).toInstant());
        DigitalWatch gui = DigitalWatch.getInstance();
        try {
            if(this.currentMode instanceof TimeKeeping) {
                SimpleDateFormat sdf = new SimpleDateFormat(((TimeKeeping)this.currentMode).getDisplayFormat() == true ? "yyyyMMddHHmmss" : "yyyyMMddhhmmss");
                String showTime = sdf.format(Date.from(((TimeKeeping)this.currentMode).getCurrentTime().atZone(ZoneId.systemDefault()).toInstant()));
                gui.showDigit(showTime);
            }

            if(this.currentMode instanceof WatchTimer) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                String showTime = sdf.format(Date.from(((WatchTimer)this.currentMode).getRemainedTimer().atZone(ZoneId.systemDefault()).toInstant()));
                gui.showDigit(showTime);
            }
            if(this.currentMode instanceof Alarm){

            }
        }catch (Exception e){
            System.out.println("나한테왜그래");
            e.printStackTrace();
        }
    }


    public Boolean getEditMode() {
        return isEditMode;
    }

    public void setEditMode(Boolean editMode) {
        isEditMode = editMode;
    }

    public Boolean getSetMode() {
        return isSetMode;
    }

    public void setSetMode(Boolean setMode) {
        isSetMode = setMode;
    }

    public Object getCurrentMode(){
        return this.currentMode;
    }
}