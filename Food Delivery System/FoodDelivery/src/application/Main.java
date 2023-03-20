package application;

import controller.Controller;
import presentationLayer.LoginInterface;

public class Main {

    public static void main(String args[]) {
        LoginInterface v = new LoginInterface();

        Controller c = new Controller(v);
        c.initController();

    }
}
