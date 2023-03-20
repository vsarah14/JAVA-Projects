import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;

import javax.swing.*;
import java.awt.*;

// the class for the graphic interface
public class WebcamGUI extends JFrame {

    private JFrame frame;
    private Webcam webcam;

    WebcamGUI() {
        frame = new JFrame("Security Camera");
        // get the size of the screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        // determine the new location of the window
        int w = this.getSize().width;
        int h = this.getSize().height;
        int x = (dim.width - w) / 3;
        int y = (dim.height - h) / 4;
        // move the window
        frame.setLocation(x, y);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        webcam = Webcam.getDefault();
        webcam.setViewSize(WebcamResolution.VGA.getSize());
        WebcamPanel webcamPanel = new WebcamPanel(webcam);
        // to display the image size
        webcamPanel.setImageSizeDisplayed(true);
        // to display the frequency measured by fps
        webcamPanel.setFPSDisplayed(true);
        webcamPanel.setMirrored(true);
        frame.add(webcamPanel);
        frame.pack();
    }
}
