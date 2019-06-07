package Logic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Timer;
import java.time.LocalTime;

class StopWatchTest {

    private Timer m_timer = new Timer();
    StopWatch sw = new StopWatch(m_timer);

    @Test
    void activate() {
        sw.activate();
        assertTrue(sw.getActivated());
    }

    @Test
    void pause() {
        sw.pause();
        assertFalse(sw.getActivated());
    }

    @Test
    void reset() {
        sw.setActivated(false);
        sw.reset();
        assertFalse(sw.getActivated());
        assertEquals(sw.getCountDay(), 0);
        assertEquals(sw.getCurrentStopwatch(), LocalTime.of(0,0,0));
    }

    @Test
    void loadStopWatch(){
        LocalTime tmpTime = LocalTime.of(0,0,0);
        sw.setCurrentStopwatch(tmpTime);
        assertEquals(sw.loadStopWatch(), tmpTime);
    }


}