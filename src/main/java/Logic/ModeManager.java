package Logic;

import java.util.LinkedList;
import java.util.Timer;

public class ModeManager {

    private int currentMode = 0;
    public Boolean[] setMode = {false,false,false,false,false}; // watchTimer,stopwatch,alarm,dday,intervaltimer

    private TimeKeeping timekeeping=null;
    private WatchTimer watchTimer=null;
    private StopWatch stopwatch=null;
    private Alarm alarm=null;
    private Dday dday=null;
    private IntervalTimer intervaltimer=null;

    private Timer m_timer;

    LinkedList<Object> modes  = new LinkedList<Object>();

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

    public void setStopwatch(StopWatch stopwatch) {
        this.stopwatch = stopwatch;
    }

    public Alarm getAlarm() {
        return alarm;
    }

    public void setAlarm(Alarm alarm) {
        this.alarm = alarm;
    }

    public Dday getDday() {
        return dday;
    }

    public void setDday(Dday dday) {
        this.dday = dday;
    }

    public IntervalTimer getIntervaltimer() {
        return intervaltimer;
    }

    public void setIntervaltimer(IntervalTimer intervaltimer) {
        this.intervaltimer = intervaltimer;
    }


    public int getCurrentModeIndex() {
        return this.currentMode;
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

    public Boolean[] loadSetMode() {
        return this.setMode;
    }


    public int toggleMode(int modeNume) {
        if(setMode[modeNume]==true)
            setMode[modeNume] = false;
        else
            setMode[modeNume] = true;
        return 0;
    }

    public WatchTimer createTimer() {
        if(!setMode[0]){
            this.watchTimer = new WatchTimer(m_timer, this.timekeeping);
            this.setMode[0] = true;
        }
        if(modes.size() == 0) modes.add(this.timekeeping);
        modes.add(this.watchTimer);
        return this.watchTimer;
    }

    public void destoryTimer() {
        if(this.watchTimer != null) {
            this.watchTimer.reset();
            modes.remove(this.watchTimer);
            this.watchTimer = null;
        }
        this.setMode[0] = false;
    }


    public StopWatch createStopwatch() {
        this.stopwatch = new StopWatch(m_timer);
        this.setMode[1] = true;
        if(modes.size() == 0) modes.add(this.timekeeping);
        modes.add(this.stopwatch);
        // TODO implement here
        return this.stopwatch;
    }

    public void destroyStopwatch() {
        if(this.stopwatch != null) {
            this.stopwatch.reset();
            modes.remove(this.stopwatch);
            this.stopwatch = null;
        }
        this.setMode[1] = false;
    }


    public Alarm createAlarm() {
        this.alarm = new Alarm(m_timer,timekeeping);
        this.setMode[2] = true;
        if(modes.size() == 0) modes.add(this.timekeeping);
        modes.add(this.alarm);
        return this.alarm;
    }

    public void destroyAlarm() {
        if(this.alarm != null) {
            for(int i=0;i<4;i++){
                    this.alarm.disableAlarm(i);
            }
        }
        this.alarm = null;
        this.setMode[2] = false;
    }


    public Dday createDday() {
        this.dday = new Dday(timekeeping, m_timer);
        this.setMode[3] = true;
        if(modes.size() == 0) modes.add(this.timekeeping);
        modes.add(this.dday);
        return this.dday;
    }

    public void destroyDday() {
        if(this.dday != null) {
            this.dday.reset();
            modes.remove(dday);
            this.dday = null;
        }
        this.setMode[3] = false;
    }


    public IntervalTimer createIntervalTimer() {
        this.intervaltimer = new IntervalTimer(m_timer);
        this.setMode[4] = true;
        if(modes.size() == 0) modes.add(this.timekeeping);
        modes.add(this.intervaltimer);
        return this.intervaltimer;
    }

    public void destroyIntervalTimer() {
        if(this.intervaltimer != null) {
            this.intervaltimer.disable();
            modes.remove(intervaltimer);
            this.intervaltimer = null;
        }
        this.setMode[4] = false;
    }


}