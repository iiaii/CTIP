package Logic;

import java.util.LinkedList;
import java.util.Timer;

public class ModeManager {

    private int currentMode = 0;
    private boolean[] setMode = {false, false, false, false, false};


    private TimeKeeping timekeeping = null;
    private WatchTimer watchTimer = null;
    private StopWatch stopwatch = null;
    private Alarm alarm = null;
    private Dday dday = null;
    private IntervalTimer intervaltimer = null;

    private Timer m_timer;

    public void setCurrentMode(int currentMode) {
        this.currentMode = currentMode;
    }

    public boolean[] getSetMode() {
        return setMode;
    }

    public void setSetMode(boolean[] setMode) {
        this.setMode = setMode;
    }

    LinkedList<Object> modes = new LinkedList<Object>();

    public ModeManager(Timer m_timer) {
        this.currentMode = 0;
        this.m_timer = m_timer;
        this.timekeeping = new TimeKeeping(m_timer);
        modes.add(this.timekeeping);
    }

    public TimeKeeping getTimekeeping() {
        return timekeeping;
    }

    public void setTimekeeping(TimeKeeping timekeeping) {
        this.timekeeping = timekeeping;
    }

    public WatchTimer getWatchTimer() {
        return watchTimer;
    }

    public void setWatchTimer(WatchTimer watchTimer) {
        this.watchTimer = watchTimer;
    }

    public StopWatch getStopwatch() {
        return stopwatch;
    }

    public Alarm getAlarm() {
        return alarm;
    }

    public Dday getDday() {
        return dday;
    }

    public IntervalTimer getIntervaltimer() {
        return intervaltimer;
    }

    public Object getCurrentMode() {
        return modes.get(currentMode);
    }

    public LinkedList<Object> getModes() {
        return modes;
    }

    public Object getNextMode() {
        this.currentMode = (this.currentMode + 1) % 4;
        return this.modes.get(this.currentMode);
    }

    public boolean[] loadSetMode() {
        return this.setMode;
    }

    public WatchTimer createTimer() {
        if (!setMode[0]) {
            this.watchTimer = new WatchTimer(m_timer);
            this.setMode[0] = true;
        }
        if (modes.size() == 0) modes.add(this.timekeeping);
        modes.add(this.watchTimer);
        currentMode = 0;
        return this.watchTimer;
    }

    public void destoryTimer() {
        if (this.watchTimer != null) {
            this.watchTimer.cancel();
            modes.remove(this.watchTimer);
            this.watchTimer = null;
        }
        this.setMode[0] = false;
        currentMode = 0;
    }


    public StopWatch createStopwatch() {
        this.stopwatch = new StopWatch(m_timer);
        this.setMode[1] = true;
        if (modes.size() == 0) modes.add(this.timekeeping);
        modes.add(this.stopwatch);
        currentMode = 0;
        return this.stopwatch;
    }

    public void destroyStopwatch() {
        if (this.stopwatch != null) {
            this.stopwatch.cancel();
            modes.remove(this.stopwatch);
            this.stopwatch = null;

        }
        this.setMode[1] = false;
        currentMode = 0;
    }


    public Alarm createAlarm() {
        this.alarm = new Alarm(m_timer, timekeeping);
        this.setMode[2] = true;
        if (modes.size() == 0) modes.add(this.timekeeping);
        modes.add(this.alarm);
        currentMode = 0;
        return this.alarm;
    }

    public void destroyAlarm() {
        if (this.alarm != null) {
            for (int i = 0; i < 4; i++) {
                this.alarm.getAlarmTime(i).cancel();
            }
        }
        this.alarm = null;
        this.setMode[2] = false;
        currentMode = 0;
    }


    public Dday createDday() {
        this.dday = new Dday(timekeeping, m_timer);
        this.setMode[3] = true;
        if (modes.size() == 0) modes.add(this.timekeeping);
        modes.add(this.dday);
        currentMode = 0;
        return this.dday;
    }

    public void destroyDday() {
        if (this.dday != null) {
            this.dday.cancel();
            modes.remove(dday);
            this.dday = null;
        }
        this.setMode[3] = false;
        currentMode = 0;
    }


    public IntervalTimer createIntervalTimer() {
        this.intervaltimer = new IntervalTimer(m_timer);
        this.setMode[4] = true;
        if (modes.size() == 0) modes.add(this.timekeeping);
        modes.add(this.intervaltimer);
        currentMode = 0;
        return this.intervaltimer;
    }

    public void destroyIntervalTimer() {
        if (this.intervaltimer != null) {
            this.intervaltimer.cancel();
            modes.remove(intervaltimer);
            this.intervaltimer = null;
        }
        this.setMode[4] = false;
        currentMode = 0;
    }


}