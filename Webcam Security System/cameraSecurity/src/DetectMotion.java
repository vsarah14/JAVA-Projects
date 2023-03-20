import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamMotionDetector;
import com.github.sarxos.webcam.WebcamMotionEvent;
import com.github.sarxos.webcam.WebcamMotionListener;

import javax.imageio.ImageIO;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Date;

// the class that detects the motion and plays the alarm
public class DetectMotion implements WebcamMotionListener {

    private Alarm alarm = new Alarm();
    private int count = 1;

    public DetectMotion() {
        WebcamMotionDetector detector = new WebcamMotionDetector(Webcam.getDefault());
        detector.setInterval(500);
        detector.addMotionListener(this);
        detector.start();

        try {
            Files.write(Paths.get("motionDetected.txt"), "".getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void motionDetected(WebcamMotionEvent wme) {
        System.out.println("Motion detected.");

        alarm.playSound();

        String s = "motion_detected_";
        String end = ".jpg";

        try {
            ImageIO.write(Webcam.getDefault().getImage(), "JPG", new File(s + String.valueOf(count) + end));
            Files.write(Paths.get("motionDetected.txt"), ("Motion detected number " + String.valueOf(count) + "\n").getBytes(), StandardOpenOption.APPEND);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
            Date date = new Date(System.currentTimeMillis());
            Files.write(Paths.get("motionDetected.txt"), (formatter.format(date) + "\n").getBytes(), StandardOpenOption.APPEND);
            count++;

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
