package dataAccessLayer;

import businessLayer.ProductBLL;
import model.Product;
import presentation.ProductView;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ProductDAO extends AbstractDAO<Product> {

    private static Connection productCon;
    private Statement productStatement;
    private ProductView productView;

    /**
     * The constructor for ProductDAO.
     *
     * @param connection  - connection
     * @param productView - interface for the product operations
     * @throws SQLException - exception for sql database
     */
    public ProductDAO(Connection connection, ProductView productView) throws SQLException {
        this.productCon = connection;
        this.productView = productView;
        this.productStatement = productCon.createStatement();
    }

    /**
     * A method that return the list of products.
     *
     * @return - the list of al the products
     */
    public ArrayList<Product> findAll() {
        String query = createSelectAll();
        ArrayList<Product> allProducts = new ArrayList<>();
        try {
            ResultSet rs = productStatement.executeQuery(query);
            while (rs.next()) {
                Product newProduct = new Product(rs.getInt("productId"), rs.getString("nameP"), rs.getInt("price"), rs.getInt("quantity"));
                allProducts.add(newProduct);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allProducts;
    }

    /**
     * A method that find a product by searching for its id.
     *
     * @param productId - the id of the product that we are looking for
     * @return - the product that we were searching
     */
    public Product findById(int productId) {
        ArrayList<Product> allProducts = findAll();
        Product foundProduct = null;
        for (Product p : allProducts) {
            if (p.getProductId() == productId) {
                foundProduct = p;
            }
        }
        return foundProduct;
    }

    /**
     * A method that inserts a product.
     */
    public void insertProduct() {
        int id = Integer.parseInt(productView.getIdField().getText());
        String name = productView.getNameField().getText();
        int price = Integer.parseInt(productView.getPriceField().getText());
        int quantity = Integer.parseInt(productView.getQuantityField().getText());
        ProductBLL pr = new ProductBLL();
        if (pr.validProduct(name, price, quantity)) {
            Product newProduct = new Product(id, name, price, quantity);
            insert(newProduct);
            productView.setResult("The product has been inserted.");
        } else {
            productView.setResult("Bad input. Name/Price/Quantity is incorrect.");
            System.out.println("Bad input. Name/Price/Quantity is incorrect.");
        }
    }

    /**
     * A method that deletes a product.
     */
    public void deleteProduct() {
        int id = Integer.parseInt(productView.getIdField().getText());
        Product product = findById(id);
        delete(product);
        productView.setResult("The product has been deleted.");
    }

    /**
     * A method that updates a product.
     */
    public void updateProduct() {
        int id = Integer.parseInt(productView.getIdField().getText());
        String name = productView.getNameField().getText();
        int price = Integer.parseInt(productView.getPriceField().getText());
        int quantity = Integer.parseInt(productView.getQuantityField().getText());
        Product product = findById(id);
        product.setNameP(name);
        product.setPrice(price);
        product.setQuantity(quantity);
        ProductBLL pr = new ProductBLL();
        if (pr.validProduct(name, price, quantity)) {
            update(product);
            productView.setResult("The product has been updated.");
        } else {
            productView.setResult("Bad input. Name/Price/Quantity is incorrect.");
            System.out.println("Bad input. Name/Price/Quantity is incorrect.");
        }
    }

    /**
     * A method that generates the table.
     */
    public void viewTable() {

        ArrayList<Product> products = findAll();
        JScrollPane scroll = new JScrollPane();
        JTable table = new JTable();

        scroll.setBounds(100, 250, 600, 150);
        productView.getContentPane().add(scroll);
        table = createTable(products);
        scroll.setViewportView(table);
        productView.setResult("The products have been generated.");

    }

}
