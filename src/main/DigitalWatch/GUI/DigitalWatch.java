package GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Logic.WatchSystem;

public class DigitalWatch extends JFrame {
    WatchButton[] buttons;
    WatchSystem ws = new WatchSystem();
    ButtonEvent[] modes = new ButtonEvent[4];

    public DigitalWatch() {
        int[] btnX = {24, 646, 24, 646};
        int[] btnY = {100, 100, 310, 310};
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

        int[] date = {20,19,05,12};
        WatchCursor[] dateDigit = new WatchCursor[4];
        int sizeX = 60, sizeY=50, startXOffset = 50;
        for(int i=0;i<4;i++){
            dateDigit[i] = new WatchCursor(2, date[i]);
            dateDigit[i].setSize(sizeX, sizeY);
            dateDigit[i].setLocation((startXOffset +10) * (i+1), 60);
            dateDigit[i].setVisible(true);
            panel.add(dateDigit[i]);
        }

        WatchCursor hour = new WatchCursor(4, 19);
        hour.setSize(110, 110);
        hour.setLocation(160, 190);
        hour.setVisible(true);
        panel.add(hour);

        WatchCursor minute = new WatchCursor(4, 05);
        minute.setSize(110, 110);
        minute.setLocation(300, 190);
        minute.setVisible(true);
        panel.add(minute);

        WatchCursor second = new WatchCursor(4, 12);
        second.setSize(110, 110);
        second.setLocation(440, 190);
        second.setVisible(true);
        panel.add(second);

        this.add(panel);

        buttons[0].setClickListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modes[ws.modeManager.getCurrentMode()].buttonA();
            }
        });

        buttons[0].setClickHoldListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modes[ws.modeManager.getCurrentMode()].buttonAHold();
            }
        });

        buttons[1].setClickListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modes[ws.modeManager.getCurrentMode()].buttonB();
            }
        });

        buttons[1].setClickHoldListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modes[ws.modeManager.getCurrentMode()].buttonBHold();
            }
        });

        buttons[2].setClickListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modes[ws.modeManager.getCurrentMode()].buttonC();
            }
        });

        buttons[2].setClickHoldListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modes[ws.modeManager.getCurrentMode()].buttonCHold();
            }
        });
        buttons[3].setClickListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modes[ws.modeManager.getCurrentMode()].buttonD();
            }
        });

        buttons[3].setClickHoldListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modes[ws.modeManager.getCurrentMode()].buttonDHold();
            }
        });

        this.getContentPane().setBackground(Color.white);
        //this.setBackground(Color.white);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new DigitalWatch();
    }
}
