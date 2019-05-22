package GUI;

import javax.swing.*;
import java.awt.*;

public class TestComponent extends JPanel {

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.fillRoundRect(0, 0, 10, 3, 0,0);
    }
}

