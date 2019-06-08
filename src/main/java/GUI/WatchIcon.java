package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class WatchIcon extends JLabel {
    private boolean selected;
    private BufferedImage originalImg;
    private BufferedImage img;
    private Color tempColor = Color.black;
    private Color currentColor = Color.BLACK;

    public WatchIcon(String iconName)  {
        this.originalImg = IconImages.getImage(iconName);
        this.img = recolorImage(this.originalImg, Color.gray);
        this.setBackground(Color.black);
    }
    public WatchIcon(String iconName, Color defaultColor)  {
        this(iconName);
        this.img = recolorImage(this.originalImg, defaultColor);
    }

    public void setColor(Color color){
        this.currentColor = color;
        this.img = recolorImage(this.originalImg, color);
        this.paintComponent(this.getGraphics());
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        RenderingHints qualityHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        qualityHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2.setRenderingHints(qualityHints);

        g2.clearRect(0,0,this.getWidth(), this.getHeight());
        if(this.img != null)
            g2.drawImage(this.img, 0, 0,this.getWidth(),this.getHeight(), this);
        else {
            g2.drawImage(this.originalImg, 0, 0,this.getWidth(),this.getHeight(), this);
        }

    }

    public BufferedImage recolorImage(BufferedImage original, Color replaceColor) {
        int width = original.getWidth();
        int findColor = Color.black.getRGB();
        int height = original.getHeight();
        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = (Graphics2D)newImage.getGraphics();
        for(int w=0;w<width;w++){
            for(int h=0;h<height;h++){
                if(findColor == original.getRGB(w,h)) {
                    g.setColor(replaceColor);
                } else {
                    g.setColor(Color.white);
                }
                g.drawLine(w, h, w, h);
            }
        }
        return newImage;
    }
}
