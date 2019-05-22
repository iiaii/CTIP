package Logic;
import java.time.LocalDateTime;

public class WatchSystem {
    private int currentCursor; //연도1 연도2 월 일 시 분 초
    private int currentDday;
    private LocalDateTime tempTime;
    private LocalDateTime tempTime2;
    private ModeManager modeManager;
    private TimeKeeping timekeeping;
    private WatchTimer watchTimer;
    private StopWatch stopwatch;
    private Alarm alarm;
    private Dday dday;
    private IntervalTimer intervaltimer;

    public LocalDateTime enterEditMode() {
        currentCursor=0;
        if(modeManager.getCurrentMode()==1 || modeManager.getCurrentMode()==3 || modeManager.getCurrentMode()==5)
            currentCursor = 4;
        tempTime = timekeeping.currentTime;
        tempTime2 = timekeeping.currentTime; //수정 더 필요
        return timekeeping.currentTime;
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
                if(currentDday==0) {
                    if(currentCursor==0)
                        tempTime.plusYears(100);
                    else if(currentCursor==1)
                        tempTime.plusYears(1);
                    else if(currentCursor==2)
                        tempTime.plusMonths(1);
                    else
                        tempTime.plusDays(1);
                }
                else {
                    if(currentCursor==0)
                        tempTime2.plusYears(100);
                    else if(currentCursor==1)
                        tempTime2.plusYears(1);
                    else if(currentCursor==2)
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
                currentCursor=(currentCursor+1)% 7;
                break;
            case 1: case 3: case 5: //timer, alarm, interval timer
                currentCursor = (currentCursor+1)%3 + 4;
                break;
            case 4: //dday
                currentCursor = (currentCursor+1) %4;
        }
        return currentCursor;
    }

    public void saveTime() {
        return;
    }

    public void pauseTimer() {
        watchTimer.pause();
    }

    public void resetTimer() {
        watchTimer.reset();
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
        currentDday = (currentDday+1)%2;
        if(currentDday==0)
            return tempTime;
        else
            return tempTime2;
    }

    public void saveDday() {
        dday.saveDday(tempTime.toLocalDate(), tempTime2.toLocalDate());
        return;
    }

    public LocalDateTime resetDday() {
        dday.reset();
        return dday.loadEndDday().atTime(0,0,0);
    }

    public long changeDdayFormat() {
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
        Dday dd = new Dday();
        dd.saveDday(null, LocalDateTime.of(2019,12,9,0,0,0));
        System.out.println(dd.getCalDday());

    }

}