package presentationLayer;

import businessLayer.DeliveryService;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class ObserverInterface extends JFrame implements Observer {

    private JTextArea messageArea;
    private JLabel messageLabel;
    private JButton prepareButton;
    DeliveryService service = DeliveryService.GetDeliveryService();

    public ObserverInterface() {
        service.addObserver(this);
        messageArea = new JTextArea(5, 5);
        messageLabel = new JLabel("New orders are displayed below:");
        prepareButton = new JButton("Prepare Order!!");
        setPreferredSize(new Dimension(752, 430));
        setLayout(null);
        add(messageArea);
        add(messageLabel);
        add(prepareButton);
        messageArea.setBounds(80, 120, 305, 240);
        messageLabel.setBounds(80, 80, 215, 30);
        prepareButton.setBounds(455, 160, 145, 55);
    }

    @Override
    public void update(Observable o, Object arg) {

        messageArea.append("New order received!" + "\n");
        prepareButton.addActionListener(e -> messageArea.setText(""));
    }
}
