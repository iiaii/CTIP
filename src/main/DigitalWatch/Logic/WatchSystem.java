package Logic;
import java.time.LocalDateTime;

import java.util.Timer;
import java.util.TimerTask;

public class WatchSystem {
    public LocalDateTime tempTime;
    public LocalDateTime tempTime2;
    public ModeManager modeManager;

    public LocalDateTime enterEditMode() {
        return null;
    }

    public LocalDateTime increaseData() {
        return null;
    }

    public int changeCursor() {
        return 0;
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
        modeManager.getWatchTimer().saveTimer(data);
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

        return null;
    }

    public void saveDday() {

        return;
    }

    public LocalDateTime resetDday() {

        return null;
    }

    public int changeDdayFormat() {

        return 0;
    }

    public void activateStopwatch() {

        return;
    }

    public void pauseStopwatch() {

        return;
    }

    public void resetStopwatch() {
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
    public void activateTimer(){
        modeManager.getWatchTimer().activate();
    }

    public static void main(String[]args) throws Exception {
//        WatchTimer wt  = new WatchTimer();
//        wt.activate();

    }
}