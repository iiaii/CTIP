package GUI;

import javax.swing.*;
import java.awt.*;

public class WatchCursor extends JPanel {
    private int number;
    private int stroke = 2;
    private WatchDigit digit1, digit2;
    private String segments = "00";
    private boolean cursor = false;

    public WatchCursor(String segments){
        this.setLayout(null);
        this.segments = segments;
    }

    public void setCursorState(boolean cursor){
        this.cursor = cursor;
        this.digit1.setSelected(cursor);
        this.digit2.setSelected(cursor);
    }

    public boolean getCursorState() {
        return this.cursor;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = this.getWidth();
        int height = this.getHeight();

        if(this.digit1 == null) {
            this.digit1 = new WatchDigit(this.segments.charAt(0));
            this.digit1.setSize(width / 2, height);
            this.digit1.setLocation(0, 0);
            this.add(this.digit1);
        } else {
            this.digit1.setSegment(this.segments.charAt(0));
        }
        if(this.digit2 == null) {
            this.digit2 = new WatchDigit(this.segments.charAt(1));
            this.digit2.setSize(width / 2, height);
            this.digit2.setLocation(width / 2 , 0);
            this.add(this.digit2);
        } else {
            this.digit2.setSegment(this.segments.charAt(1));
        }
    }

    public void setSegments(String segments) {
        this.segments = segments;
        char segment1 = segments.charAt(0);
        this.digit1.setSegment(segment1);
        char segment2 = segments.charAt(1);
        this.digit2.setSegment(segment2);
        this.digit1.repaint();
        this.digit2.repaint();
    }

}
