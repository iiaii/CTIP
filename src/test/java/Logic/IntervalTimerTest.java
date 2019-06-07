package Logic;

import GUI.DigitalWatch;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Timer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IntervalTimerTest {
//    private Timer m_timer = new Timer();
//    private IntervalTimer it = new IntervalTimer(m_timer);
    private Timer m_timer = new Timer();
    private IntervalTimer it = new IntervalTimer(m_timer);
    @Test
    void enable() {
        LocalTime tmpTime = LocalTime.of(0,0,0);
        LocalTime tmpTime2 = LocalTime.of(0,10,0);
        LocalDateTime initDateTime = LocalDateTime.of(LocalDate.now(), tmpTime);
        LocalDateTime initDateTime2 = LocalDateTime.of(LocalDate.now(), tmpTime2);
        it.setSavedIntervalTimer(initDateTime2);
        it.enable();
        assertTrue(it.getIsEnabled());

        it.setRemainedIntervalTimer(initDateTime);
        it.enable();
        assertEquals(it.getRemainedIntervalTimer(), it.getSavedIntervalTimer());
    }

    @Test
    void disable() {
        it.setEnabled(true);
        it.disable();
        assertFalse(it.getIsEnabled());
    }

    @Test
    void loadIntervalTimer() {
        LocalTime initTime = LocalTime.of(0,0,0);
        LocalDate initDate = LocalDate.of(0,1,1);
        LocalDateTime initDateTime = LocalDateTime.of(initDate, initTime);
        it.setRemainedIntervalTimer(initDateTime);
        assertEquals(it.loadIntervalTimer(), initDateTime);
    }

    @Test
    void saveIntervalTimer() {
        LocalTime initTime = LocalTime.of(0,0,0);
        LocalDate initDate = LocalDate.of(0,1,1);
        LocalDateTime initDateTime = LocalDateTime.of(initDate, initTime);

        it.setEnabled(false);
        it.saveIntervalTimer(initDateTime);
        assertEquals(it.getSavedIntervalTimer(), initDateTime);
        assertEquals(it.getRemainedIntervalTimer(), initDateTime);
        assertEquals(it.getIteration(), 0);
    }

    @Disabled
    @Test
    void ring() {
        it.ring();
        assertTrue(DigitalWatch.getInstance().getBell().isPlaying());
    }

}