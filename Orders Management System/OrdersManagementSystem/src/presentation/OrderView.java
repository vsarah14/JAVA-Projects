package presentation;

import dataAccessLayer.OrderDAO;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderView extends JFrame {

    private JTextField idField;
    private JComboBox clientBox;
    private JComboBox productBox;
    private JTextField quantityField;
    private JTextField messageField;
    private JLabel idLabel;
    private JLabel nameLabel;
    private JLabel priceLabel;
    private JLabel quantityLabel;
    private JButton tableButton;
    private JButton addButton;
    private JButton deleteButton;
    private JButton updateButton;

    /**
     * The interface for the order.
     *
     * @param con - connection
     * @throws SQLException - exception for the sql database
     */
    public OrderView(Connection con) throws SQLException {

        OrderView view = this;
        OrderDAO orderDAO = new OrderDAO(con, view);

        setPreferredSize(new Dimension(800, 500));
        setLayout(null);

        idField = new JTextField();
        idField.setBounds(85, 20, 180, 30);
        add(idField);

        ArrayList<Integer> clients = new ArrayList<>();
        ArrayList<Integer> products = new ArrayList<>();
        try {
            clients = orderDAO.getClients();
            products = orderDAO.getProducts();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        clientBox = new JComboBox();
        clientBox.setBounds(85, 70, 180, 30);
        for (int i = 0; i < clients.size(); i++) {
            clientBox.addItem(clients.get(i));
        }
        add(clientBox);
        productBox = new JComboBox();
        productBox.setBounds(85, 130, 180, 30);
        for (int i = 0; i < products.size(); i++) {
            productBox.addItem(products.get(i));
        }
        add(productBox);

        quantityField = new JTextField();
        quantityField.setBounds(85, 180, 180, 30);
        add(quantityField);
        messageField = new JTextField();
        messageField.setBounds(100, 420, 600, 30);
        add(messageField);
        messageField.setEnabled(false);
        messageField.setDisabledTextColor(Color.black);
        idLabel = new JLabel("Order id:");
        idLabel.setBounds(20, 20, 100, 25);
        add(idLabel);
        nameLabel = new JLabel("Client id:");
        nameLabel.setBounds(20, 70, 100, 25);
        add(nameLabel);
        priceLabel = new JLabel("Product id:");
        priceLabel.setBounds(20, 130, 100, 25);
        add(priceLabel);
        quantityLabel = new JLabel("Quantity:");
        quantityLabel.setBounds(25, 180, 100, 25);
        add(quantityLabel);
        tableButton = new JButton("View table");
        tableButton.setBounds(345, 35, 115, 45);
        add(tableButton);
        addButton = new JButton("Add");
        addButton.setBounds(515, 35, 115, 45);
        add(addButton);
        deleteButton = new JButton("Delete");
        deleteButton.setBounds(345, 125, 115, 45);
        add(deleteButton);
        updateButton = new JButton("Update");
        updateButton.setBounds(520, 125, 115, 45);
        add(updateButton);

        tableButton.addActionListener(e -> orderDAO.viewTable());
        addButton.addActionListener(e -> {
            try {
                orderDAO.insertOrder();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        deleteButton.addActionListener(e -> {
            try {
                orderDAO.deleteOrder();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        updateButton.addActionListener(e -> {
            try {
                orderDAO.updateOrder();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
    }

    /**
     * Getter for the field containing the id.
     *
     * @return - the field with the id
     */
    public JTextField getIdField() {
        return idField;
    }

    /**
     * Getter for the field containing the quantity.
     *
     * @return - the field with the quantity
     */
    public JTextField getQuantityField() {
        return quantityField;
    }

    /**
     * Getter for the client id.
     *
     * @return - the client id selected in the combo box.
     */
    public int getClientId() {
        return (Integer) clientBox.getSelectedItem();
    }

    /**
     * Getter for the product id.
     *
     * @return - the product id selected in the combo box.
     */
    public int getProductId() {
        return (Integer) productBox.getSelectedItem();
    }

    /**
     * A method that displays a success/error message.
     *
     * @param message - the message that will be displayed
     */
    public void setResult(String message) {
        this.messageField.setText(message);
    }
}
