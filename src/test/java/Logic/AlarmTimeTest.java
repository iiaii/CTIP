package Logic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.Timer;
import java.time.LocalTime;
import java.time.LocalDate;
import java.time.LocalDateTime;
class AlarmTimeTest {
    Timer m_timer = new Timer();
    TimeKeeping tk = new TimeKeeping(m_timer);
    AlarmTime artm  = new AlarmTime(m_timer, tk);

    @Test
    void loadAlarmData() {
        artm.setCurrentAlarm(LocalDateTime.now());
        assertEquals(artm.getCurrentAlarm(), LocalDateTime.now());
    }

    @Test
    void saveAlarmData() {
        artm.saveAlarmData(LocalDateTime.now());
        assertEquals(artm.getCurrentAlarm(), LocalDateTime.now());
    }

    @Test
    void enable() {
        artm.enable();
        assertTrue(artm.getEnabled());
    }

    @Test
    void disable() {
        artm.disable();
        assertFalse(artm.getEnabled());
    }

    @Test
    void ring(){
        artm.ring();
        assertFalse(artm.getEnabled());
    }
}