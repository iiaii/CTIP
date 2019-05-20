package GUI;

import javax.swing.*;
import java.awt.*;

public class TestDigit extends WatchDigit {
    private int number;
    private int stroke = 2;
    private int preDigits[] = {-1,-1,-1,-1,-1,-1,-1};
    private Color[] colors = {Color.lightGray, Color.red};

    public TestDigit(){
        this.number = 0;
        this.setBackground(Color.white);
    }

    public TestDigit(int stroke) {
        this();
        this.stroke = stroke;
    }

    public TestDigit(int stroke, int number){
        this(stroke);
        this.number = number;
    }

    public void setStroke(int stroke){
        this.stroke = stroke;
        this.drawDigit(this.getGraphics(), true);
    }

    public void setNumber(int number) {
        this.number = number;
        this.drawDigit(this.getGraphics(), false);
    }

    public int getNumber(){
        return this.number;
    }

    private void drawDigit(Graphics g, Boolean repaint){
        System.out.println("drawDigit called" + this.number);
        Graphics2D g2 = (Graphics2D)g;
        RenderingHints qualityHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        qualityHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2.setRenderingHints(qualityHints);

        g2.setStroke(new BasicStroke(stroke));

        int[] digits = get7Digits(this.number);
        int width = this.getWidth();
        int height = this.getHeight();
        int space = width/10 + (stroke / 2);
        int redundant = 1;// 여백적용
        int widthOfHorizontal = width - (space + redundant)*2;
        int heightOfVertical = height/2 - (int)(space * 1.5);
        int rounding = 4;

        int[][] coordinates = {
                {space + redundant, 0, widthOfHorizontal, stroke, rounding, rounding},
                {width-(stroke + redundant),space, stroke, heightOfVertical, rounding, rounding},
                {width-(stroke + redundant),height - (space + heightOfVertical),stroke, heightOfVertical, rounding, rounding},
                {space + redundant,height-(stroke),widthOfHorizontal,stroke,rounding,rounding},
                {redundant,height-(space+heightOfVertical),stroke,heightOfVertical,rounding,rounding},
                {redundant,space,stroke,heightOfVertical,rounding,rounding},
                {space + redundant,(height-stroke)/2,widthOfHorizontal,stroke,rounding,rounding}
        };

        for(int k=0;k<7;k++) {
            if (preDigits[k] != digits[k] || repaint) {
                g2.clearRect(coordinates[k][0], coordinates[k][1], coordinates[k][2], coordinates[k][3]);

                setLineColor(g2, digits[k]);

                g2.drawRoundRect(coordinates[k][0], coordinates[k][1], coordinates[k][2], coordinates[k][3], 3, 3);
                this.preDigits[k] = digits[k];
            }
        }
    }

    public void paint(Graphics g){
        super.paint(g);
       // this.drawDigit(g, true);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        this.drawDigit(g, false);
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
