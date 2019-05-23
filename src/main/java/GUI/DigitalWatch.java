package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import Logic.*;
import sun.jvm.hotspot.utilities.Interval;

public class DigitalWatch extends JFrame {
    private static DigitalWatch instance;
    private WatchButton[] buttons;
    private WatchIcon[] icons;
    private String[] iconNames = {"sun","timer","stopwatch","alarm","dday","intervaltimer"};
    private WatchCursor[] cursors = new WatchCursor[7];
    private WatchSystem ws;

    public DigitalWatch() {
        /* Image Cache initialize */
        for(int i=0;i<6;i++){
            IconImages.getImage(iconNames[i]);
        }
        char digitFileName[] = {'0','1','2','3','4','5','6','7','8','9','-','d','E','n','P','z'};
        for(int i=0;i<16;i++){
            IconImages.getImage("segment/color1/" + Character.toString(digitFileName[i]));
            IconImages.getImage("segment/color2/" + Character.toString(digitFileName[i]));
        }
        int[] btnX = {24, 646, 24, 646};
        int[] btnY = {100, 100, 310, 310};
        this.setBackground(Color.black);
        buttons = new WatchButton[4];
        for(int btnIndex = 0;btnIndex<4;btnIndex++){
            WatchButton btn = new WatchButton();
            btn.setSize(30, 60);
            btn.setLocation(btnX[btnIndex], btnY[btnIndex]);
            btn.setBackground(Color.red);
            btn.setVisible(true);
            btn.setShape((btnIndex % 2) == 0);
            btn.setHorizontalAlignment(JLabel.CENTER);
            this.add(btn);
            buttons[btnIndex] = btn;
        }
        this.setTitle("Digital Watch");
        this.setSize(700, 500);
        this.setLayout(null);

        WatchPanel panel = new WatchPanel();
        panel.setLayout(null);
        panel.setSize(600, 400);
        panel.setLocation(50, 40);
        panel.setBackground(Color.cyan);
        panel.setOpaque(false);

        icons = new WatchIcon[6];
        Color c = new Color(0, 144, 158);
        Color[] iconColors = {new Color(242,140,56 ),new Color(221,221,221),c,new Color(255, 111, 97),c,new Color(221,221,221)};
        for(int i=0;i< icons.length;i++){
            icons[i] = new WatchIcon(iconNames[i], iconColors[i]);
            icons[i].setSize(40, 40);
            icons[i].setLocation(50 + 45 * i, 80);
            icons[i].setVisible(true);
            panel.add(icons[i]);
        }


        JPanel miniDigits = new JPanel(){
            public void paintComponent(Graphics g){
                super.paintComponent(g);

                Graphics2D g2 = (Graphics2D)g;
                RenderingHints qualityHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                qualityHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                g2.setRenderingHints(qualityHints);
                g2.setStroke(new BasicStroke(4));

                //draw outline border
                //g2.drawRoundRect(2,2,width-4, this.getHeight()-4,80, 80);
                g2.drawRoundRect(2,2,this.getWidth()-4, this.getHeight()-4,20, 20);

            }
        };
        miniDigits.setBackground(Color.white);
        miniDigits.setSize(220,60);
        miniDigits.setLocation(330,70);
        miniDigits.setLayout(null);

        String[] date = {"zz", "zz","zz","zz"};
        WatchCursor dateDigit[] = new WatchCursor[4];
        int sizeX = 50, sizeY=42, startXOffset = 10;
        for(int i=0;i<4;i++){
            dateDigit[i] = new WatchCursor(date[i]);
            dateDigit[i].setSize(sizeX, sizeY);
            dateDigit[i].setLocation((startXOffset) + (sizeX) * (i), 10);
            dateDigit[i].setVisible(true);
            cursors[i] = dateDigit[i];
            miniDigits.add(dateDigit[i]);
        }
        panel.add(miniDigits);

        WatchCursor hour = new WatchCursor("zz");
        hour.setSize(136, 110);
        hour.setLocation(70, 190);
        hour.setVisible(true);
        cursors[4] = hour;
        panel.add(hour);

        WatchCursor minute = new WatchCursor("zz");
        minute.setSize(136, 110);
        minute.setLocation(231, 190);
        minute.setVisible(true);
        cursors[5] = minute;
        panel.add(minute);

        WatchCursor second = new WatchCursor("zz");
        second.setSize(136, 110);
        second.setLocation(392, 190);
        second.setVisible(true);
        cursors[6] = second;
        panel.add(second);

        ClickEvent events = new ClickEvent();
        buttons[0].setClickListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                events.buttonA();
            }
        });
        buttons[1].setClickListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                events.buttonB();
            }
        });
        buttons[2].setClickListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                events.buttonC();
            }
        });
        buttons[3].setClickListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                events.buttonD();
            }
        });

        buttons[0].setClickHoldListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                events.buttonAHold();
            }
        });
        buttons[1].setClickHoldListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                events.buttonBHold();
            }
        });
        buttons[2].setClickHoldListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                events.buttonCHold();
            }
        });
        buttons[3].setClickHoldListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                events.buttonDHold();
            }
        });

        this.getContentPane().setBackground(Color.white);
        this.setResizable(false);
        this.add(panel);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setVisible(true);

        Timer m_timer = new Timer();
        this.ws = new WatchSystem(m_timer);
        m_timer.scheduleAtFixedRate(ws, 2000, 100);
    }

    public static DigitalWatch getInstance() {
        if(instance == null) instance = new DigitalWatch();
        return instance;
    }

    public void showDigit(String content) { // cursor yyyyMMddhhmmss
        for(int i=0;i<7;i++) {
            this.cursors[i].setSegments(content.substring(2 * i, 2 * (i + 1)));
        }
    }

    public void showMode(int[] modes) { // {0,1,0,0,0,0}
        Color colors[] = {new Color(221, 221, 221), new Color(255, 111, 97), new Color(0, 144, 158)}; // disabled, color1, color2
        for(int i=0;i<this.icons.length;i++){
            this.icons[i].setColor(colors[modes[i]]); // modes 값에 따라서 아이콘 색 지정
            // modes 0이면 비활성화, 1이면 리빙코랄, 2면 보색
        }
    }

    public void selectCursor(int cursor) {
        try {
            for(int i=0;i<this.cursors.length;i++){
                if(this.cursors[i].getCursorState() != (i == cursor))
                    this.cursors[i].setCursorState(i == cursor);
            }
        } catch(NullPointerException e){
        }
    }

    class ClickEvent implements ButtonEvent {

        public void buttonA() {
            if(ws.getSetMode() == false) {
                Object mode = ws.getCurrentMode();
                if(mode instanceof TimeKeeping) {
                    ws.changeHourFormat();
                }

                if(mode instanceof Dday) {
                    ws.changeDdayFormat();
                }
            } else {
                ws.changeCursor();
            }

        }

        public void buttonAHold() {
            if(ws.getSetMode() == false) {
                ws.enterSetMode();
            } else {
                //none
            }
        }

        public void buttonB() {
            if(ws.getSetMode() == true) {
                //mode toggle
                //ws.chooseModes();
            } else {
                Boolean isEditMode = ws.getEditMode();
                Object mode = ws.getCurrentMode();
                if(isEditMode == false) {
                    if(mode instanceof WatchTimer){
                        WatchTimer t = (WatchTimer)mode;
                        if(t.getActived() == true){
                            ws.pauseTimer();
                        } else {
                            ws.activateTimer();
                        }
                    }

                    if(mode instanceof StopWatch){
                        StopWatch t = (StopWatch)mode;
                        if(t.getActivated() == true){
                            ws.pauseStopwatch();
                        } else {
                            ws.activateStopwatch();
                        }
                    }

                    if(mode instanceof Alarm) {
                        // get alarm state
                        if(((Alarm)mode).alarms[ws.getCurrentAlarmPage()].isEnabled == true) {
                            ws.disableAlarm();
                        } else {
                            ws.enableAlarm();
                        }
                    }

                    if(mode instanceof Dday) {
                        Dday t = (Dday)mode;
                        // none
                    }

                    if(mode instanceof IntervalTimer) {
                        IntervalTimer t = (IntervalTimer)mode;
                        ws.disableIntervalTimer();
                        ws.enablentervalTimer(); // 오타임
                    }
                } else {
                    ws.increaseData();
                }
            }
        }

        public void buttonBHold() {
            ///nope
        }

        public void buttonC() {

            if(ws.getSetMode() == true) {
                //mode toggle
                //ws.chooseModes();
                ws.saveMode();
            } else {
                Boolean isEditMode = ws.getEditMode();
                Object mode = ws.getCurrentMode();
                if (isEditMode == true) {
                    if (mode instanceof TimeKeeping) {
                        ws.saveTime();
                    }

                    if (mode instanceof WatchTimer) {
                        ws.saveTimer();
                    }

                    if (mode instanceof Alarm) {
                        ws.saveAlarm();
                    }

                    if (mode instanceof Dday) {
                        ws.saveDday();
                    }

                    if (mode instanceof IntervalTimer) {
                        ws.saveIntervalTimer();
                    }
                } else {
                    ws.changeMode();
                }
            }
        }

        public void buttonCHold() {
            if(ws.getSetMode() == true) {
                //none
            } else {
                ws.enterEditMode();
            }
        }

        public void buttonD() {

            if(ws.getSetMode() == true) {
                //none
            } else {
                Boolean isEditMode = ws.getEditMode();
                Object mode = ws.getCurrentMode();
                if (isEditMode == true) {
                    if (mode instanceof WatchTimer) {
                        ws.resetTimer();
                    }

                    if (mode instanceof StopWatch) {
                        ws.resetStopwatch();
                    }

                    if (mode instanceof Alarm) {
                        ws.resetAlarm();
                    }

                    if (mode instanceof Dday) {
                        ws.changePage();
                    }

                    if (mode instanceof IntervalTimer) {
                        ws.resetIntervalTimer();
                    }
                } else {
                    ws.changeMode();
                }
            }
        }

        public void buttonDHold() {
            Boolean isEditMode = ws.getEditMode();
            Object mode = ws.getCurrentMode();
            if (isEditMode == true) {
                if (mode instanceof TimeKeeping) {
                    //ws.exitEditMode();
                }

                if (mode instanceof WatchTimer) {
                    //ws.exitEditMode();
                }

                if (mode instanceof Alarm) {
                    //ws.exitEditMode();
                }

                if (mode instanceof Dday) {
                    //ws.exitEditMode();
                }

                if (mode instanceof IntervalTimer) {
                    //ws.exitEditMode();
                }

                if(ws.getSetMode() == true) {
                    //ws.exitSetMode();
                }
            } else {
                if (mode instanceof Dday) {
                    ws.resetDday();
                }
            }
        }

    }

    public static void main(String[] args) {
        DigitalWatch.getInstance();
    }
}
