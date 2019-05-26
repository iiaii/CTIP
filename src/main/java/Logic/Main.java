package Logic;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.net.URL;
import java.util.LinkedList;

public class Main {
    private AudioInputStream audioIn;
    private Clip clip;

    public Main() throws Exception{
        clip.start();
        clip.loop(999);
        System.out.println("start");

    }
    public void play() throws Exception{
        // Open audio clip and load samples from the audio input stream.
        //clip.start();
    }

    public void pause() {
        //clip.stop();
        clip.stop();
    }

    public static void main(String[] args) throws Exception {
        Main m = new Main();
        m.play();
        Thread.sleep(5000);
///        m.pause();
        System.out.println("program end? sound end??");
    }


}
