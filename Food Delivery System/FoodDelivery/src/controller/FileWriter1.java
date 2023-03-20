package controller;

import businessLayer.MenuItem;
import businessLayer.Order;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileWriter1 {

    public void generateBill(Order order, ArrayList<MenuItem> menuItems) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("bill.txt", false));
            writer.append("Order id: " + order.getOrderId() + "\n");
            writer.append("Order date: " + order.getOrderDate().getDay() + "-" + order.getOrderDate().getMonth() + "-" + order.getOrderDate().getYear() + "\n");
            writer.append("Order hour: " + order.getOrderTime() + "\n");
            writer.append("Products:" + "\n");
            for (int i = 0; i < menuItems.size(); i++) {
                writer.append("Name: " + menuItems.get(i).getTitle() + ", Price: " + menuItems.get(i).getPrice() + "\n");
            }
            writer.append("Total price: ");
            int price = 0;
            for (int i = 0; i < menuItems.size(); i++) {
                price += menuItems.get(i).getPrice();
            }
            writer.append(String.valueOf(price));
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occured");
            e.printStackTrace();
        }

    }
}
