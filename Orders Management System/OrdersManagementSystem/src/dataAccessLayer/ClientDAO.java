package dataAccessLayer;

import businessLayer.ClientBLL;
import model.Client;
import presentation.ClientView;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class ClientDAO extends AbstractDAO<Client> {

    private static Connection clientCon;
    private Statement clientStatement;
    private ClientView clientView;

    public ClientDAO() {

    }

    /**
     * The constructor for ClientDAO
     *
     * @param connection - connection
     * @param clientView - interface for client operations
     * @throws SQLException - exception regarding sql database
     */
    public ClientDAO(Connection connection, ClientView clientView) throws SQLException {
        this.clientCon = connection;
        this.clientView = clientView;
        this.clientStatement = connection.createStatement();
    }

    /**
     * A method that puts all the clients in a list.
     *
     * @return - the list of all the clients
     */
    public ArrayList<Client> findAll() {
        String query = createSelectAll();
        ArrayList<Client> allClients = new ArrayList<>();
        try {
            ResultSet rs = clientStatement.executeQuery(query);
            while (rs.next()) {
                Client client = new Client(rs.getInt("clientId"), rs.getString("name"), rs.getString("email"));
                allClients.add(client);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allClients;
    }

    /**
     * A method
     *
     * @param clientId - client id
     * @return - the client that has the id that we were looking for
     */
    public Client findById(int clientId) {
        ArrayList<Client> allClients = findAll();
        Client foundClient = null;
        for (Client c : allClients) {
            if (c.getClientId() == clientId) {
                foundClient = c;
            }
        }
        return foundClient;
    }

    /**
     * A method that inserts a client to the table.
     */
    public void insertClient() {
        int id = Integer.parseInt(clientView.getIdField().getText());
        String name = clientView.getNameField().getText();
        String email = clientView.getMailField().getText();
        ClientBLL cl = new ClientBLL();
        if (cl.validClient(name, email)) {
            Client client = new Client(id, name, email);
            insert(client);
            clientView.setResult("The client has been inserted.");
        } else {
            clientView.setResult("Bad input. Name/Email is incorrect.");
            System.out.println("Bad input. Name/Email is incorrect.");
        }
    }

    /**
     * A method that deletes a client from the table;
     */
    public void deleteClient() {
        int id = Integer.parseInt(clientView.getIdField().getText());
        Client client = findById(id);
        delete(client);
        clientView.setResult("The client has been deleted.");
    }

    /**
     * A method that update a client from the table.
     */
    public void update() {
        int id = Integer.parseInt(clientView.getIdField().getText());
        String name = clientView.getNameField().getText();
        String email = clientView.getMailField().getText();
        Client newClient = findById(id);
        newClient.setName(name);
        newClient.setEmail(email);
        ClientBLL cl = new ClientBLL();
        if (cl.validClient(name, email)) {
            update(newClient);
            clientView.setResult("The client has been updated.");
        } else {
            clientView.setResult("Bad input. Name/Email is incorrect.");
            System.out.println("Bad input. Name/Email is incorrect.");
        }
    }

    /**
     * A method that displays the table.
     */
    public void viewTable() {

        ArrayList<Client> clients = findAll();
        JScrollPane scroll = new JScrollPane();
        JTable table = new JTable();

        scroll.setBounds(100, 250, 600, 150);
        clientView.getContentPane().add(scroll);
        table = createTable(clients);
        scroll.setViewportView(table);
        clientView.setResult("The client have been generated.");
    }

}
