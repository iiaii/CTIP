package GUI;

import javax.swing.*;
import java.awt.*;

public class WatchCursor extends JPanel {
    private int number;
    private int stroke = 2;
    private WatchDigit digit1, digit2;
    private int max1, max2;
    private Boolean cursor = false;

    public WatchCursor(){
        this.number = 0;
        this.max1 = 9;
        this.max2 = 9;
        this.setLayout(null);
    }

    public WatchCursor(int stroke) {
        this();
        this.stroke = stroke;
    }

    public WatchCursor(int stroke, int number){
        this(stroke);
        this.number = number;
    }

    public WatchCursor(int stroke, int number, int max1, int max2){
        this(stroke);
        this.max1 = max1;
        this.max2 = max2;
    }

    public void setCursorState(Boolean cursor){
        this.cursor = cursor;
    }

    public Boolean getCursorState() {
        return this.cursor;
    }

    private void setDigits(int number) {
        this.setDigits(number, this.digit1, this.digit2);
    }

    private void setDigits(int number, WatchDigit digit1, WatchDigit digit2){
        int[] num = getSeperated(number);
        digit1.setNumber(num[0]);
        digit2.setNumber(num[1]);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        int[] num = getSeperated(this.number);
        if (this.digit1 == null) {
            System.out.println("Digit1 created");
            this.digit1 = new TestDigit(this.stroke, num[0]);
            this.digit1.setSize(this.getWidth() / 2, this.getHeight());
            this.digit1.setLocation(0, 0);
            this.digit1.setVisible(true);
            this.add(this.digit1);
        }

        if (this.digit2 == null) {
            System.out.println("Digit2 created");
            this.digit2 = new TestDigit(this.stroke, num[1]);
            this.digit2.setSize(this.getWidth() / 2, this.getHeight());
            this.digit2.setLocation(this.getWidth() / 2, 0);
            this.digit2.setVisible(true);
            this.add(this.digit2);
        }
    }

    public int increase() {
        int num = getIncreased(this.number);
        this.setDigits(num); // 현재 digit이 다음번의 nextDigit이 될거니까~ㄴ

        this.number = num;
        return this.number;
    }

    public int[] getSeperated(int number) {
        int num1 = (number / 10);
        int num2 = (number % 10);

        int[] result = {num1, num2};

        return result;
    }

    public int getIncreased(int number) {
        int[] num = getSeperated(number);
        if(num[1] == this.max2) {
            num[1] = 0;
            num[0] ++;
            if(num[0] > this.max1) {
                num[0] = 0;
            }
        } else {
            num[1]++;
        }
        number = (num[0] * 10) + num[1];

        return number;
    }

    public int getIncreased(int number, int iteration) {
        for(int i=0;i<iteration;i++){
            number = getIncreased(number);
        }

        return number;
    }


}
