package application;

import controller.Controller;
import view.View;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        View v = new View();
        Controller c = new Controller(v);
        c.initController();

    }

}
