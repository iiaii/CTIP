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
        assertEquals(sw.getActivated(), true);
    }

    @Test
    void pause() {
        sw.pause();
        assertEquals(sw.getActivated(), false);
    }

    @Test
    void reset() {
        sw.reset();
        assertEquals(sw.getActivated(), false);
        assertEquals(sw.getCountDay(), 0);
    }

    @Test
    void loadStopWatch(){
        LocalTime tmpTime = LocalTime.of(0,0,0);
        sw.setCurrentStopwatch(tmpTime);
        assertEquals(sw.loadStopWatch(), tmpTime);
    }


}