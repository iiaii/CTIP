package Logic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Time;
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
        LocalDateTime loadAlarmpage = alarm.loadAlarm(0);
        LocalTime tmpTime = LocalTime.of(0,0,0);
        assertEquals(loadAlarmpage.toLocalTime(), tmpTime);
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
        assertEquals(alarm.getAlarmTime(0).getEnabled(), true);
    }

    @Test
    void disableAlarm() {
        alarm.disableAlarm(0);
        assertEquals(alarm.getAlarmTime(0).getEnabled(), false);
    }
}