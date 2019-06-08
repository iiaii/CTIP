package Logic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Timer;
class AlarmTest {
    private Timer m_timer = new Timer();
    private TimeKeeping timeKeeping = new TimeKeeping(m_timer);
    private Alarm alarm = new Alarm(m_timer, timeKeeping);
    @Test
    void loadAlarm() {
        LocalTime tmpTime = LocalTime.of(0,0,0);
        alarm.getAlarmTime(0).setCurrentAlarm(LocalDateTime.of(LocalDate.now(), tmpTime));
        assertEquals(alarm.getAlarmTime(0).getCurrentAlarm(), LocalDateTime.of(LocalDate.now(),tmpTime));
    }

    @Test
    void saveAlarm() {
        LocalDateTime ldt = LocalDateTime.now();
        alarm.saveAlarm(0, ldt);
        assertEquals(alarm.getAlarmTime(0).getCurrentAlarm(), ldt);

    }

    @Test
    void enableAlarm() {
        alarm.enableAlarm(0);
        assertTrue(alarm.getAlarmTime(0).getEnabled());
    }

    @Test
    void disableAlarm() {
        alarm.disableAlarm(0);
        assertFalse(alarm.getAlarmTime(0).getEnabled());
    }
}