package view;

import javax.swing.*;
import java.awt.*;

public class View extends JFrame {

    //the components of the graphical interface
    private JButton computeButton;
    private JTextField textField3;
    private JTextField textField2;
    private JTextField textField1;
    private JComboBox comboBox;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;

    public View() {

        setPreferredSize(new Dimension(700, 400));
        setLayout(null);

        ImageIcon image = new ImageIcon("logo.png");
        this.setIconImage(image.getImage());

        //the button
        computeButton = new JButton("Compute");
        computeButton.setBounds(200, 300, 400, 40);
        add(computeButton);

        //the combobox
        comboBox = new JComboBox();
        this.comboBox.addItem("Add");
        this.comboBox.addItem("Subtract");
        this.comboBox.addItem("Multiply");
        this.comboBox.addItem("Integrate");
        this.comboBox.addItem("Derivative");
        comboBox.setBounds(50, 300, 100, 40);
        add(comboBox);

        //the 3 text fields(one of them is enabled, so you cannot write in it)
        textField1 = new JTextField();
        textField2 = new JTextField();
        textField3 = new JTextField();
        textField3.setEnabled(false);
        textField1.setBounds(200, 70, 400, 40);
        textField2.setBounds(200, 140, 400, 40);
        textField3.setBounds(200, 215, 400, 40);
        textField3.setDisabledTextColor(Color.black);
        add(textField3);
        add(textField1);
        add(textField2);

        //the 4 labels
        label1 = new JLabel("Polynom1:");
        label2 = new JLabel("Polynom2:");
        label3 = new JLabel("Result:");
        label4 = new JLabel("Polynomial Calculator");
        label1.setBounds(50, 75, 100, 25);
        label2.setBounds(50, 145, 100, 25);
        label3.setBounds(50, 220, 100, 25);
        label4.setBounds(300, 20, 200, 25);
        add(label1);
        add(label2);
        add(label3);
        add(label4);

    }

    //getters and setters
    public JButton getComputeButton() {
        return computeButton;
    }

    public JTextField getTextField2() {
        return textField2;
    }

    public JTextField getTextField1() {
        return textField1;
    }

    public JComboBox getComboBox() {
        return comboBox;
    }

    public void setResult(String message) {
        this.textField3.setText(message);
    }
}
