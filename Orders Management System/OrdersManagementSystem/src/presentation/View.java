package presentation;

import javax.swing.*;
import java.awt.*;

public class View extends JFrame {

    private JButton clientButton;
    private JButton productButton;
    private JButton orderButton;
    private JLabel title;

    /**
     * Interface for the user (main interface).
     */
    public View() {

        setPreferredSize(new Dimension(752, 430));
        setLayout(null);

        clientButton = new JButton("Client operations");
        clientButton.setBounds(50, 175, 175, 85);
        add(clientButton);
        productButton = new JButton("Product operations");
        productButton.setBounds(285, 175, 175, 85);
        add(productButton);
        orderButton = new JButton("Order operations");
        orderButton.setBounds(520, 175, 175, 85);
        add(orderButton);
        title = new JLabel("WareHouse - Orders Management");
        title.setBounds(270, 65, 235, 50);
        add(title);

    }

    /**
     * Getter for the client button
     *
     * @return - the button for the client
     */
    public JButton getClientButton() {
        return clientButton;
    }

    /**
     * Getter for the product button.
     *
     * @return - the button for the product
     */
    public JButton getProductButton() {
        return productButton;
    }

    /**
     * Getter for the order Button
     *
     * @return - the button for the order
     */
    public JButton getOrderButton() {
        return orderButton;
    }

}
