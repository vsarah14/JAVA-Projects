package presentation;

import connection.ConnectionFactory;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;

public class Controller {

    private View view1 = new View();
    private ClientView view2;
    private ProductView view3;
    private OrderView view4;

    /**
     * Constructor for the controller.
     *
     * @param view - the user interface
     */
    public void Controller(View view) {
        this.view1 = view;
    }

    /**
     * Initializing the controller and the main interface.
     *
     * @throws SQLException - exception for sql database
     */
    public void initController() throws SQLException {
        Connection con = ConnectionFactory.getConnection();

        view2 = new ClientView(con);
        view3 = new ProductView(con);
        view4 = new OrderView(con);

        this.view1.setTitle("Orders Management System");
        this.view1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.view1.pack();
        this.view1.setVisible(true);
        this.view1.setResizable(false);
        this.view1.setLocationRelativeTo(null);

        this.view1.getClientButton().addActionListener(e -> clientWindow());
        this.view1.getProductButton().addActionListener(e -> productWindow());
        this.view1.getOrderButton().addActionListener(e -> orderWindow());
    }

    /**
     * A method for the client window.
     */
    public void clientWindow() {
        this.view2.setTitle("Client");
        this.view2.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.view2.pack();
        this.view2.setVisible(true);
        this.view2.setResizable(false);
        this.view2.setLocationRelativeTo(null);
    }

    /**
     * A method for the product window.
     */
    public void productWindow() {
        this.view3.setTitle("Product");
        this.view3.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.view3.pack();
        this.view3.setVisible(true);
        this.view3.setResizable(false);
        this.view3.setLocationRelativeTo(null);
    }

    /**
     * A method for the order window.
     */
    public void orderWindow() {
        this.view4.setTitle("Order");
        this.view4.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.view4.pack();
        this.view4.setVisible(true);
        this.view4.setResizable(false);
        this.view4.setLocationRelativeTo(null);
    }

}
