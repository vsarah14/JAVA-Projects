package controller;

import businessLayer.User;
import presentationLayer.AdministratorInterface;
import presentationLayer.ClientInterface;
import presentationLayer.LoginInterface;
import presentationLayer.ObserverInterface;

import javax.swing.*;
import java.io.*;

public class Controller {

    private LoginInterface view1 = new LoginInterface();
    private ObserverInterface view2 = new ObserverInterface();
    private ClientInterface view3 = new ClientInterface();
    private AdministratorInterface view4 = new AdministratorInterface();
    private User user = new User();
    private String message = "You have successfully registered!";

    public Controller(LoginInterface view) {
        this.view1 = view;
    }

    public void initController() {

        this.view1.setTitle("Food Management System");
        this.view1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.view1.pack();
        this.view1.setVisible(true);
        this.view1.setResizable(false);
        this.view1.setLocationRelativeTo(null);

        this.view1.getRegisterButton().addActionListener(e -> {
            try {
                tryToRegister();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        this.view1.getLoginButton().addActionListener(e -> {
            try {
                tryToLogin();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    public void clientWindow() {
        this.view3.setTitle("Client");
        this.view3.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.view3.pack();
        this.view3.setVisible(true);
        this.view3.setResizable(false);
        this.view4.setLocationRelativeTo(null);
    }

    public void administratorWindow() {
        this.view4.setTitle("Administrator");
        this.view4.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.view4.pack();
        this.view4.setVisible(true);
        this.view4.setResizable(false);
        this.view4.setLocationRelativeTo(null);
    }

    public void observerWindow() {
        this.view2.setTitle("Employee");
        this.view2.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.view2.pack();
        this.view2.setVisible(true);
        this.view2.setResizable(false);
        this.view2.setLocationRelativeTo(null);
    }

    public void tryToRegister() throws Exception {
        user.setUsername(this.view1.getTextField1().getText());
        user.setPassword(this.view1.getTextField2().getText());
        int count = 0;
        String word = user.getUsername();
        File f = new File("User.txt");
        BufferedReader fr = new BufferedReader(new FileReader(f));
        String s;
        while ((s = fr.readLine()) != null) {
            String[] st = s.split(" ");
            String name = st[1];
            if (st[0].equals("Username:")) {
                if (name.equals(word)) {
                    count++;
                }
            }
        }
        if (count == 0) {
            appendUsingFileOutputStream("User.txt", "Username: " + user.getUsername() + "\n");
            appendUsingFileOutputStream("User.txt", "Password: " + user.getPassword() + "\n");
            message = "You have successfully registered!";
        } else {
            message = "Please try to register again! This username is already taken.";
        }
        displayMessage(message);
    }

    public void tryToLogin() throws Exception {
        user.setUsername(this.view1.getTextField1().getText());
        user.setPassword(this.view1.getTextField2().getText());

        if ((!user.getUsername().equals("administrator")) && (!user.getUsername().equals("employee"))) {
            if (tryToLogin1(user.getUsername(), user.getPassword()) == 2) {
                message = "Success!You are a client!";
                displayMessage(message);
                this.view1.getClientButton().addActionListener(e -> clientWindow());
            } else {
                message = "You need to create an account.";
                displayMessage(message);
            }
        } else if (user.getUsername().equals("administrator")) {
            if (tryToLogin1(user.getUsername(), user.getPassword()) == 2) {
                message = "Success!You are an administrator!";
                displayMessage(message);
                this.view1.getAdministratorButton().addActionListener(e -> administratorWindow());
            } else {
                message = "You need to create an account.";
                displayMessage(message);
            }
        } else if (user.getUsername().equals("employee")) {
            if (tryToLogin1(user.getUsername(), user.getPassword()) == 2) {
                message = "Success!You are an employee!";
                displayMessage(message);
                this.view1.getEmployeeButton().addActionListener(e -> observerWindow());
            } else {
                message = "You need to create an account.";
                System.out.println(tryToLogin1(user.getUsername(), user.getPassword()));
                displayMessage(message);
            }
        }
    }

    public int tryToLogin1(String name, String password) throws Exception {
        File f = new File("User.txt");
        BufferedReader fr = new BufferedReader(new FileReader(f));
        int count = 0;
        int var = 0;
        int n = 2;
        String s;
        while (n != 0 && (s = fr.readLine()) != null) {
            String[] st = s.split(" ");
            if (st[0].equals("Username:")) {
                count++;
                if (st[1].equals(name)) {
                    n--;
                }
            }
            s = fr.readLine();
            st = s.split(" ");
            if (st[0].equals("Password:")) {
                count++;
                if (st[1].equals(password)) {
                    n--;
                }
            }
            if (n == 0)
                var = 2;
            if (count % 2 == 0) {
                n = 2;
            }
        }
        return var;
    }

    private static void appendUsingFileOutputStream(String fileName, String data) {
        OutputStream os = null;
        try {
            os = new FileOutputStream(new File(fileName), true);
            os.write(data.getBytes(), 0, data.length());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void displayMessage(String message) {
        this.view1.getTextField3().setText(message);
    }

}
