package Logic;
import GUI.DigitalWatch;
import com.sun.org.apache.bcel.internal.generic.LoadClass;
import javafx.scene.paint.Stop;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import java.time.ZoneId;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class WatchSystem extends TimerTask{
    private int currentCursor; //연도1 연도2 월 일 시 분 초
    private int currentModeCursor; //모드 커서 - 타이머, 스탑워치, 알람, dday, IT
    private int currentDdayPage=0;
    private int currentAlarmPage = 0;
    public Boolean[] setMode = {true,true,true,false,false};
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
        this.saveMode();
    }

    public void enterEditMode() {
        DigitalWatch gui = DigitalWatch.getInstance();
        //String data = "zzzzzzzzzzzzzzzzz";
        //Object currentMode = modeManager.getCurrentMode();
        if(currentMode instanceof TimeKeeping){
            tempTime = ((TimeKeeping) currentMode).loadTime();
            currentCursor = 0;
        }
        else if(currentMode instanceof WatchTimer){
            tempTime = ((WatchTimer) currentMode).loadTimer();
            currentCursor = 4;
        }
        else if(currentMode instanceof Alarm){
            tempTime = ((Alarm) currentMode).loadAlarm(currentAlarmPage);
            currentCursor = 4;
        }
        else if(currentMode instanceof Dday){
            tempTime = ((Dday) currentMode).loadStartDday();

            currentCursor = 0;
        }
        else if(currentMode instanceof IntervalTimer){
            tempTime = ((IntervalTimer) currentMode).loadIntervalTimer();
            currentCursor = 4;
        }
        else{
            System.out.println("errror");
        }
        isEditMode = true;
        //return data;
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
                tempTime.plusYears(100);
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

    public void changeHourFormat() {
        ((TimeKeeping)this.currentMode).setHourformat();
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

    public LocalDateTime changeAlarmPage() {
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
        Boolean[] setMode = modeManager.setMode;
        int[] showMode = new int[6];
        showMode[0] = this.modeManager.getTimekeeping().getDisplayFormat() == true ? 1 : 0;

        for(int i=0;i<setMode.length;i++){

        }
//        DigitalWatch.getInstance().showMode();

    }

    public void chooseModes() {
        if(setMode[currentCursor]==true)
            setMode[currentCursor] = false;
        else
            setMode[currentCursor] = true;
    }

    public void saveMode() {
        int count=0;
        for(int i=0;i<5;i++){
            if(setMode[i])
                count++;
        }
        if(count!=3){
            return;
        }
        modeManager.getModes().clear();
        if(setMode[0]){
            modeManager.createTimer();
        }
        else{
            modeManager.destoryTimer();
        }
        if(setMode[1]){
            modeManager.createStopwatch();
        }
        else{
            modeManager.destroyStopwatch();
        }
        if(setMode[2]){
            modeManager.createAlarm();
        }
        else{
            modeManager.destroyAlarm();
        }
        if(setMode[3]){
            modeManager.createDday();
        }
        else{
            modeManager.destroyDday();
        }
        if(setMode[4]){
            modeManager.createIntervalTimer();
        }
        else{
            modeManager.destroyDday();
        }
        // exitSetMode();
        //selected Mode
        //this.watchTimer = modeManager.createTimer();
        return;
    }

    public void exitSetMode(){
        isSetMode = false;
    }

    public void muteBeep() {

        return;
    }

    public Boolean[] enterSetMode() {
        currentMode = null;
        currentCursor = 0;
        isSetMode = true;
        System.arraycopy(modeManager.loadSetMode(),0,setMode,0,5);
        return setMode;
    }

    public void activateTimer() {
        modeManager.getWatchTimer().activate();
    }
    /* gui part*/
    public void run() {
        //Date date = java.util.Date.from(watchTimer.getRemainedTimer().atZone(ZoneId.systemDefault()).toInstant());
        DigitalWatch gui = DigitalWatch.getInstance();
        String data;
        try {
            if(this.isSetMode==true){
                data = "zzzzzzzzzzzzzzzzz";
            }
            else{
                data = ideal(currentMode);
            }
            gui.showDigit(data);
        }
        catch (Exception e) {
            System.out.println("나한테왜그래");
            e.printStackTrace();
        }
    }


    public Boolean getIsEditMode() {
        return isEditMode;
    }

    public void setEditMode(Boolean editMode) {
        isEditMode = editMode;
    }

    public Boolean getIsSetMode() {
        return isSetMode;
    }

    public void setIsSetMode(Boolean setMode) {
        isSetMode = setMode;
    }

    public Object getCurrentMode(){
        return this.currentMode;
    }
    public String ideal(Object mode) {
        String data,data2;
        Boolean displayFormat;
        SimpleDateFormat format;
        LocalDateTime origin;

        if(mode instanceof TimeKeeping){
            format = new SimpleDateFormat(((TimeKeeping) mode).getDisplayFormat() ? "yyyyMMddHHmmss" : "yyyyMMddhhmmss");
            origin = ((TimeKeeping) mode).loadTime();
            data = format.format(LocaltoDate(origin));
            return data;
        }
        else if(mode instanceof WatchTimer){
            format = new SimpleDateFormat("HHmmss");
            origin = ((WatchTimer) mode).loadTimer();
            data = format.format(LocaltoDate(origin));
            format = new SimpleDateFormat("yyyyMMdd");
            data2 = format.format(LocaltoDate(modeManager.getTimekeeping().getCurrentTime()));
            return data2+data;
        }
        else if(mode instanceof StopWatch){
            format = new SimpleDateFormat("HHmmss");
            data = format.format(Date.from(((StopWatch) mode).loadStopWatch().atDate(LocalDate.now()).atZone((ZoneId.systemDefault())).toInstant()));
            return "dzzzzzz"+((StopWatch) mode).getCountDay()+data;
        }
        else if(mode instanceof Alarm){
            format = new SimpleDateFormat("HHmmss");
            data = format.format(LocaltoDate(((Alarm) mode).loadAlarm(currentAlarmPage)));
            if(((Alarm) mode).getAlarmTime(currentAlarmPage).getEnabled() == true)
                data2 = "ENzzzzz"+currentAlarmPage;
            else
                data2 = "zzzzzzz"+currentAlarmPage;
            return data2+data;
        }
        else if(mode instanceof Dday){
            format = new SimpleDateFormat("yyMMdd");
            Boolean displayType = ((Dday) mode).getDisplayType();
            data = format.format(LocaltoDate(((Dday) mode).loadStartDday()));
            if(displayType==true)
                data2 = "d-"+(int)((Dday) mode).getCalDday()%10000;
            else
                data2 = (Math.round(100* ((Dday) mode).getCalDday())/100)+"PE";
            return data+data2;
        }
        else if(mode instanceof IntervalTimer) {
            format = new SimpleDateFormat("HHmmss");
            data = format.format(LocaltoDate(((IntervalTimer) mode).loadIntervalTimer()));
            data2 = "" + ((IntervalTimer) mode).getIteration();
            return data2 + data;
        }
        return "zzzzzzzzzzzzzzzzz";
    }
    public Date LocaltoDate(LocalDateTime time){
        return Date.from(time.atZone(ZoneId.systemDefault()).toInstant());
    }
}