package Logic;

import GUI.DigitalWatch;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Timer;

class WatchSystemTest {
    Timer m_timer = new Timer();
    WatchSystem ws = new WatchSystem(m_timer);
    TimeKeeping tk = new TimeKeeping(m_timer);
    WatchTimer wt = new WatchTimer(m_timer);
    IntervalTimer it = new IntervalTimer(m_timer);
    StopWatch sw = new StopWatch(m_timer);
    Dday d = new Dday(tk, m_timer);
    Alarm alarm = new Alarm(m_timer, tk);
    ModeManager mM = new ModeManager(m_timer);
    @Test
    void enterEditMode() {
        ws.setCurrentMode(wt);
        wt.setActived(true);
        assertFalse(ws.getEdited());
        LocalDateTime tmpDateTime = LocalDateTime.of(LocalDate.now(), LocalTime.of(0,0,0));
        wt.setRemainedTimer(tmpDateTime);
        ws.enterEditMode();
        assertEquals(ws.getTempTime(), tmpDateTime);
        assertEquals(ws.getCurrentCursor(), 4);
    }

    @Test
    void increaseData() {
        LocalTime tmpTime = LocalTime.of(0,0,0);
        LocalDateTime initDateTime = LocalDateTime.of(tk.getCurrentTime().toLocalDate(), tmpTime);
        ws.setCurrentCursor(0);
        ws.setCurrentDdayPage(0);
        ws.setTempTime(initDateTime);
        ws.increaseData();
        assertEquals(initDateTime.plusYears(100), ws.getTempTime());
        ws.setCurrentDdayPage(1);
        ws.setTempTime2(initDateTime);
        ws.increaseData();
        assertEquals(initDateTime.plusYears(100), ws.getTempTime2());
    }

    @Test
    void changeCursor() {
        ws.setIsSetMode(false);
        ws.setCurrentMode(tk);
        ws.setCurrentCursor(0);
        ws.changeCursor();
        assertEquals(ws.getCurrentCursor(), 1);
    }

    @Test
    void saveTime() {
        LocalTime tmpTime = LocalTime.of(0,0,0);
        LocalDateTime initDateTime = LocalDateTime.of(tk.getCurrentTime().toLocalDate(), tmpTime);
        ws.setTempTime(initDateTime);
        ws.setCurrentMode(tk);
        ws.saveTime();
        assertEquals(tk.getCurrentTime(), initDateTime);
    }

    @Test
    void changeHourFormat() {
        ws.setCurrentMode(tk);
        ws.changeHourFormat();
        assertEquals(tk.getDisplayFormat(), false);
    }

    @Test
    void pauseTimer() {
        ws.setCurrentMode(wt);
        ws.pauseTimer();
        assertEquals(wt.getActived(), false);
    }

    @Test
    void resetTimer() {
        LocalTime tmpTime = LocalTime.of(0,0,0);
        LocalDateTime initDateTime = LocalDateTime.of(LocalDate.now(), tmpTime);
        ws.setCurrentMode(wt);
        wt.setActived(false);
        ws.resetTimer();

        assertEquals(wt.getSavedTimer(), initDateTime);
        assertEquals(wt.getRemainedTimer(), initDateTime);
    }

    @Test
    void saveTimer() {
        LocalTime tmpTime = LocalTime.of(0,0,0);
        LocalDateTime initDateTime = LocalDateTime.of(LocalDate.now(), tmpTime);
        ws.setCurrentMode(wt);
        ws.setTempTime(initDateTime);
        ws.saveTimer();
        assertEquals(wt.getSavedTimer(), initDateTime);
        assertEquals(wt.getRemainedTimer(), initDateTime);
        assertEquals(wt.getActived(), false);
    }

    @Test
    void enableIntervalTimer() {
        ws.setCurrentMode(it);

        LocalTime tmpTime = LocalTime.of(0,0,0);
        LocalTime tmpTime2 = LocalTime.of(0,10,0);
        LocalDateTime initDateTime = LocalDateTime.of(LocalDate.now(), tmpTime);
        LocalDateTime initDateTime2 = LocalDateTime.of(LocalDate.now(), tmpTime2);
        it.setSavedIntervalTimer(initDateTime2);
        it.setRemainedIntervalTimer(initDateTime);

        ws.enableIntervalTimer();

        assertTrue(it.getIsEnabled());
        assertEquals(it.getRemainedIntervalTimer(), it.getSavedIntervalTimer());

    }

