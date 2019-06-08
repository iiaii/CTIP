package Logic;

import GUI.DigitalWatch;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Timer;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;

class WatchTimerTest {
    private Timer m_timer = new Timer();
    private TimeKeeping timeKeeping;

    TimeKeeping tk = new TimeKeeping(m_timer);
    WatchTimer wt = new WatchTimer(m_timer);
    @Test
    void activate() {
        LocalTime tmpTime = LocalTime.of(0,0,0);
        LocalDateTime initDateTime = LocalDateTime.of(LocalDate.now(), tmpTime);
        wt.setRemainedTimer(initDateTime);
        wt.activate();
        assertFalse(wt.getActived());
    }

    @Test
    void pause() {
        wt.pause();
        assertFalse(wt.getActived());
    }

    @Test
    void reset() {
        LocalTime tmpTime = LocalTime.of(0,0,0);
        LocalDateTime initDateTime = LocalDateTime.of(LocalDate.now(), tmpTime);
        wt.reset();
        assertEquals(wt.getRemainedTimer(), initDateTime);
        assertEquals(wt.getSavedTimer(), initDateTime);
    }

    @Test
    void loadTimer() {
        LocalTime tmpTime = LocalTime.of(0,0,0);
        LocalDateTime initDateTime = LocalDateTime.of(LocalDate.now(), tmpTime);
        wt.setRemainedTimer(initDateTime);
        LocalDateTime getRemainedTime = wt.getRemainedTimer();
        assertEquals(getRemainedTime, initDateTime);
    }

    @Test
    void saveTimer() {
        LocalTime tmpTime = LocalTime.of(0,0,0);
        LocalDateTime initDateTime = LocalDateTime.of(tk.getCurrentTime().toLocalDate(), tmpTime);
        wt.saveTimer(initDateTime);
        assertEquals(wt.getSavedTimer(), initDateTime);
        assertEquals(wt.getRemainedTimer(), initDateTime);
        assertFalse(wt.getActived());
    }

    @Disabled
    @Test
    void ring() {
        wt.ring();
        assertTrue(DigitalWatch.getInstance().getBell().isPlaying());
    }
}