package Logic;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import GUI.DigitalWatch;
import java.util.Timer;
import java.time.LocalTime;
import java.time.LocalDate;
import java.time.LocalDateTime;
class DdayTest {

    private Timer m_timer = new Timer();
    private TimeKeeping tk = new TimeKeeping(m_timer);
    private Dday d = new Dday(tk, m_timer);

    @Test
    void loadStartDday() {
        LocalTime initTime = LocalTime.of(0,0,0);
        LocalDateTime initDateTime = LocalDateTime.of(tk.getCurrentTime().toLocalDate(), initTime);
        d.setStartDday(initDateTime);
        d.loadStartDday();
        assertEquals(d.getStartDday(), initDateTime);
    }

    @Test
    void loadEndDday() {
        LocalTime initTime = LocalTime.of(0,0,0);
        LocalDateTime initDateTime = LocalDateTime.of(tk.getCurrentTime().toLocalDate(), initTime);
        d.setEndDday(initDateTime);
        d.loadEndDday();
        assertEquals(d.getEndDday(), initDateTime);
    }


    @Test
    void reset() {
        LocalDateTime initDateTime = LocalDateTime.of(tk.getCurrentTime().toLocalDate(), LocalTime.of(0,0,0));
        d.setCurrentDay(tk.getCurrentTime());
        d.reset();
        assertEquals(d.getStartDday(), initDateTime);
        assertEquals(d.getEndDday(), initDateTime);
        assertFalse(d.getExistStartDday());
        assertFalse(d.getExistEndDday());
        assertTrue(d.getDisplayType());
    }

    @Test
    void changeFormat() {
        d.setDisplayType(true);
        d.setExistStartDday(false);
        d.changeFormat();
        assertTrue(d.getDisplayType());

        d.setDisplayType(true);
        d.setExistStartDday(true);
        d.changeFormat();
        assertFalse(d.getDisplayType());
    }

    @Disabled
    @Test
    void ring(){
        d.ring();
        assertTrue(DigitalWatch.getInstance().getBell().isPlaying());
    }
}