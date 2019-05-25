package Logic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
        LocalDateTime initDateTime = LocalDateTime.of(tk.getCurrentTime().toLocalDate(), tk.getCurrentTime().toLocalTime());
        d.setCurrentDay(tk.getCurrentTime());
        d.reset();
        assertEquals(d.getStartDday(), initDateTime);
        assertEquals(d.getEndDday(), initDateTime);
    }

    @Test
    void changeFormat() {
        d.setDisplayType(true);
        d.changeFormat();
        assertEquals(d.getDisplayType(), false);
    }
}