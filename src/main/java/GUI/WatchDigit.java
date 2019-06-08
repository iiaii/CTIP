package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class WatchDigit extends JPanel {
    private char segment;
    private boolean selected;
    private BufferedImage image;

    public boolean getSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
        this.loadSegment();
    }

    public WatchDigit() {

        this.setSegment('0');
        this.selected = false;
        this.setBackground(Color.white);
    }

    public WatchDigit(char segment){
        this();
        this.setSegment(segment);
    }

    public void setSegment(char segment) {
        this.segment = segment;
    }

    private void loadSegment()  {
        String path = "segment/color" + ((selected == false) ? "1" : "2") + "/" + Character.toString(segment);
        this.image = IconImages.getImage(path);

    }

    private void drawDigit(Graphics g) {
        this.loadSegment();
        g.drawImage(this.image, 0, 0, this.getWidth(), this.getHeight(), this);
    }

    public void paint(Graphics g) {
        super.paint(g);
        this.drawDigit(g);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.drawDigit(g);
    }

}
