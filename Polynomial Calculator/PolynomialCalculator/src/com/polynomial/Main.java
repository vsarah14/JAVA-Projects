package com.polynomial;

import controller.Controller;
import view.View;

public class Main {

    public static void main(String args[]) {
        View v = new View();
        Controller c = new Controller(v);

        c.initController();
    }
}
