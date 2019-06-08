package Logic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;
import java.util.Timer;
class ModeManagerTest {
    Timer m_timer = new Timer();
    private TimeKeeping tk = new TimeKeeping(m_timer);
    private WatchSystem ws = new WatchSystem(m_timer);
    private ModeManager mM = ws.getModeManager();

    @Test
    void getCurrentMode() {
        mM.setCurrentMode(0);
        assertEquals(mM.getCurrentMode(), mM.getModes().get(0));
    }

    @Test
    void getNextMode() {
        boolean[] tmpSetMode = {true, true, true, false, false};
        ws.setSetMode(tmpSetMode);
        ws.saveMode();
        mM.setCurrentMode(0);
        Object nextMode = mM.getNextMode();
        assertEquals(nextMode, mM.getCurrentMode());
    }

    @Test
    void loadSetMode() {
        boolean[] tmpSetMode = {true, true, true, false, false};
        mM.setSetMode(tmpSetMode);
        assertEquals(mM.loadSetMode(), tmpSetMode);
    }

    @Test
    void createTimer() {
        WatchTimer ctWatchTimer = mM.createTimer();
        assertNotNull(ctWatchTimer);
    }

    @Test
    void destoryTimer() {
        mM.destoryTimer();
        assertNull(mM.getWatchTimer());
    }

    @Test
    void createStopwatch() {
        StopWatch stopwatch = mM.createStopwatch();
        assertNotNull(stopwatch);
    }

    @Test
    void destroyStopwatch() {
        mM.destroyStopwatch();
        assertNull(mM.getStopwatch());
    }

    @Test
    void createAlarm() {
        Alarm alarm = mM.createAlarm();
        assertNotNull(alarm);
    }

    @Test
    void destroyAlarm() {
        mM.destroyAlarm();
        assertNull(mM.getAlarm());
    }

    @Test
    void createDday() {
        Dday dday = mM.createDday();
        assertNotNull(dday);
    }

    @Test
    void destroyDday() {
        mM.destroyDday();
        assertNull(mM.getDday());
    }

    @Test
    void createIntervalTimer() {
        IntervalTimer it = mM.createIntervalTimer();
        assertNotNull(it);
    }

    @Test
    void destroyIntervalTimer() {
        mM.destroyIntervalTimer();
        assertNull(mM.getIntervaltimer());
    }
}