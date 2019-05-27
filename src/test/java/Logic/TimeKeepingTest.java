package Logic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Timer;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;

class TimeKeepingTest {
    Timer m_timer = new Timer();
    TimeKeeping tk = new TimeKeeping(m_timer);
    @Test
    void loadTime() {
        LocalTime initTime = LocalTime.of(0,0,0);
        LocalDateTime initDateTime = LocalDateTime.of(LocalDate.now(), initTime);
        tk.setCurrentTime(initDateTime);
        assertEquals(tk.loadTime(), initDateTime);
    }

    @Test
    void saveTime() {
        LocalTime tmpTime = LocalTime.of(0,0,0);
        LocalDateTime initDateTime = LocalDateTime.of(LocalDate.now(), tmpTime);
        tk.saveTime(initDateTime);
        assertEquals(tk.getCurrentTime(), initDateTime);
    }

    @Test
    void setHourformat() {
        tk.setHourformat();
        assertFalse(tk.getDisplayFormat());
    }
}