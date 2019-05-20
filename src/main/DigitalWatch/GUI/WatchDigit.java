package GUI;

import javax.swing.*;
import java.awt.*;

public class WatchDigit extends JPanel {
    private int number;
    private int stroke = 2;
    private int preDigits[] = {-1,-1,-1,-1,-1,-1,-1};
    private Color[] colors = {Color.lightGray.brighter(), Color.red};

    public WatchDigit(){
        this.number = 0;
        this.setBackground(Color.white);
    }

    public WatchDigit(int stroke) {
        this();
        this.stroke = stroke;
    }

    public WatchDigit(int stroke, int number){
        this(stroke);
        this.number = number;
    }

    public void setStroke(int stroke){
        this.stroke = stroke;
        this.drawDigit(this.getGraphics());
    }

    public void setNumber(int number) {
        this.number = number;
        this.drawDigit(this.getGraphics());
    }

    public int getNumber(){
        return this.number;
    }

    private void drawDigit(Graphics g){
        System.out.println("drawDigit called");
        Graphics2D g2 = (Graphics2D)g;
        RenderingHints qualityHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        qualityHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2.setRenderingHints(qualityHints);


        int width = this.getWidth();
        int height = this.getHeight();

        g2.setStroke(new BasicStroke(stroke));

        int[] digits = get7Digits(this.number);
        int xStart = width / 10;
        int[][] coordinates = {
                {8, 2, this.getWidth() - 16, stroke},
                {this.getWidth() - (2 + stroke), 8, stroke, (this.getHeight()/2 - (2+8+3))},
                {this.getWidth() - (2+stroke), (this.getHeight() + stroke*2) /2, stroke,(this.getHeight() - (stroke*2+8+4))/2},
                {8, this.getHeight() - (2+stroke), this.getWidth() - 16, stroke},
                {2,(this.getHeight()+stroke*2)/2,stroke, (this.getHeight() - (stroke*2+8+4))/2},
                {2,8,stroke,(this.getHeight()/2-(2+8+3))},
                {8, (this.getHeight() - stroke*2)/2, this.getWidth() - 16, stroke}
        };

        for(int k=0;k<7;k++) {
            if (preDigits[k] != digits[k]) {
                g2.clearRect(coordinates[k][0], coordinates[k][1], coordinates[k][2], coordinates[k][3]);

                setLineColor(g2, digits[k]);

                g2.fillRoundRect(coordinates[k][0], coordinates[k][1], coordinates[k][2], coordinates[k][3], 3, 3);
                this.preDigits[k] = digits[k];
           }
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        this.drawDigit(g);
    }

    private void setLineColor(Graphics g, int bit) {
        g.setColor(this.colors[bit]);
    }

    public int[] get7Digits(int num) {
        int[] digits = new int[7];
        if(num > 9) {

            // d
            // P
            // E
        } else {

            int A = (num & 0x8) >> 3;
            int B = (num & 0x4) >> 2;
            int C = (num & 0x2) >> 1;
            int D = (num & 0x1);

            digits[0] = A | C | (B & D) | (B ^ 1 & D ^ 1);
            digits[1] = (B ^ 1) | (C ^ 1 & D ^ 1) | (C & D);
            digits[2] = B | (C ^ 1) | D;
            digits[3] = ((B ^ 1) & (D ^ 1)) | (C & (D ^ 1)) | (B & (C ^ 1) & D) | ((B ^ 1) & C) | A;
            digits[4] = ((B ^ 1) & (D ^ 1)) | (C & (D ^ 1));
            digits[5] = A | ((C ^ 1) & (D ^ 1)) | (B & (C ^ 1)) | (B & (D ^ 1));
            digits[6] = A | (B & (C ^ 1)) | ((B ^ 1) & C) | (C & (D ^ 1));
        }
        return digits;
    }
}
