package controller;

import model.Polynom;
import view.View;

import javax.swing.*;

public class Controller {

    private View view = new View();

    public Controller(View view) {
        this.view = view;
    }

    public void initController() {

        this.view.setTitle("Polynomial Calculator");
        this.view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.view.pack();
        this.view.setVisible(true);
        this.view.setResizable(false);
        this.view.getComputeButton().addActionListener(e -> executePolyOperation());
    }

    private void executePolyOperation() {
//        read polynomials
        String poly1 = this.view.getTextField1().getText();
        String poly2 = this.view.getTextField2().getText();

//        read operation
        String op = this.view.getComboBox().getSelectedItem().toString();

        try {
            Polynom p1 = Polynom.parseStringForPoly(poly1);
            Polynom p2 = Polynom.parseStringForPoly(poly2);

            Polynom res = Polynom.polynomialOperation(p1, p2, op);

            this.view.setResult(res.toString());
            if (op == "Integrate") {
                this.view.setResult(res + " +C");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            this.view.setResult(e.getMessage());
        }


    }


}
