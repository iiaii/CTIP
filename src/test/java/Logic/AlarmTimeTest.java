package Logic;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Timer;

import static org.junit.jupiter.api.Assertions.*;
class AlarmTimeTest {
    Timer m_timer = new Timer();
    TimeKeeping tk = new TimeKeeping(m_timer);
    AlarmTime artm  = new AlarmTime(m_timer, tk);

    @Test
    void loadAlarmData() {

        LocalDateTime tmpDateTime = LocalDateTime.now();
        artm.setCurrentAlarm(tmpDateTime);
        assertEquals(artm.getCurrentAlarm(), tmpDateTime);
    }

    @Test
    void saveAlarmData() {
        LocalDateTime tmpDateTime = LocalDateTime.now();
        artm.saveAlarmData(tmpDateTime);
        assertEquals(artm.getCurrentAlarm(), tmpDateTime);
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

    @Disabled
    @Test
    void ring(){
        artm.ring();
        assertFalse(artm.getEnabled());
    }
}