package Logic;
import GUI.DigitalWatch;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;

import java.time.ZoneId;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class WatchSystem extends TimerTask{
    private int currentCursor; //연도1 연도2 월 일 시 분 초
    private int currentModeCursor; //모드 커서 - 타이머, 스탑워치, 알람, dday, IT
    private int currentDdayPage=0;
    private int currentAlarmPage = 0;
    public Boolean[] setMode = {true,true,false,false,true};
    private LocalDateTime tempTime;
    private LocalDateTime tempTime2;
    public ModeManager modeManager;
    private Boolean isEditMode = false;
    private Boolean isSetMode = false;
    private Object currentMode = null;
    private Timer m_timer;
    private Boolean isEdited = false;

    public WatchSystem(Timer m_timer) {
        this.m_timer = m_timer;
        this.modeManager = new ModeManager(m_timer);
        this.currentMode = this.modeManager.getCurrentMode();
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
        isEditMode = true;
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
            tempTime2 = ((Dday) currentMode).loadEndDday();
            currentCursor = 0;
        }
        else if(currentMode instanceof IntervalTimer){
            tempTime = ((IntervalTimer) currentMode).loadIntervalTimer();
            currentCursor = 4;
        }
        else{
            System.out.println("errror");
        }
    }
    public LocalDateTime increaseData() {
        Object currentMode = modeManager.getCurrentMode();
        if(currentCursor==0){
            if(this.currentDdayPage == 0){
                tempTime = tempTime.plusYears(100);
                isEdited = true;
            } else {
                tempTime2 = tempTime2.plusYears(100);
            }
        }
        else if(currentCursor==1){
            if(this.currentDdayPage == 0){
                tempTime = tempTime.plusYears(1);
                if(tempTime.getYear() % 100 == 0) tempTime = tempTime.minusYears(100);
                isEdited = true;
            } else {
                tempTime2 = tempTime2.plusYears(1);
                if(tempTime2.getYear() % 100 == 0) tempTime2 = tempTime2.minusYears(100);
            }
        }
        else if(currentCursor==2){
            if(this.currentDdayPage == 0){
                tempTime = tempTime.plusMonths(1);
                if(tempTime.getMonthValue() == 1) tempTime = tempTime.minusYears(1);
                isEdited = true;
            } else {
                tempTime2 = tempTime2.plusMonths(1);
                if(tempTime2.getMonthValue() == 1) tempTime2 = tempTime2.minusYears(1);
            }
        }
        else if(currentCursor==3){
            if(this.currentDdayPage == 0){
                tempTime = tempTime.plusDays(1);
                if(tempTime.getDayOfMonth() == 1) tempTime = tempTime.minusMonths(1);
                isEdited = true;
            } else {
                tempTime2 = tempTime2.plusDays(1);
                if(tempTime2.getDayOfMonth() == 1) tempTime2 = tempTime2.minusMonths(1);
            }
        }
        else if(currentCursor==4){
            tempTime = tempTime.plusHours(1);
            if(tempTime.getHour() == 0) tempTime = tempTime.minusDays(1);
        }
        else if(currentCursor==5){
            tempTime = tempTime.plusMinutes(1);
            if(tempTime.getMinute() == 0) tempTime = tempTime.minusHours(1);
        }
        else if(currentCursor==6){
            tempTime = tempTime.plusSeconds(1);
            if(tempTime.getSecond() == 0) tempTime = tempTime.minusMinutes(1);
        }

        if(this.currentDdayPage == 0) {
            return tempTime;
        } else {
            return tempTime2;
        }
    }

    public void changeCursor() {
        Object currentMode = modeManager.getCurrentMode();
        if(isSetMode == true){
            currentModeCursor = (currentModeCursor + 1) % 5;
        } else {
            if(currentMode instanceof TimeKeeping){
                currentCursor = (currentCursor+1) % 7;
            }
            else if(currentMode instanceof Dday){
                currentCursor = (currentCursor+1) % 4;
            }
            else if(currentMode instanceof Alarm || currentMode instanceof WatchTimer ||
                    currentMode instanceof IntervalTimer){
                currentCursor = (currentCursor) % 3 + 4; // 0, 1, 2 //4 5 6
            }
        }
    }

    public void saveTime() {
        ((TimeKeeping)currentMode).saveTime(tempTime);
        exitEditMode();
    }

    public void changeHourFormat() {
        ((TimeKeeping)this.currentMode).setHourformat();
    }

    public void pauseTimer() {
        ((WatchTimer)currentMode).pause();
    }

    public void resetTimer() {
        ((WatchTimer)currentMode).reset();
    }

    public void saveTimer() {
        ((WatchTimer)currentMode).saveTimer(tempTime);
        exitEditMode();
    }

    public void enablentervalTimer() {
        ((IntervalTimer)currentMode).enable();
    }

    public void disableIntervalTimer() {
        ((IntervalTimer)currentMode).disable();
    }

    public void saveIntervalTimer() {
        ((IntervalTimer)currentMode).saveIntervalTimer(tempTime);
        exitEditMode();
    }

    public void resetIntervalTimer() {
        ((IntervalTimer)currentMode).reset();
    }

    public void saveAlarm() {
        ((Alarm)currentMode).saveAlarm(currentAlarmPage, tempTime);
        exitEditMode();
    }

    public LocalDateTime resetAlarm() {
        tempTime = null;
        return tempTime;
    }

    public void enableAlarm() {
        ((Alarm)currentMode).enableAlarm(currentAlarmPage);
    }

    public void disableAlarm() {
        ((Alarm)currentMode).disableAlarm(currentAlarmPage);
    }

    public LocalDateTime changeAlarmPage() {
        currentAlarmPage = (currentAlarmPage+1) % 4;
        return  ((Alarm)currentMode).loadAlarm(currentAlarmPage);
    }

    public void changePage() {
        currentDdayPage = (currentDdayPage+1)%2;
    }

    public void saveDday() {
        ((Dday)currentMode).saveDday((this.isEdited == true || ((Dday)currentMode).getExistStartDday() == true) ? tempTime : null,tempTime2); // 수정됐으면  temptime, 아니면 null
        exitEditMode();
    }

    public LocalDateTime resetDday() {
        ((Dday)currentMode).reset();
        return ((Dday)currentMode).loadEndDday();
    }

    public double changeDdayFormat() {
        ((Dday)currentMode).changeFormat();
        return ((Dday)currentMode).getCalDday();
    }

    public int getCurrentDdayPage() {
        return currentDdayPage;
    }

    public int getCurrentAlarmPage() {
        return currentAlarmPage;
    }

    public void activateStopwatch() {
        ((StopWatch)currentMode).activate();
    }

    public void pauseStopwatch() {
        ((StopWatch)currentMode).pause();
    }

    public void resetStopwatch() {
        ((StopWatch)currentMode).reset();
    }

    public void changeMode() {
        this.currentMode = modeManager.getNextMode();
        this.currentDdayPage = 0;
        this.currentAlarmPage = 0;
        this.isEdited = false;

    }

    public void chooseModes() {
        System.out.println(setMode[currentModeCursor]);
        setMode[currentModeCursor] = !setMode[currentModeCursor];
        System.out.println(setMode[currentModeCursor]);
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
            modeManager.destroyIntervalTimer();
        }
        exitSetMode();
        //selected Mode
        //this.watchTimer = modeManager.createTimer();
        return;
    }

    public void exitSetMode(){
        isSetMode = false;
        currentModeCursor = 0;
        this.currentMode = modeManager.getCurrentMode();
    }

    public void exitEditMode() {
        isEditMode = false;
        currentDdayPage = 0;
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
        ((WatchTimer)currentMode).activate();
    }
    /* gui part*/
    public void run() {
        //Date date = java.util.Date.from(watchTimer.getRemainedTimer().atZone(ZoneId.systemDefault()).toInstant());
        DigitalWatch gui = DigitalWatch.getInstance();
        String data;
        int icon[] = new int[6];

        try {
            icon = iconIdeal();
            gui.showMode(icon);
            /* show digit part */
            if(this.isSetMode==true){
                data = "zzzzzzzzzzzzzzzzz";
            }
            else{
                data = digitIdeal(currentMode);
            }
            gui.showDigit(data);
            if(this.isEditMode) {
                gui.selectCursor(this.currentCursor);
            } else {
                gui.selectCursor(-1);
            }
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

    public String digitIdeal(Object mode) {
        String data= "",data2 = "";
        Boolean displayFormat;
        SimpleDateFormat format;
        LocalDateTime origin = null;

        if(mode instanceof TimeKeeping){
            format = new SimpleDateFormat(((TimeKeeping) mode).getDisplayFormat() ? "yyyyMMddHHmmss" : "yyyyMMddhhmmss");
            if(this.isEditMode == true) {
                origin = this.tempTime;
            } else {
                origin = (modeManager.getTimekeeping()).loadTime();
            }
            data = format.format(LocaltoDate(origin));
            return data;
        }
        else if(mode instanceof WatchTimer){
            format = new SimpleDateFormat("HHmmss");
            if(this.isEditMode == true) {
                origin = this.tempTime;
                data = format.format(LocaltoDate(origin));
                data2 = "zzzzzzzz";
            } else {
                origin = ((WatchTimer) mode).loadTimer();
                data = format.format(LocaltoDate(origin));
                format = new SimpleDateFormat("yyyyMMdd");
                data2 = format.format(LocaltoDate(modeManager.getTimekeeping().getCurrentTime()));
            }
            return data2+data;
        }
        else if(mode instanceof StopWatch){
            format = new SimpleDateFormat("HHmmss");
            if(this.isEditMode == true) {
                data = "zzzzzzzz" + format.format(this.tempTime);
            } else {
                // 10일넘으면 좆된다시발
                data = "dzzzzzz"+((StopWatch) mode).getCountDay() + format.format(Date.from(((StopWatch) mode).loadStopWatch().atDate(LocalDate.now()).atZone((ZoneId.systemDefault())).toInstant()));
            }
            return data;
        }
        else if(mode instanceof Alarm){
            format = new SimpleDateFormat("HHmmss");
            if(this.isEditMode == true) {
                data = "zzzzzzzz" + format.format(this.tempTime);
            } else {
                data = format.format(LocaltoDate(((Alarm) mode).loadAlarm(currentAlarmPage)));
                if(((Alarm) mode).getAlarmTime(currentAlarmPage).getEnabled() == true)
                    data2 = "ENzzzzz"+currentAlarmPage;
                else
                    data2 = "zzzzzzz"+currentAlarmPage;
            }
            return data2+data;
        }
        else if(mode instanceof Dday){
            format = new SimpleDateFormat("yyyyMMdd");
            if(this.isEditMode == true) {
                if(currentDdayPage == 0){
                    data = format.format(LocaltoDate(this.tempTime)) + "zzzzzz";
                } else {
                    data = format.format(LocaltoDate(this.tempTime2)) + "zzzzzz";
                }
            } else {
                Boolean displayType = ((Dday) mode).getDisplayType();
                data = format.format(LocaltoDate(((Dday) mode).loadEndDday()));
                if(displayType==true)
                    data2 = "d-" + String.format("%04d", (int)((Dday) mode).getCalDday() % 10000);
                else {
                    data2 = (String.format("%04.2f", ((Dday)mode).getCalDday()) + ((((Dday)mode).getCalDday() * 100) % 100 == 0 ? "0" : "")).replace(".","") + "PE";
                    if(((Dday)mode).getCalDday() == 100) {
                        data2 = "zD0nEz";
                    }
                }
            }

            return data+data2;
        }
        else if(mode instanceof IntervalTimer) {
            String finIteration = "";
            format = new SimpleDateFormat("HHmmss");
            if(this.isEditMode == true) {
                finIteration = format.format(this.tempTime);
            } else {
                String zNum = "";
                int IterationLength = 0;
                data = format.format(LocaltoDate(((IntervalTimer) mode).loadIntervalTimer()));
                IterationLength = (((IntervalTimer)mode).getIteration() > 0) ? (int)(Math.log10(((IntervalTimer)mode).getIteration())+1) : 0;
                for(int i = 0; i < 8-IterationLength; i++){
                    zNum += "z";
                }
                String tmpString = (IterationLength == 0) ? "": String.valueOf(((IntervalTimer)mode).getIteration());
                if(IterationLength == 0){
                    finIteration = "zzzzzzz0";
                }else {
                    finIteration = zNum + tmpString;
                }
            }
            return finIteration+data;
        }
        return "zzzzzzzzzzzzzzzzz";
    }
    public int[] iconIdeal(){
        int[] icon = new int[6];
        String PM;
        LocalDateTime currentTime = modeManager.getTimekeeping().loadTime();
        SimpleDateFormat format = new SimpleDateFormat("a");
        PM = format.format(LocaltoDate(currentTime));
        if(PM.equals("PM"))
            icon[0] = 1;
        for(int i=0;i<5;i++){
            if(modeManager.loadSetMode()[i]==true){
                icon[i+1] = 2;
            }
        }
        if(isSetMode == true) {
            for(int i=0;i<5;i++){
                if(i == currentModeCursor && setMode[i] == true)
                    icon[i+1] = 2;
                else if(i == currentModeCursor && setMode[i] == false)
                    icon[i+1] = 1;
                else if(i != currentModeCursor && setMode[i] == false)
                    icon[i+1] = 0;
                else
                    icon[i+1] = 3;
            }

        } else {
            if(currentMode instanceof WatchTimer){
                icon[1]=1;
            }
            else if(currentMode instanceof StopWatch){
                icon[2]=1;
            }
            else if(currentMode instanceof Alarm){
                icon[3]=1;
            }
            else if(currentMode instanceof Dday){
                icon[4]=1;
            }
            else if(currentMode instanceof IntervalTimer) {
                icon[5]=1;
            }
        }
        return icon;
    }
    public Date LocaltoDate(LocalDateTime time){
        return Date.from(time.atZone(ZoneId.systemDefault()).toInstant());
    }

}