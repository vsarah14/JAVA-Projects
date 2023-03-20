package controller;

import businessLayer.DeliveryService;

import java.io.*;

public class Serializator {
    private static final long serialVersionUID = 1L;

    public static void serialize(DeliveryService service) {
        try {
            FileOutputStream f = new FileOutputStream("service.ser");
            ObjectOutputStream outputStream = new ObjectOutputStream(f);
            outputStream.writeObject(service);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static DeliveryService deserialize() {
        DeliveryService savedService = null;
        try {
            FileInputStream f1 = new FileInputStream("service.ser");
            ObjectInputStream inputStream = new ObjectInputStream(f1);
            savedService = (DeliveryService) inputStream.readObject();
            inputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return savedService;
    }
}