    @Test
    void disableIntervalTimer() {
        ws.setCurrentMode(it);
        ws.disableIntervalTimer();
        assertEquals(it.getIsEnabled(), false);

    }

    @Test
    void saveIntervalTimer() {
        LocalTime tmpTime = LocalTime.of(0,0,0);
        LocalDateTime initDateTime = LocalDateTime.of(LocalDate.now(), tmpTime);
        ws.setTempTime(initDateTime);
        ws.setCurrentMode(it);
        ws.saveIntervalTimer();
        it.setEnabled(false);
        assertEquals(it.getSavedIntervalTimer(), initDateTime);
    }

    @Test
    void resetIntervalTimer() {
        LocalTime tmpTime = LocalTime.of(0,0,0);
        LocalDateTime initDateTime = LocalDateTime.of(LocalDate.now(), tmpTime);
        ws.setCurrentMode(it);
        it.setEnabled(false);
        ws.resetIntervalTimer();
        assertEquals(it.getSavedIntervalTimer(), initDateTime);
        assertEquals(it.getRemainedIntervalTimer(), initDateTime);
        assertEquals(it.getIteration(), 0);
    }

    @Test
    void saveAlarm() {
        ws.setCurrentMode(alarm);
        ws.setCurrentAlarmPage(0);
        LocalTime tmpTime = LocalTime.of(0,0,0);
        LocalDateTime initDateTime = LocalDateTime.of(LocalDate.now(), tmpTime);
        ws.setTempTime(initDateTime);
        ws.saveAlarm();
        assertEquals(alarm.getAlarmTime(0).getCurrentAlarm(), initDateTime);
    }

    @Test
    void resetAlarm() {
        LocalDateTime tempTime = LocalDateTime.of(LocalDate.now(), LocalTime.of(0,0,0));
        ws.setCurrentMode(alarm);
        ws.setCurrentAlarmPage(0);
        ws.setTempTime(tempTime);
        alarm.getAlarmTime(0).setCurrentAlarm(tempTime);
        ws.resetAlarm();
        assertEquals(ws.getModeManager().getAlarm().getAlarmTime(0).getCurrentAlarm(), tempTime);
    }

    @Test
    void enableAlarm() {
        ws.setCurrentMode(alarm);
        ws.setCurrentAlarmPage(0);
        ws.enableAlarm();
        assertEquals(alarm.getAlarmTime(0).getEnabled(), true);
    }

    @Test
    void disableAlarm() {
        ws.setCurrentMode(alarm);
        ws.setCurrentAlarmPage(0);
        ws.disableAlarm();
        assertEquals(alarm.getAlarmTime(0).getEnabled(), false);

    }

    @Test
    void changeAlarmPage() {
        ws.setCurrentMode(alarm);
        ws.setCurrentAlarmPage(3);
        ws.changeAlarmPage();
        assertEquals(0, ws.getCurrentAlarmPage());

    }

    @Test
    void changePage() {
        ws.setCurrentDdayPage(0);
        ws.changePage();
        assertEquals(ws.getCurrentDdayPage(), 1);
        ws.setCurrentDdayPage(1);
        ws.changePage();
        assertEquals(ws.getCurrentDdayPage(), 0);
    }

    @Test
    void saveDday() {
        ws.setCurrentMode(d);
        ws.setEdited(false);
        LocalDateTime tmpDateTime= d.getStartDday();
        ws.saveDday();
        assertEquals(d.getStartDday(), tmpDateTime);
        LocalTime tmpTime = LocalTime.of(0,0,0);
        LocalDateTime initDateTime = LocalDateTime.of(LocalDate.of(2019,5,26), tmpTime);
        LocalDateTime initDateTime2 = LocalDateTime.of(LocalDate.of(2019,5,27), tmpTime);
        ws.setTempTime(initDateTime);
        ws.setTempTime2(initDateTime2);
        ws.setEdited(true);
        ws.saveDday();
        assertEquals(d.getStartDday(), initDateTime);
        assertEquals(d.getEndDday(), initDateTime2);
        assertEquals(d.getExistStartDday(), true);
    }

