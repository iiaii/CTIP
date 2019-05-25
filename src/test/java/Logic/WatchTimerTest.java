package Logic;

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
    WatchTimer wt = new WatchTimer(m_timer, tk);

    @Test
    void activate() {
        wt.activate();
        assertEquals(wt.getActived(), true);
    }

    @Test
    void pause() {
        wt.pause();
        assertEquals(wt.getActived(), false);
    }

    @Test
    void reset() {
        LocalTime tmpTime = LocalTime.of(0,0,0);
        LocalDateTime initDateTime = LocalDateTime.of(tk.getCurrentTime().toLocalDate(), tmpTime);
        wt.reset();
        assertEquals(wt.getRemainedTimer(), initDateTime);
        assertEquals(wt.getSavedTimer(), initDateTime);
    }

    @Test
    void loadTimer() {
        LocalDateTime loadDateTime = wt.loadTimer();
        LocalDateTime getRemainedTime = wt.getRemainedTimer();
        assertEquals(loadDateTime, getRemainedTime);
    }

    @Test
    void saveTimer() {
        LocalTime tmpTime = LocalTime.of(0,0,0);
        LocalDateTime initDateTime = LocalDateTime.of(tk.getCurrentTime().toLocalDate(), tmpTime);
        wt.saveTimer(initDateTime);
        assertEquals(wt.getSavedTimer(), initDateTime);
    }

    @Test
    void ring() {
        LocalTime tmpTime = LocalTime.of(0,0,0);
        LocalDateTime initDateTime = LocalDateTime.of(tk.getCurrentTime().toLocalDate(), tmpTime);
        wt.setRemainedTimer(initDateTime);
        wt.ring();
        assertEquals(wt.getActived(), false);
    }
}