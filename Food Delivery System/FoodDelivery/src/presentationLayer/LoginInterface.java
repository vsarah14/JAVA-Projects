package presentationLayer;

import javax.swing.*;
import java.awt.*;

public class LoginInterface extends JFrame {

    private JButton loginButton;
    private JButton registerButton;
    private JButton clientButton;
    private JButton administratorButton;
    private JButton employeeButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JLabel label1;
    private JLabel label2;

    public LoginInterface() {

        setPreferredSize(new Dimension(900, 700));
        setLayout(null);

        loginButton = new JButton("Log In");
        loginButton.setBounds(200, 350, 150, 50);
        add(loginButton);
        registerButton = new JButton("Register");
        registerButton.setBounds(400, 350, 150, 50);
        add(registerButton);
        clientButton = new JButton("Client");
        clientButton.setBounds(200, 450, 150, 50);
        add(clientButton);
        administratorButton = new JButton("Administrator");
        administratorButton.setBounds(400, 450, 150, 50);
        add(administratorButton);
        employeeButton = new JButton("Employee");
        employeeButton.setBounds(300, 550, 150, 50);
        add(employeeButton);
        textField1 = new JTextField();
        textField1.setBounds(200, 80, 350, 30);
        add(textField1);
        textField2 = new JTextField();
        textField2.setBounds(200, 150, 350, 30);
        add(textField2);
        textField3 = new JTextField();
        textField3.setBounds(200, 220, 350, 30);
        add(textField3);
        textField3.setEnabled(false);
        textField3.setDisabledTextColor(Color.BLACK);
        label1 = new JLabel("Username:");
        label1.setBounds(60, 80, 100, 25);
        add(label1);
        label2 = new JLabel("Password:");
        label2.setBounds(60, 150, 100, 25);
        add(label2);

    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public JButton getRegisterButton() {
        return registerButton;
    }

    public JButton getClientButton() {
        return clientButton;
    }

    public JButton getAdministratorButton() {
        return administratorButton;
    }

    public JTextField getTextField3() {
        return textField3;
    }

    public JTextField getTextField1() {
        return textField1;
    }

    public JTextField getTextField2() {
        return textField2;
    }

    public JButton getEmployeeButton() {
        return employeeButton;
    }
}