    @Test
    void resetDday() {
        ws.setCurrentMode(d);

        LocalTime tmpTime = LocalTime.of(0,0,0);
        LocalDateTime initDateTime = LocalDateTime.of(LocalDate.of(2019,5,26), tmpTime);

        d.setEndDday(initDateTime);
        d.setCurrentDay(initDateTime);

        assertEquals(ws.resetDday(), d.getEndDday());
        assertFalse(d.getExistStartDday());
        assertEquals(d.getStartDday(), initDateTime);
        assertEquals(d.getEndDday(), initDateTime);
    }

    @Test
    void changeDdayFormat() {
        ws.setCurrentMode(d);
        d.setExistStartDday(false);
        d.setCalDday(10);
        assertEquals(ws.changeDdayFormat(), d.getCalDday());
        assertEquals(d.getDisplayType(), true);
    }


    @Test
    void activateStopwatch() {
        ws.setCurrentMode(sw);
        ws.activateStopwatch();
        assertTrue(sw.getActivated());
    }

    @Test
    void pauseStopwatch() {
        ws.setCurrentMode(sw);
        ws.pauseStopwatch();
        assertFalse(sw.getActivated());
    }

    @Test
    void resetStopwatch() {
        ws.setCurrentMode(sw);
        sw.setActivated(false);
        ws.resetStopwatch();
        assertEquals(sw.getCurrentStopwatch(), LocalTime.of(0,0,0));
        assertEquals(sw.getActivated(), false);
        assertEquals(sw.getCountDay(), 0);
    }

    @Test
    void chooseModes() {
        ws.setCurrentCursor(0);
        Boolean[] tmp = {true,true,false,false,true};
        ws.setSetMode(tmp);
        ws.chooseModes();
        assertFalse(ws.getSetMode()[0]);
    }

    @Test
    void saveMode() {
        Boolean[] tmp = {true,true,false,false,true};
        ws.setSetMode(tmp);
        ws.saveMode();
        assertNotNull(ws.getModeManager().getWatchTimer());
        assertNotNull(ws.getModeManager().getStopwatch());
        assertNotNull(ws.getModeManager().getIntervaltimer());
    }
    @Test
    void exitSetMode(){
        assertFalse(ws.getIsSetMode());
        assertEquals(ws.getCurrentModeCursor(), 0);
        ws.setCurrentMode(ws.getModeManager().getWatchTimer());
        ws.enterSetMode();
        ws.exitSetMode();
        assertEquals(ws.getCurrentMode(), ws.getModeManager().getTimekeeping());
    }

    @Test
    void exitEditMode(){
        assertFalse(ws.getEdited());
        assertEquals(ws.getCurrentDdayPage(),0);
    }

    @Ignore
    void muteBeep() {
        ws.muteBeep();
        assertFalse(DigitalWatch.getInstance().getBell().isPlaying());
    }

    @Test
    void enterSetMode() {
        ws.setCurrentMode(ws.getModeManager().getTimekeeping());
        ws.enterSetMode();
        assertTrue(ws.getIsSetMode());
        ws.exitSetMode();
        ws.setCurrentMode(ws.getModeManager().getStopwatch());
        ws.enterSetMode();
        assertTrue(ws.getIsSetMode());
    }

    @Test
    void activateTimer() {
        ws.setCurrentMode(wt);

        LocalTime tmpTime = LocalTime.of(0,0,0);
        LocalDateTime initDateTime = LocalDateTime.of(LocalDate.of(2019,5,26), tmpTime);

        wt.setRemainedTimer(initDateTime);
        ws.activateTimer();
        assertFalse(wt.getActived());
    }


    @Test
    void changeMode() {
        Boolean[] tmpSetMode = {true, true, true, false, false};
        ws.setSetMode(tmpSetMode);
        ws.saveMode();
        Object nextMode = ws.getModeManager().getWatchTimer();
        ws.changeMode();
        assertEquals(ws.getCurrentMode(), nextMode);
        assertEquals(ws.getCurrentDdayPage(), 0);
        assertEquals(ws.getCurrentAlarmPage(), 0);
        assertFalse(ws.getEdited());
    }
}