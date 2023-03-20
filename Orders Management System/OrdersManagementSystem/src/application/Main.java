package application;

import presentation.Controller;

import java.sql.SQLException;

public class Main {

    public static void main(String args[]) throws SQLException {

        Controller c = new Controller();
        c.initController();

    }
}
