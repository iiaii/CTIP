package GUI;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class IconImages {
    public static HashMap<String, BufferedImage> icons;

    private IconImages() {}

    private static BufferedImage loadFile(String filename) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("icon/" + filename + ".png"));
        } catch(IOException e){

        }
        return image;
    }

    public static BufferedImage getImage(String filename) {
        if(icons == null){
            icons =new HashMap<>(10);
        }
        BufferedImage temp = icons.get(filename);
        if(temp == null) icons.put(filename, loadFile(filename));
        return temp;
    }
}
