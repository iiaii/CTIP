package Logic;

import GUI.ButtonEvent;

public class ModeManager {

    public int currentMode;
    public Boolean[] setMode = new Boolean[5]; // timer,stopwatch,alarm,dday,intervaltimer
    public TimeKeeping timekeeping;
    public WatchTimer timer;
    public StopWatch stopwatch;
    public Alarm alarm;
    public Dday dday;
    public IntervalTimer intervaltimer;

    public ModeManager() {
        this.timekeeping = new TimeKeeping();
    }

    public int getNextMode() {
        this.currentMode = (this.currentMode++) % 4;
        return this.currentMode;
    }

    public Boolean[] loadSetMode() {
        return this.setMode;
    }


    public int toggleMode() {
        return 0;
    }

    public WatchTimer createTimer() {
        this.timer = new WatchTimer();
        this.setMode[0] = true;
        return this.timer;
    }

    public void destoryTimer() {
        this.timer.reset();
        this.timer = null;
        this.setMode[0] = false;
    }


    public StopWatch createStopwatch() {
        this.stopwatch = new StopWatch();
        // TODO implement here
        return this.stopwatch;
    }

    public void destroyStopwatch() {
        this.stopwatch.reset();
        this.stopwatch = null;
        this.setMode[1] = false;
    }


    public Alarm createAlarm() {
        this.alarm = new Alarm();
        this.setMode[2] = true;
        return this.alarm;
    }

    public void destroyAlarm() {
        this.alarm.disableAlarm(0);
        this.alarm.disableAlarm(1);
        this.alarm.disableAlarm(2);
        this.alarm.disableAlarm(3);
        this.alarm = null;
        this.setMode[2] = true;
    }


    public Dday createDday() {
        this.dday = new Dday();
        this.setMode[3] = true;
        return this.dday;
    }

    public void destroyDday() {
        this.dday.reset();
        this.dday = null;
        this.setMode[3] = false;
    }


    public IntervalTimer createIntervalTimer() {
        this.intervaltimer = new IntervalTimer();
        this.setMode[4] = true;
        return this.intervaltimer;
    }

    public void destroyIntervalTimer() {
        this.intervaltimer.disable();
        this.intervaltimer = null;
        this.setMode[4] = false;
    }
}