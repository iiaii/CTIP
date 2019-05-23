package GUI;

import javax.swing.*;
import java.awt.*;

public class WatchPanel extends JPanel {
    public WatchPanel() {
        //this.setSize(getParent().getSize());
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        RenderingHints qualityHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        qualityHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2.setRenderingHints(qualityHints);
        g2.setStroke(new BasicStroke(4));

        //draw outline border
        g2.drawRoundRect(2,2,this.getWidth()-4, this.getHeight()-4,80, 80);

        //draw inline border
        g2.drawRoundRect(30, 30, this.getWidth()-60, this.getHeight()-60,80, 80);
    }

}
