package businessLayer;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class DeliveryService extends Observable implements IDeliveryServingProcessing, Serializable {

    private ArrayList<MenuItem> menu = new ArrayList<>();
    private Map<Order, ArrayList<MenuItem>> orderDetails = new HashMap<Order, ArrayList<MenuItem>>();
    private ArrayList<Order> orders = new ArrayList<>();
    private static DeliveryService _deliveryService;

    /**
     * Getter - in order to have a single service through the whole application
     *
     * @return the delivery service
     */
    public static DeliveryService GetDeliveryService() {
        if (_deliveryService == null) {
            _deliveryService = new DeliveryService();
        }
        return _deliveryService;
    }

    /**
     * @invariant the assertion that needs to be held throughout the life of this class
     */
    public void wellFormed() {
        assert !menu.contains(null);
    }

    /**
     * Getter for the menu
     *
     * @return the menu
     */
    public ArrayList<MenuItem> getMenu() {
        return menu;
    }

    /**
     * Setter fot the menu
     *
     * @param menu - menu items
     */
    public void setMenu(ArrayList<MenuItem> menu) {
        this.menu = menu;
    }

    @Override
    public void addMenuItem(MenuItem item) {
        assert item != null;
        int preCond = menu.size();
        menu.add(item);
        int postCond = menu.size();
        assert preCond + 1 == postCond;
    }

    @Override
    public void deleteMenuItem(MenuItem item) {
        int preCond = menu.size();
        menu.remove(item);
        int postCont = menu.size();
        assert preCond - 1 == postCont;
    }

    @Override
    public void modifyMenuItem(MenuItem item, MenuItem newItem) {
        assert item != null;
        assert newItem != null;
        int preCond = menu.size();
        item.setTitle(newItem.getTitle());
        item.setPrice(newItem.getPrice());
        item.setCalories(newItem.getCalories());
        item.setFat(newItem.getFat());
        item.setProtein(newItem.getProtein());
        item.setRating(newItem.getRating());
        item.setSodium(newItem.getSodium());
        int postCond = menu.size();
        assert preCond == postCond;
    }

    @Override
    public void createOrder(Order order, ArrayList<MenuItem> products) {
        assert order != null;
        assert products != null;
        orderDetails.put(order, products);
        orders.add(order);
        setChanged();
        notifyObservers(orders);
        assert orders != null;
    }

    @Override
    public void generateReport1(int startHour, int endHour) {
        assert startHour != 0;
        assert endHour != 0;

        List<Order> newOrders = new ArrayList<>();
        newOrders = orders.stream().filter(o -> (o.getOrderTime() > startHour && o.getOrderTime() < endHour)).collect(Collectors.toList());

        try {
            FileWriter fw = new FileWriter("report1.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            pw.println("Orders that were ordered between " + startHour + " and " + endHour + ": ");
            for (int i = 0; i < newOrders.size(); i++) {
                pw.println("Order id: " + newOrders.get(i).getOrderId() + " Order time: " + newOrders.get(i).getOrderTime() + " Order date: " + newOrders.get(i).getOrderDate().getDay() + "-" + newOrders.get(i).getOrderDate().getMonth() + "-" + newOrders.get(i).getOrderDate().getYear());
            }
            pw.println();
            pw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void generateReport2() {
        ArrayList<MenuItem> orderedProducts = new ArrayList<>();
        for (Map.Entry<Order, ArrayList<MenuItem>> entry : orderDetails.entrySet()) {
            ArrayList<MenuItem> products = entry.getValue();
            for (int i = 0; i < products.size(); i++) {
                orderedProducts.add(products.get(i));
                orderedProducts.get(i).setCount(1);
            }
        }
        for (int i = 0; i < orderedProducts.size() - 1; i++) {
            for (int j = i + 1; j < orderedProducts.size(); j++) {
                if (orderedProducts.get(i).getTitle().equals(orderedProducts.get(j).getTitle())) {
                    orderedProducts.get(i).setCount(orderedProducts.get(i).getCount() + 1);
                    orderedProducts.remove(j);
                }
            }
        }
        List<MenuItem> repeatedProduct = orderedProducts.stream().filter(o -> o.getCount() > 1).collect(Collectors.toList());
        try {
            FileWriter fw = new FileWriter("report2.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            for (int i = 0; i < repeatedProduct.size(); i++) {
                pw.println(repeatedProduct.get(i).getTitle() + "  was ordered " + repeatedProduct.get(i).getCount() + " times.");
            }
            pw.println();
            pw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void generateReport3(int price) {
        assert price != 0;
        List<Order> newOrders = new ArrayList<>();
        ArrayList<MenuItem> orderedProducts = new ArrayList<>();
        if (orders.size() > 1) {
            for (Map.Entry<Order, ArrayList<MenuItem>> entry : orderDetails.entrySet()) {
                ArrayList<MenuItem> products = entry.getValue();
                int p = 0;
                for (int i = 0; i < products.size(); i++) {
                    p += products.get(i).getPrice();
                }
                if (p > price) {
                    newOrders.add(entry.getKey());
                    try {
                        FileWriter fw = new FileWriter("report3.txt", true);
                        BufferedWriter bw = new BufferedWriter(fw);
                        PrintWriter pw = new PrintWriter(bw);
                        pw.println("Order id: " + entry.getKey().getOrderId());
                        pw.println("Price: " + p);
                        pw.println();
                        pw.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    public void generateReport4(int day) {
        assert day != 0;
        List<MenuItem> newProducts = new ArrayList<>();
        Map<Order, ArrayList<MenuItem>> newOrders = new HashMap<Order, ArrayList<MenuItem>>();
        newOrders = orderDetails.entrySet().stream().filter(o -> o.getKey().getOrderDate().getDay() == day).collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue()));
        try {
            FileWriter fw = new FileWriter("report4.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            for (Map.Entry<Order, ArrayList<MenuItem>> entry : newOrders.entrySet()) {
                pw.println("Order id: " + entry.getKey().getOrderId());
                ArrayList<MenuItem> products = entry.getValue();
                for (int i = 0; i < products.size(); i++) {
                    newProducts.add(products.get(i));
                    newProducts.get(i).setCount(1);
                }
            }
            for (int i = 0; i < newProducts.size() - 1; i++) {
                for (int j = i + 1; j < newProducts.size(); j++) {
                    if (newProducts.get(i).getTitle().equals(newProducts.get(j).getTitle())) {
                        newProducts.get(i).setCount(newProducts.get(i).getCount() + 1);
                        newProducts.remove(j);
                    }
                }
            }
            pw.println("Products are: ");
            for (int i = 0; i < newProducts.size(); i++) {
                pw.println(newProducts.get(i).getTitle() + ", number of orders: " + newProducts.get(i).getCount());
            }
            pw.println();
            pw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
