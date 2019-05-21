package Logic;
import java.time.LocalDateTime;
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
        modeManager.watchTimer.pause();
    }

    public void resetTimer() {
        modeManager.watchTimer.reset();
    }

    public void saveTimer(LocalDateTime data) {
        WatchTimer wt = new WatchTimer(data);
    }

    public void enablentervalTimer() {
        return;
    }

    public void disableIntervalTimer() {

        return;
    }

    public void saveIntervalTimer() {

        return;
    }

    public void resetIntervalTimer() {

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
        modeManager.watchTimer.activate();
    }

    public static void main(String[]args) throws Exception {
//        WatchTimer wt  = new WatchTimer();
//        wt.activate();

    }

}