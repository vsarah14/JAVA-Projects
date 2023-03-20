package presentation;

import dataAccessLayer.ClientDAO;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;

public class ClientView extends JFrame {

    private JTextField idField;
    private JTextField nameField;
    private JTextField mailField;
    private JTextField messageField;
    private JLabel idLabel;
    private JLabel nameLabel;
    private JLabel mailLabel;
    private JButton tableButton;
    private JButton addButton;
    private JButton deleteButton;
    private JButton updateButton;

    /**
     * The client interface.
     *
     * @param con - connection to the database
     * @throws SQLException - exception for sql database
     */
    public ClientView(Connection con) throws SQLException {

        ClientView view = this;
        ClientDAO clientDAO = new ClientDAO(con, view);

        setPreferredSize(new Dimension(800, 500));
        setLayout(null);

        idField = new JTextField();
        idField.setBounds(85, 35, 180, 30);
        add(idField);
        nameField = new JTextField();
        nameField.setBounds(85, 85, 180, 30);
        add(nameField);
        mailField = new JTextField();
        mailField.setBounds(85, 135, 180, 30);
        add(mailField);
        messageField = new JTextField();
        messageField.setBounds(100, 420, 600, 30);
        add(messageField);
        messageField.setEnabled(false);
        messageField.setDisabledTextColor(Color.black);
        idLabel = new JLabel("Client id:");
        idLabel.setBounds(20, 35, 100, 25);
        add(idLabel);
        nameLabel = new JLabel("Name:");
        nameLabel.setBounds(25, 85, 100, 25);
        add(nameLabel);
        mailLabel = new JLabel("E-mail:");
        mailLabel.setBounds(25, 135, 100, 25);
        add(mailLabel);
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

        tableButton.addActionListener(e -> clientDAO.viewTable());
        addButton.addActionListener(e -> clientDAO.insertClient());
        deleteButton.addActionListener(e -> clientDAO.deleteClient());
        updateButton.addActionListener(e -> clientDAO.update());
    }

    /**
     * Getter for the field containing the id.
     *
     * @return - the id of the field
     */
    public JTextField getIdField() {
        return idField;
    }

    /**
     * Getter for field containing the name.
     *
     * @return - the name of the field
     */
    public JTextField getNameField() {
        return nameField;
    }

    /**
     * Getter for the field containing the mail.
     *
     * @return - the mail of the field.
     */
    public JTextField getMailField() {
        return mailField;
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
