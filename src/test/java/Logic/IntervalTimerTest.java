package Logic;

import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Timer;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IntervalTimerTest {
//    private Timer m_timer = new Timer();
//    private IntervalTimer it = new IntervalTimer(m_timer);
    private Timer m_timer;
    private IntervalTimer it;
    @Test
    void enable() {
        assertEquals(it.getIsEnabled(), true);
    }

    @Test
    void disable() {
        assertEquals(it.getIsEnabled(), false);
    }

    @Test
    void reset() {

        LocalTime initTime = LocalTime.of(0,0,0);
        LocalDate initDate = LocalDate.of(0,1,1);

        LocalTime testTime = LocalTime.of(0,0,0);
        LocalDate testDate = LocalDate.of(0,2,1);

        LocalDateTime initDateTime = LocalDateTime.of(initDate, initTime);
        LocalDateTime testDateTime = LocalDateTime.of(testDate, testTime);

        it.setRemainedIntervalTimer(testDateTime);
        it.setSavedIntervalTimer(testDateTime);

//        it.reset();

//        assertEquals(it.getRemainedIntervalTimer(), initDateTime);
//        assertEquals(it.getSavedIntervalTimer(), initDateTime);


    }

    @Test
    void loadIntervalTimer() {
        LocalTime initTime = LocalTime.of(0,0,0);
        LocalDate initDate = LocalDate.of(0,1,1);
        LocalDateTime initDateTime = LocalDateTime.of(initDate, initTime);

        it.loadIntervalTimer();
        assertEquals(it.getRemainedIntervalTimer(), initDateTime);
    }

    @Test
    void saveIntervalTimer() {
        LocalTime initTime = LocalTime.of(0,0,0);
        LocalDate initDate = LocalDate.of(0,1,1);

        LocalDateTime initDateTime = LocalDateTime.of(initDate, initTime);

        it.saveIntervalTimer(initDateTime);
        assertEquals(it.getSavedIntervalTimer(), initDateTime);
    }

    @Test
    void ring() {
        m_timer = new Timer();
        it = new IntervalTimer(m_timer);
        SimpleDateFormat formatTime = new SimpleDateFormat("HHmmss");

        LocalTime initTime = LocalTime.of(0,0,0);
        LocalDate initDate = LocalDate.of(0,1,1);

        LocalDateTime initDateTime = LocalDateTime.of(initDate, initTime);
        int testIteration = 1;
        it.setSavedIntervalTimer(initDateTime);
        it.setRemainedIntervalTimer(initDateTime);
        it.setEnabled(true);

        it.ring();

        assertEquals(it.getRemainedIntervalTimer(),initDateTime.minusSeconds(1));

        it.setRemainedIntervalTimer(it.getRemainedIntervalTimer());

        assertEquals(it.getIteration(), testIteration);
//        assertTrue(Boolean.parseBoolean(formatTime.format(Date.from(it.getRemainedIntervalTimer().atZone(ZoneId.systemDefault()).toInstant()).equals("235959"))));
//
//        assertEquals(it.getIteration(), testIteration);
    }

}