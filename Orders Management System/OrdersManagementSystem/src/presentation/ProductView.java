package presentation;

import dataAccessLayer.ProductDAO;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;

public class ProductView extends JFrame {

    private JTextField idField;
    private JTextField nameField;
    private JTextField priceField;
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
     * The interface for the products.
     *
     * @param con - connection
     * @throws SQLException - exception for sql database
     */
    public ProductView(Connection con) throws SQLException {

        ProductView view = this;
        ProductDAO productDAO = new ProductDAO(con, view);

        setPreferredSize(new Dimension(800, 500));
        setLayout(null);

        idField = new JTextField();
        idField.setBounds(85, 20, 180, 30);
        add(idField);
        nameField = new JTextField();
        nameField.setBounds(85, 70, 180, 30);
        add(nameField);
        priceField = new JTextField();
        priceField.setBounds(85, 130, 180, 30);
        add(priceField);
        quantityField = new JTextField();
        quantityField.setBounds(85, 180, 180, 30);
        add(quantityField);
        messageField = new JTextField();
        messageField.setBounds(100, 420, 600, 30);
        add(messageField);
        messageField.setEnabled(false);
        messageField.setDisabledTextColor(Color.black);
        idLabel = new JLabel("Product id:");
        idLabel.setBounds(20, 20, 100, 25);
        add(idLabel);
        nameLabel = new JLabel("Name:");
        nameLabel.setBounds(25, 70, 100, 25);
        add(nameLabel);
        priceLabel = new JLabel("Price:");
        priceLabel.setBounds(25, 130, 100, 25);
        add(priceLabel);
        quantityLabel = new JLabel("Quantity:");
        quantityLabel.setBounds(25, 180, 100, 25);
        add(quantityLabel);
        tableButton = new JButton("view table");
        tableButton.setBounds(345, 35, 115, 45);
        add(tableButton);
        addButton = new JButton("add");
        addButton.setBounds(515, 35, 115, 45);
        add(addButton);
        deleteButton = new JButton("delete");
        deleteButton.setBounds(345, 125, 115, 45);
        add(deleteButton);
        updateButton = new JButton("update");
        updateButton.setBounds(520, 125, 115, 45);
        add(updateButton);

        tableButton.addActionListener(e -> productDAO.viewTable());
        addButton.addActionListener(e -> productDAO.insertProduct());
        deleteButton.addActionListener(e -> productDAO.deleteProduct());
        updateButton.addActionListener(e -> productDAO.updateProduct());
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
     * Getter for the field containing the name.
     *
     * @return - the field with the name
     */
    public JTextField getNameField() {
        return nameField;
    }

    /**
     * Getter for the field containing the price.
     *
     * @return - the field with the price
     */
    public JTextField getPriceField() {
        return priceField;
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
     * A method that displays a success/error message.
     *
     * @param message - the message that will be displayed
     */
    public void setResult(String message) {
        this.messageField.setText(message);
    }
}
