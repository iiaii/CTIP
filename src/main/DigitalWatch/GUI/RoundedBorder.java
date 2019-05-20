package GUI;

import javax.swing.border.AbstractBorder;
import java.awt.*;
import java.awt.geom.Arc2D;
import java.awt.geom.Area;
import java.awt.geom.Line2D;
import java.awt.geom.RoundRectangle2D;

@SuppressWarnings("serial")
public class RoundedBorder extends AbstractBorder {
    private int radii;
    private Insets insets;
    private Color innerS = new Color(166, 166, 166);
    private Color outerS = new Color(116, 116, 116);
    private Color innerH = Color.WHITE;
    private Color outerH = Color.WHITE;
    private boolean tColor = false;

    public RoundedBorder(int radius) {
        radii = radius;
    }

    public RoundedBorder(int radius, Insets i) {
        radii = radius;
        insets = i;
    }

    public RoundedBorder(int radius, Insets i, Color out, Color in) {
        radii = radius;
        insets = i;
        innerS = in;
        outerS = out;
        tColor = true;
    }

    public RoundedBorder(int radius, Insets i, Color outH, Color inH, Color outS, Color inS) {
        radii = radius;
        insets = i;
        innerS = inS;
        outerS = outS;
        innerH = inH;
        outerH = outH;
    }

    @Override
    public Insets getBorderInsets(Component c) {
        if(insets != null) {
            return insets;
        }
        return new Insets(5, 5, 5, 5);
    }

    @Override
    public Insets getBorderInsets(Component c, Insets insets) {
        return getBorderInsets(c);
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        super.paintBorder(c, g, x, y, width, height);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));

        Shape clip = g2d.getClip();
        Area rect = new Area(clip);
        rect.subtract(new Area(new RoundRectangle2D.Double(x, y, width, height, radii, radii)));
        g2d.setClip(rect);
        g2d.setColor(c.getParent().getBackground());
        g2d.fillRect(0, 0, width, height);
        g2d.setClip(clip);
        g2d.draw(rect);

        if(!tColor) {
            double offset = radii / 2;
            //Outer
            g2d.setColor(outerH);
            g2d.draw(new Line2D.Double(0, offset, 0, height - 2 - offset)); //Left
            g2d.draw(new Line2D.Double(1 + offset, 0, width - 2 - offset, 0)); //Top
            g2d.draw(new Arc2D.Double(width - radii - 1, 0, radii, radii, 45, 45, Arc2D.OPEN)); //Top Right P1
            g2d.draw(new Arc2D.Double(0, 0, radii, radii, 90, 90, Arc2D.OPEN)); //Top Left
            g2d.draw(new Arc2D.Double(0, height - radii - 1, radii, radii, 180, 45, Arc2D.OPEN)); //Bottom Left P1

            //Inner
            g2d.setColor(innerH);
            g2d.draw(new Line2D.Double(1, 1 + offset, 1, height - 3 - offset)); //Left
            g2d.draw(new Line2D.Double(2 + offset, 1, width - 3 - offset, 1)); //Top
            g2d.draw(new Arc2D.Double(width - radii - 2, 1, radii, radii, 45, 45, Arc2D.OPEN)); //Top Right P1
            g2d.draw(new Arc2D.Double(1, 1, radii, radii, 90, 90, Arc2D.OPEN)); //Top Left
            g2d.draw(new Arc2D.Double(1, height - radii - 2, radii, radii, 180, 45, Arc2D.OPEN)); //Bottom Left P1

            //Outer
            g2d.setColor(outerS);
            g2d.draw(new Line2D.Double(offset, height - 1, width - 1 - offset, height - 1)); //Bottom
            g2d.draw(new Line2D.Double(width - 1, offset, width - 1, height - 2 - offset)); //Right
            g2d.draw(new Arc2D.Double(width - radii - 1, 0, radii, radii, 0, 45, Arc2D.OPEN)); //Top Right P2
            g2d.draw(new Arc2D.Double(width - radii - 1, height - radii - 1, radii, radii, 270, 90, Arc2D.OPEN)); //Bottom Right
            g2d.draw(new Arc2D.Double(0, height - radii - 1, radii, radii, 225, 45, Arc2D.OPEN)); //Bottom Left P2

            //Inner
            g2d.setColor(innerS);
            g2d.draw(new Line2D.Double(1 + offset, height - 2, width - 2 - offset, height - 2)); //Bottom
            g2d.draw(new Line2D.Double(width - 2, 1 + offset, width - 2, height - 3 - offset)); //Right
            g2d.draw(new Arc2D.Double(width - radii - 2, 1, radii, radii, 0, 45, Arc2D.OPEN)); //Top Right P2
            g2d.draw(new Arc2D.Double(width - radii - 2, height - radii - 2, radii, radii, 270, 90, Arc2D.OPEN)); //Bottom Right
            g2d.draw(new Arc2D.Double(1, height - radii - 2, radii, radii, 225, 45, Arc2D.OPEN)); //Bottom Left P2
        } else {
            g2d.setColor(innerS); //Inner Rectangle
            g2d.draw(new RoundRectangle2D.Double(x + 1, y + 1, width - 2, height - 2, radii, radii));

            g2d.setColor(outerS); //Outer Rectangle
            g2d.draw(new RoundRectangle2D.Double(x, y, width - 2, height - 2, radii, radii));
        }
    }
}
