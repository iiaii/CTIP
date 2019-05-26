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
        LocalDateTime currentTime = tk.getCurrentTime();
        LocalDateTime loadTime = tk.loadTime();
        assertEquals(currentTime, loadTime);
    }

    @Test
    void saveTime() {
        LocalTime tmpTime = LocalTime.of(0,0,0);
        LocalDateTime initDateTime = LocalDateTime.of(tk.getCurrentTime().toLocalDate(), tmpTime);

        tk.saveTime(initDateTime);
        assertEquals(tk.getCurrentTime(), initDateTime);
    }

    @Test
    void setHourformat() {
        tk.setHourformat();
        assertEquals(tk.getDisplayFormat(), false);
    }
}