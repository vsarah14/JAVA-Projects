package view;

import javax.swing.*;
import java.awt.*;

public class View extends JFrame {

    /**
     * extends the JFrame class
     * declares the labels, the text fields, the button and the list
     */

    //the labels
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JLabel label6;
    private JLabel label7;
    private JLabel label8;
    private JLabel label9;
    private JLabel label10;
    private JLabel label11;
    private JLabel label12;
    private JLabel label13;

    //the text fields
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JTextField textField8;
    private JTextField textField9;
    private JTextField textField10;

    //the scroll panel
    private JScrollPane logScrollPane;
    private JTextArea logsField;

    //the simulation button
    private JButton simulationButton;

    public View() {

        /**
         * adjusts the size of the frame and sets the layout
         * sets bounds for the components
         * adds each component to the frame
         * sets some text fields to disabled, so the user cannot write in them
         */
        setPreferredSize(new Dimension(898, 569));
        setLayout(null);

        //we name the labels and set the bounds, then add them to the frame
        label1 = new JLabel("No. clients:");
        label2 = new JLabel("No. queues:");
        label3 = new JLabel("Simulation time:");
        label4 = new JLabel("Arrival time:");
        label5 = new JLabel("minimum:");
        label6 = new JLabel("maximum:");
        label7 = new JLabel("Service time:");
        label8 = new JLabel("minimum:");
        label9 = new JLabel("maximum:");
        label10 = new JLabel("Log of Events:");
        label11 = new JLabel("Average waiting time:");
        label12 = new JLabel("Average service time:");
        label13 = new JLabel("Peak hour:");
        label1.setBounds(25, 15, 70, 20);
        label2.setBounds(25, 50, 70, 25);
        label3.setBounds(25, 90, 100, 25);
        label4.setBounds(25, 125, 100, 25);
        label5.setBounds(60, 150, 65, 25);
        label6.setBounds(60, 185, 65, 25);
        label7.setBounds(25, 220, 100, 25);
        label8.setBounds(60, 250, 60, 25);
        label9.setBounds(60, 285, 65, 25);
        label10.setBounds(505, 20, 100, 40);
        label11.setBounds(50, 380, 135, 25);
        label12.setBounds(50, 415, 130, 30);
        label13.setBounds(50, 465, 100, 25);
        add(label1);
        add(label2);
        add(label3);
        add(label4);
        add(label5);
        add(label6);
        add(label7);
        add(label8);
        add(label9);
        add(label10);
        add(label11);
        add(label12);
        add(label13);

        //we set the bound for the text fields and set some to disabled, so that the user cannot write anything there
        textField2 = new JTextField();
        textField1 = new JTextField();
        textField3 = new JTextField();
        textField4 = new JTextField();
        textField5 = new JTextField();
        textField6 = new JTextField();
        textField7 = new JTextField();
        textField8 = new JTextField();
        textField9 = new JTextField();
        textField10 = new JTextField();
        textField2.setBounds(135, 50, 100, 25);
        textField1.setBounds(135, 15, 100, 25);
        textField3.setBounds(135, 90, 100, 25);
        textField4.setBounds(135, 150, 100, 25);
        textField5.setBounds(135, 185, 100, 25);
        textField6.setBounds(135, 250, 100, 25);
        textField7.setBounds(135, 285, 100, 25);
        textField8.setBounds(185, 380, 100, 25);
        textField9.setBounds(185, 420, 100, 25);
        textField10.setBounds(185, 465, 100, 25);
        textField8.setEnabled(false);
        textField9.setEnabled(false);
        textField10.setEnabled(false);
        add(textField2);
        add(textField1);
        add(textField3);
        add(textField4);
        add(textField5);
        add(textField6);
        add(textField7);
        add(textField8);
        add(textField9);
        add(textField10);
        textField8.setDisabledTextColor(Color.black);
        textField9.setDisabledTextColor(Color.black);
        textField10.setDisabledTextColor(Color.black);

        //we set the scroll pane
        logScrollPane = new JScrollPane(logsField);
        logScrollPane.setBounds(505, 60, 335, 450);
        logsField = new JTextArea();
        logsField.setEditable(false);
        logScrollPane.setViewportView(logsField);
        add(logScrollPane);
        logsField.setText("");

        //we set the button that we need to press in order to start the simulation
        simulationButton = new JButton("START");
        simulationButton.setBounds(310, 140, 115, 70);
        add(simulationButton);

    }

    /**
     * getters and setters
     *
     * @return the parameters of the class
     */

    public JButton getSimulationButton() {
        return simulationButton;
    }

    public JTextField getTextField2() {
        return textField2;
    }

    public JTextField getTextField1() {
        return textField1;
    }

    public JTextField getTextField3() {
        return textField3;
    }

    public JTextField getTextField4() {
        return textField4;
    }

    public JTextField getTextField5() {
        return textField5;
    }

    public JTextField getTextField6() {
        return textField6;
    }

    public JTextField getTextField7() {
        return textField7;
    }

    //to display the result = the log of events
    public void setResult(String message) {
        logsField.setText(message);
    }

    //to display the average service time
    public void setAverage(float a) {
        String s = "";
        s+=a;
        textField9.setText(s);
    }

    public void setHour(int a){
        String s = "";
        s+=a;
        textField10.setText(s);
    }

    public void setWaiting(float a){
        String s = "";
        s+=a;
        textField8.setText(s);
    }
}
