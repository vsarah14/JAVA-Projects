import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.util.concurrent.TimeUnit;

// the class for the alarm of the security system
public class Alarm {

    // the method that plays an alarm
    void playSound() {
        try {
            File file = new File("alarm_sound.wav");
            if (file.exists()) {
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
                Clip clip = AudioSystem.getClip();
                clip.open(audioStream);
                // the sound starts only for 3 seconds, then it stops
                clip.start();
                TimeUnit.SECONDS.sleep(3);
                clip.stop();
            } else {
                System.out.println("Can't find file.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
