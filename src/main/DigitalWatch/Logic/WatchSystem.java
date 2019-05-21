package Logic;
public class WatchSystem {
    private Time tempTime;
    private Time tempTime2;
    public ModeManager modeManager;
    private int currnentCussor; //추가

    private TimeKeeping timekeeping;
    private Timer timer;
    private StopWatch stopwatch;
    private Alarm alarm;
    private Dday dday;
    private IntervalTimer intervaltimer;

    public Time enterEditMode() {
        return null;
    }
    public Time increaseData() {
        if(modeManager.getCurrentMode()==4) {
            tempTime = timekeeping.currentTime;
            tempTime2 = timekeeping.currentTime;
            this.tempTime.setYear(tempTime.getYear()+1);
        }
        return tempTime;
    }
    public int changeCursor() {
        return 0;
    }
    public void saveTime() {
        return ;
    }
    public void pauseTimer() {
        return ;
    }
    public void resetTimer() {
        return ;
    }
    public void saveTimer() {
        return ;
    }
    public void enablentervalTimer() {
        return ;
    }
    public void disableIntervalTimer() {
        
        return ;
    }
    public void saveIntervalTimer() {
        
        return ;
    }
    public void resetIntervalTimer() {
        
        return ;
    }

    public void saveAlarm() {
        
        return ;
    }
    public void resetAlarm() {
        
        return ;
    }
    public void enableAlarm() {
        
        return ;
    }
    public void disableAlarm() {
        
        return ;
    }
    public AlarmTime changeAlarmPage() {
        
        return null;
    }
    public Time changePage() {
        
        return null;
    }
    public void saveDday() {
        dday.saveDday(tempTime, tempTime2);
        return;
    }
    public Time resetDday() {
        dday.reset();
        return dday.getEndDday();
    }
    public int changeDdayFormat() {
        dday.changeFormat(timekeeping.currentTime);
        return 0;
    }
    public void activateStopwatch() {
        
        return ;
    }
    public void pauseStopwatch() {
        
        return ;
    }
    public void resetStopwatch() {
        return ;
    }
    public int changeMode() {
        return 0;
    }
    public void chooseModes() {
        
        return ;
    }
    public void saveMode() {
        
        return ;
    }
    public Time changeHourFormat() {
        
        return null;
    }
    public void muteBeep() {
        
        return ;
    }
    public int enterSetMode() {
        
        return 0;
    }
}