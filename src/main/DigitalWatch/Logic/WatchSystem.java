package Logic;

import java.util.Timer;
import java.util.TimerTask;

public class WatchSystem {
    public Time tempTime;
    public Time tempTime2;
    public ModeManager modeManager;


    public Time enterEditMode() {
        return null;
    }
    public Time increaseData() {
        return null;
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
        
        return ;
    }
    public Time resetDday() {
        
        return null;
    }
    public int changeDdayFormat() {
        
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
    public static void main(String[] args){
        Timer m_timer = new Timer();
        TimeKeeping timeKeeping = new TimeKeeping(m_timer);
        WatchTimer watchTimer = new WatchTimer(m_timer);
        watchTimer.activate();
        //m_timer.schedule(watchTimer,0,2000);

    }
}