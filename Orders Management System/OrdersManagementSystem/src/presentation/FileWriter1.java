package presentation;

import model.Client;
import model.Orders;
import model.Product;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileWriter1 {

    /**
     * A method that generates a bill in a text file.
     *
     * @param order   - the order
     * @param product - the product that was ordered
     * @param client  - the client that ordered the product
     */
    public void generateBill(Orders order, Product product, Client client) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("bill.txt", false));
            writer.append("Order id: " + order.getOrderId() + "\n");
            writer.append("Client name: " + client.getName() + "\n");
            writer.append("Product name: " + product.getNameP() + "\n");
            writer.append("Quantity: " + order.getQuantity() + "\n");
            writer.append("Total price: " + order.getQuantity() * product.getPrice() + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occured");
            e.printStackTrace();
        }
    }
}
