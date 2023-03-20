package presentationLayer;

import businessLayer.*;
import businessLayer.MenuItem;
import controller.FileWriter1;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class ClientInterface extends JFrame {

    private DeliveryService service = DeliveryService.GetDeliveryService();
    private List<MenuItem> products = new ArrayList<>();
    private ArrayList<MenuItem> orderedProducts = new ArrayList<>();
    ArrayList<Order> orderList = new ArrayList<Order>();
    private String text = "";
    private JTable table;
    private JScrollPane scroll;
    private DefaultTableModel model;
    private JButton updateButton;
    private JButton searchButton;
    private JButton orderButton;
    private JButton addButton;
    private JTextField titleField;
    private JTextField ratingField;
    private JTextField caloriesField;
    private JTextField fatField;
    private JTextField sodiumField;
    private JTextField priceField;
    private JTextField proteinField;
    private JTextField dateField;
    private JTextField hourField;
    private JLabel titleLabel;
    private JLabel ratingLabel;
    private JLabel caloriesLabel;
    private JLabel proteinLabel;
    private JLabel sodiumLabel;
    private JLabel fatLabel;
    private JLabel priceLabel;
    private JLabel dateLabel;
    private JLabel hourLabel;
    private JScrollPane logScrollPane;
    private JTextArea logsField;

    public ClientInterface() {
        setPreferredSize(new Dimension(900, 700));
        setLayout(null);
        scroll = new JScrollPane();
        scroll.setBounds(350, 10, 500, 400);
        add(scroll);
        logScrollPane = new JScrollPane(logsField);
        logScrollPane.setBounds(505, 500, 300, 150);
        logsField = new JTextArea();
        logsField.setEditable(false);
        logScrollPane.setViewportView(logsField);
        add(logScrollPane);
        logsField.setText("");
        model = new DefaultTableModel();
        table = new JTable(model);
        model.addColumn("Title");
        model.addColumn("Rating");
        model.addColumn("Calories");
        model.addColumn("Protein");
        model.addColumn("Fat");
        model.addColumn("Sodium");
        model.addColumn("Price");
        scroll.setViewportView(table);
        titleField = new JTextField();
        titleField.setBounds(100, 30, 100, 25);
        add(titleField);
        ratingField = new JTextField();
        ratingField.setBounds(100, 80, 100, 25);
        add(ratingField);
        caloriesField = new JTextField();
        caloriesField.setBounds(100, 130, 100, 25);
        add(caloriesField);
        fatField = new JTextField();
        fatField.setBounds(100, 230, 100, 25);
        add(fatField);
        sodiumField = new JTextField();
        sodiumField.setBounds(100, 280, 100, 25);
        add(sodiumField);
        priceField = new JTextField();
        priceField.setBounds(100, 330, 100, 25);
        add(priceField);
        proteinField = new JTextField();
        proteinField.setBounds(100, 180, 100, 25);
        add(proteinField);
        dateField = new JTextField();
        dateField.setBounds(350, 500, 100, 25);
        add(dateField);
        hourField = new JTextField();
        hourField.setBounds(350, 600, 100, 25);
        add(hourField);
        titleLabel = new JLabel("Title:");
        titleLabel.setBounds(40, 30, 100, 25);
        add(titleLabel);
        ratingLabel = new JLabel("Rating:");
        ratingLabel.setBounds(40, 80, 100, 25);
        add(ratingLabel);
        caloriesLabel = new JLabel("Calories:");
        caloriesLabel.setBounds(40, 130, 100, 25);
        add(caloriesLabel);
        proteinLabel = new JLabel("Protein:");
        proteinLabel.setBounds(40, 180, 100, 25);
        add(proteinLabel);
        sodiumLabel = new JLabel("Sodium:");
        sodiumLabel.setBounds(40, 280, 100, 25);
        add(sodiumLabel);
        fatLabel = new JLabel("Fat:");
        fatLabel.setBounds(40, 230, 100, 25);
        add(fatLabel);
        priceLabel = new JLabel("Price:");
        priceLabel.setBounds(40, 330, 100, 25);
        add(priceLabel);
        dateLabel = new JLabel("Date:");
        dateLabel.setBounds(350, 450, 100, 25);
        add(dateLabel);
        hourLabel = new JLabel("Hour:");
        hourLabel.setBounds(350, 550, 100, 25);
        add(hourLabel);
        updateButton = new JButton("View Menu");
        updateButton.setBounds(70, 440, 155, 40);
        add(updateButton);
        searchButton = new JButton("Search");
        searchButton.setBounds(70, 490, 155, 40);
        add(searchButton);
        orderButton = new JButton("Order");
        orderButton.setBounds(660, 440, 155, 40);
        add(orderButton);
        addButton = new JButton("Add product");
        addButton.setBounds(500, 440, 155, 40);
        add(addButton);

        updateButton.addActionListener(e -> update());
        searchButton.addActionListener(e -> search());
        orderButton.addActionListener(e -> order());
        addButton.addActionListener(e -> addProduct());
    }

    public BaseProduct returnBaseProduct() {
        String title = titleField.getText();
        float rating = Float.parseFloat(ratingField.getText());
        int calories = Integer.parseInt(caloriesField.getText());
        int protein = Integer.parseInt(proteinField.getText());
        int fat = Integer.parseInt(fatField.getText());
        int sodium = Integer.parseInt(sodiumField.getText());
        int price = Integer.parseInt(priceField.getText());
        BaseProduct newItem = new BaseProduct(title, rating, calories, protein, fat, sodium, price);
        return newItem;
    }

    public BaseProduct returnSelectedBaseProduct() {
        String title = table.getValueAt(table.getSelectedRow(), 0).toString();
        float rating = Float.parseFloat(table.getValueAt(table.getSelectedRow(), 1).toString());
        int calories = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 2).toString());
        int protein = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 3).toString());
        int fat = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 4).toString());
        int sodium = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 5).toString());
        int price = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 6).toString());
        BaseProduct newItem = new BaseProduct(title, rating, calories, protein, fat, sodium, price);
        return newItem;
    }

    public void update() {
        for (int i = 0; i < service.getMenu().size(); i++) {
            model.addRow(new Object[]{service.getMenu().get(i).getTitle(), service.getMenu().get(i).getRating(), service.getMenu().get(i).getCalories(), service.getMenu().get(i).getProtein(), service.getMenu().get(i).getFat(), service.getMenu().get(i).getSodium(), service.getMenu().get(i).getPrice()});
            products.add(service.getMenu().get(i));
        }
    }

    public void search() {
        List<MenuItem> newProducts = new ArrayList<MenuItem>();
        if (!titleField.getText().equals("")) {
            newProducts = products.stream().filter(p -> (p.getTitle().contains(titleField.getText()))).collect(Collectors.toList());
        } else if (!priceField.getText().equals("")) {
            newProducts = products.stream().filter(p -> (priceField.getText().equals(String.valueOf(p.getPrice())))).collect(Collectors.toList());
        } else if (!ratingField.getText().equals("")) {
            newProducts = products.stream().filter(p -> (ratingField.getText().equals(String.valueOf(p.getRating())))).collect(Collectors.toList());
        } else if (!caloriesField.getText().equals("")) {
            newProducts = products.stream().filter(p -> (caloriesField.getText().equals(String.valueOf(p.getCalories())))).collect(Collectors.toList());
        } else if (!proteinField.getText().equals("")) {
            newProducts = products.stream().filter(p -> (proteinField.getText().equals(String.valueOf(p.getProtein())))).collect(Collectors.toList());
        } else if (!fatField.getText().equals("")) {
            newProducts = products.stream().filter(p -> (fatField.getText().equals(String.valueOf(p.getFat())))).collect(Collectors.toList());
        } else if (!sodiumField.getText().equals("")) {
            newProducts = products.stream().filter(p -> (sodiumField.getText().equals(String.valueOf(p.getSodium())))).collect(Collectors.toList());
        }
        int rowCount = model.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }
        for (int i = 0; i < newProducts.size(); i++) {
            model.addRow(new Object[]{newProducts.get(i).getTitle(), newProducts.get(i).getRating(), newProducts.get(i).getCalories(), newProducts.get(i).getProtein(), newProducts.get(i).getFat(), newProducts.get(i).getSodium(), newProducts.get(i).getPrice()});
        }
    }

    public void addProduct() {
        MenuItem newItem = returnSelectedBaseProduct();
        orderedProducts.add(newItem);
        text += "Name: " + newItem.getTitle() + ", Price: " + newItem.getPrice() + "\n";
        logsField.setText(text);
    }

    public void order() {
        String dateString = dateField.getText();
        StringTokenizer s = new StringTokenizer(dateString, "/");
        int day = Integer.parseInt(s.nextToken());
        int month = Integer.parseInt(s.nextToken());
        int year = Integer.parseInt(s.nextToken());
        int time = Integer.parseInt(hourField.getText());
        NewDate date = new NewDate(day, month, year);
        Order order = new Order(date, time);
        service.createOrder(order, orderedProducts);
        orderList.add(order);
        FileWriter1 f = new FileWriter1();
        f.generateBill(order, orderedProducts);
        text = " ";
        logsField.setText(text);
        orderedProducts = new ArrayList<>();

    }
}
