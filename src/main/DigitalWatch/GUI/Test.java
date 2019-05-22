package GUI;

import javax.swing.*;
import java.awt.*;

public class Test extends JFrame {

    public Test() {
        this.setTitle("Test JFrame");
        this.setSize(300, 300);
        this.setLocation(300, 300);
        this.setLayout(null);

        TestComponent test = new TestComponent();
        test.setSize(10, 10);
        test.setBackground(Color.red.brighter());
        test.setLocation(10, 10);

        TestDigit digit = new TestDigit(3, 6);
        digit.setSize(55, 110);
        digit.setLocation(30, 30);
        digit.setVisible(true);

        this.add(test);
        this.add(digit);
        this.setVisible(true);
    }
}
