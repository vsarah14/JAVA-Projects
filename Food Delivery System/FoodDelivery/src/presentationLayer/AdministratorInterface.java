package presentationLayer;

import businessLayer.BaseProduct;
import businessLayer.CompositeProduct;
import businessLayer.DeliveryService;
import businessLayer.MenuItem;
import controller.Serializator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AdministratorInterface extends JFrame {

    private DeliveryService service = DeliveryService.GetDeliveryService();
    private List<MenuItem> importedProducts;
    private ArrayList<MenuItem> combination = new ArrayList<>();
    private JTable table;
    private JScrollPane scroll;
    private DefaultTableModel model;
    private JTextField titleField;
    private JTextField ratingField;
    private JTextField caloriesField;
    private JTextField fatField;
    private JTextField sodiumField;
    private JTextField priceField;
    private JTextField proteinField;
    private JLabel titleLabel;
    private JLabel ratingLabel;
    private JLabel caloriesLabel;
    private JLabel proteinLabel;
    private JLabel sodiumLabel;
    private JLabel fatLabel;
    private JLabel priceLabel;
    private JButton combineButton;
    private JButton addButton;
    private JButton removeButton;
    private JButton addCombination;
    private JButton modifyButton;
    private JButton generateButton;

    public AdministratorInterface() {

        setPreferredSize(new Dimension(900, 700));
        setLayout(null);
        scroll = new JScrollPane();
        scroll.setBounds(350, 10, 500, 400);
        add(scroll);
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

        if (this.importedProducts == null || this.importedProducts.size() == 0) {
            readingFile();
        }

        model.addRow(new Object[]{importedProducts.get(0).getTitle(), importedProducts.get(0).getRating(), importedProducts.get(0).getCalories(), importedProducts.get(0).getProtein(), importedProducts.get(0).getFat(), importedProducts.get(0).getSodium(), importedProducts.get(0).getPrice()});
        service.addMenuItem(importedProducts.get(0));
        for (int i = 1; i < importedProducts.size(); i++) {
            int count = 0;
            for (int j = 0; j < service.getMenu().size(); j++) {
                if (importedProducts.get(i).getTitle().equals(service.getMenu().get(j).getTitle())) {
                    count++;
                }
            }
            if (count == 0) {
                model.addRow(new Object[]{importedProducts.get(i).getTitle(), importedProducts.get(i).getRating(), importedProducts.get(i).getCalories(), importedProducts.get(i).getProtein(), importedProducts.get(i).getFat(), importedProducts.get(i).getSodium(), importedProducts.get(i).getPrice()});
                service.addMenuItem(importedProducts.get(i));
            }
        }
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
        combineButton = new JButton("Combine");
        combineButton.setBounds(80, 555, 155, 40);
        add(combineButton);
        addButton = new JButton("Add");
        addButton.setBounds(80, 455, 155, 40);
        add(addButton);
        removeButton = new JButton("Remove");
        removeButton.setBounds(270, 455, 155, 40);
        add(removeButton);
        addCombination = new JButton("Add combination");
        addCombination.setBounds(270, 555, 155, 40);
        add(addCombination);
        modifyButton = new JButton("Modify");
        modifyButton.setBounds(460, 455, 155, 40);
        add(modifyButton);
        generateButton = new JButton("Reports");
        generateButton.setBounds(460, 555, 155, 40);
        add(generateButton);

        addButton.addActionListener(e -> getInfo());
        removeButton.addActionListener(e -> deleteInfo());
        addCombination.addActionListener(e -> combine1());
        combineButton.addActionListener(e -> combine());
        modifyButton.addActionListener(e -> modifyInfo());
        generateButton.addActionListener(e -> generateReport());
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

    public MenuItem returnSelectedBaseProduct() {
        String title = table.getValueAt(table.getSelectedRow(), 0).toString();
        float rating = Float.parseFloat(table.getValueAt(table.getSelectedRow(), 1).toString());
        int calories = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 2).toString());
        int protein = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 3).toString());
        int fat = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 4).toString());
        int sodium = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 5).toString());
        int price = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 6).toString());
        MenuItem newItem = new BaseProduct(title, rating, calories, protein, fat, sodium, price);
        return newItem;
    }

    public void getInfo() {
        BaseProduct newItem = returnBaseProduct();
        service.addMenuItem(newItem);
        Serializator.serialize(service);
        model.addRow(new Object[]{newItem.getTitle(), newItem.getRating(), newItem.getCalories(), newItem.getProtein(), newItem.getFat(), newItem.getSodium(), newItem.getPrice()});
    }

    public void deleteInfo() {
        MenuItem item = returnSelectedBaseProduct();
        for (int i = 0; i < service.getMenu().size(); i++) {
            if (service.getMenu().get(i).getTitle().equals(item.getTitle())) {
                service.deleteMenuItem(service.getMenu().get(i));
            }
        }
        Serializator.serialize(service);
        model.removeRow(table.getSelectedRow());
    }

    public void combine1() {
        MenuItem newItem = returnSelectedBaseProduct();
        combination.add(newItem);
    }

    public void combine() {
        String title = titleField.getText();
        MenuItem c = new CompositeProduct(title, combination);
        MenuItem composite = c.computeValues(title, combination);
        service.addMenuItem(composite);
        Serializator.serialize(service);
        model.addRow(new Object[]{composite.getTitle(), composite.getRating(), composite.getCalories(), composite.getProtein(), composite.getFat(), composite.getSodium(), composite.getPrice()});
    }

    public void modifyInfo() {
        MenuItem item = returnSelectedBaseProduct();
        BaseProduct newItem = returnBaseProduct();
        int ok = 0;
        for (int i = 0; i < service.getMenu().size(); i++) {
            if (service.getMenu().get(i).getTitle().equals(item.getTitle()) && service.getMenu().get(i).getPrice() == item.getPrice()) {
                ok = i;
            }
        }
        service.modifyMenuItem(service.getMenu().get(ok), newItem);
        Serializator.serialize(service);
        int rowCount = model.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }
        for (int i = 0; i < service.getMenu().size(); i++) {
            model.addRow(new Object[]{service.getMenu().get(i).getTitle(), service.getMenu().get(i).getRating(), service.getMenu().get(i).getCalories(), service.getMenu().get(i).getProtein(), service.getMenu().get(i).getFat(), service.getMenu().get(i).getSodium(), service.getMenu().get(i).getPrice()});
        }
    }

    public void generateReport() {
        service.generateReport1(9, 15);
        service.generateReport2();
        service.generateReport3(20);
        service.generateReport4(13);
    }

    public void readingFile() {
        Path path = Paths.get("C:\\Users\\voicu\\OneDrive\\Desktop\\SD\\FoodDelivery\\products.csv");
        Pattern pattern = Pattern.compile(",");
        if (Files.exists(path)) {
            try (Stream<String> stream = Files.lines(path)) {
                importedProducts = stream.skip(1).map(line -> {
                    String[] arr = pattern.split(line);
                    return new BaseProduct(
                            arr[0],
                            Float.parseFloat(arr[1]),
                            Integer.parseInt(arr[2]),
                            Integer.parseInt(arr[3]),
                            Integer.parseInt(arr[4]),
                            Integer.parseInt(arr[5]),
                            Integer.parseInt(arr[6]));
                }).collect(Collectors.toList());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("File does not exist");
        }
    }
}
