package dataAccessLayer;

import model.Client;
import model.Orders;
import model.Product;
import presentation.ClientView;
import presentation.FileWriter1;
import presentation.OrderView;
import presentation.ProductView;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class OrderDAO extends AbstractDAO<Orders> {

    private static Connection orderCon;
    private Statement orderStatement;
    private OrderView orderView;
    private ProductView productView;
    private ClientView clientView;

    /**
     * The constructor for OrderDAO.
     *
     * @param connection - connection
     * @param orderView  - interface for the order operations
     * @throws SQLException - exception for
     */
    public OrderDAO(Connection connection, OrderView orderView) throws SQLException {
        this.orderCon = connection;
        this.orderView = orderView;
        System.out.println(this.orderCon);
        this.orderStatement = connection.createStatement();
    }

    /**
     * A method that find all the orders.
     *
     * @return - a list with all the orders
     */
    public ArrayList<Orders> findAll() {
        String query = createSelectAll();
        ArrayList<Orders> allOrders = new ArrayList<>();
        try {

            ResultSet rs = orderStatement.executeQuery(query);
            while (rs.next()) {
                Orders orders = new Orders(rs.getInt("orderId"), rs.getInt("clientId"), rs.getInt("productId"), rs.getInt("quantity"));
                allOrders.add(orders);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allOrders;
    }

    /**
     * A method that find the order that we are looking for by knowing its id.
     *
     * @param orderId - the id of the order that we are looking for
     * @return - the order that we are looking for
     */
    public Orders findById(int orderId) {
        ArrayList<Orders> allOrders = findAll();
        Orders foundOrders = null;
        for (Orders o : allOrders) {
            if (o.getOrderId() == orderId) {
                foundOrders = o;
            }
        }
        return foundOrders;
    }

    /**
     * A method that returns a list of the ids of all the clients.
     *
     * @return - the ids of all the clients
     * @throws SQLException - exception for sql query
     */
    public ArrayList<Integer> getClients() throws SQLException {
        String query = "SELECT clientId FROM Client";
        ArrayList<Integer> ids = new ArrayList<>();
        ResultSet rs = orderStatement.executeQuery(query);
        while (rs.next()) {
            ids.add(rs.getInt("clientId"));
        }
        return ids;
    }

    /**
     * A method that returns a list of ids of all the products.
     *
     * @return - the ids of all the products
     * @throws SQLException - exception for sql query
     */
    public ArrayList<Integer> getProducts() throws SQLException {
        String query = "SELECT productId FROM Product";
        ArrayList<Integer> ids = new ArrayList<>();
        ResultSet rs = orderStatement.executeQuery(query);
        while (rs.next()) {
            ids.add(rs.getInt("productId"));
        }
        return ids;
    }

    /**
     * A method that inserts an order.
     *
     * @throws SQLException - exception for sql database
     */
    public void insertOrder() throws SQLException {
        int oId = Integer.parseInt(orderView.getIdField().getText());
        int cId = orderView.getClientId();
        int pId = orderView.getProductId();
        int quantity = Integer.parseInt(orderView.getQuantityField().getText());
        ProductDAO productDAO = new ProductDAO(orderCon, productView);
        ClientDAO clientDAO = new ClientDAO(orderCon, clientView);
        Product searchProduct = productDAO.findById(pId);
        Client searchClient = clientDAO.findById(cId);
        int quantityP = searchProduct.getQuantity();
        if (quantity <= quantityP) {
            searchProduct.setQuantity(quantityP - quantity);
            update(searchProduct);
            Orders newOrders = new Orders(oId, cId, pId, quantity);
            insert(newOrders);
            FileWriter1 f = new FileWriter1();
            f.generateBill(newOrders, searchProduct, searchClient);
            orderView.setResult("The order has been inserted.");
        } else {
            orderView.setResult("The stock doesn't have that many products.");
            System.out.println("The stock doesn't have that many products.");
        }
    }

    /**
     * A method that deletes an order.
     *
     * @throws SQLException - exception for sql database
     */
    public void deleteOrder() throws SQLException {
        int oId = Integer.parseInt(orderView.getIdField().getText());
        int pId = orderView.getProductId();
        Orders newOrders = findById(oId);
        int quantity = newOrders.getQuantity();
        ProductDAO productDAO = new ProductDAO(orderCon, productView);
        Product searchProduct = productDAO.findById(pId);
        int quantityP = searchProduct.getQuantity();
        searchProduct.setQuantity(quantityP + quantity);
        update(searchProduct);
        delete(newOrders);
        orderView.setResult("The order has been deleted.");
    }

    /**
     * A method that updates an order.
     *
     * @throws SQLException - exception for sql database
     */
    public void updateOrder() throws SQLException {
        int oId = Integer.parseInt(orderView.getIdField().getText());
        int cId = orderView.getClientId();
        int pId = orderView.getProductId();
        int quantity = Integer.parseInt(orderView.getQuantityField().getText());
        Orders newOrders = findById(oId);
        int productId = newOrders.getProductId();
        int quantityO = newOrders.getQuantity();
        ProductDAO productDAO = new ProductDAO(orderCon, productView);
        ClientDAO clientDAO = new ClientDAO(orderCon, clientView);
        Product searchProduct = productDAO.findById(pId);
        Client searchClient = clientDAO.findById(cId);
        int quantityP = searchProduct.getQuantity();
        searchProduct.setQuantity(quantityP + quantityO - quantity);
        update(searchProduct);
        Orders finalOrders = new Orders(oId, cId, pId, quantity);
        update(finalOrders);
        FileWriter1 f = new FileWriter1();
        f.generateBill(finalOrders, searchProduct, searchClient);
        orderView.setResult("The order has been updated.");
    }

    /**
     * A method that displays the table.
     */
    public void viewTable() {
        ArrayList<Orders> orders = findAll();
        for (Orders order : orders) {
            System.out.println(order.getOrderId());
        }
        JScrollPane scroll = new JScrollPane();
        JTable table = new JTable();
        scroll.setBounds(100, 250, 600, 150);
        orderView.getContentPane().add(scroll);
        table = createTable(orders);
        scroll.setViewportView(table);
        orderView.setResult("The orders have been generated.");
    }

}
