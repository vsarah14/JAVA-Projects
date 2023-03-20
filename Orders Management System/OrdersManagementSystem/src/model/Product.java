package model;

public class Product {

    private int productId;
    private String nameP;
    private int price;
    private int quantity;

    /**
     * Getter for the product id.
     *
     * @return the product id
     */
    public int getProductId() {
        return productId;
    }

    /**
     * Setter for the product id.
     *
     * @param productId - product id
     */
    public void setProductId(int productId) {
        this.productId = productId;
    }

    /**
     * Getter for the name.
     *
     * @return - the name of the product
     */
    public String getNameP() {
        return nameP;
    }

    /**
     * Setter fot the name.
     *
     * @param nameP - the name of the product
     */
    public void setNameP(String nameP) {
        this.nameP = nameP;
    }

    /**
     * Getter for the price.
     *
     * @return - the price of the product
     */
    public int getPrice() {
        return price;
    }

    /**
     * Setter for the price.
     *
     * @param price - the price of the product
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * Getter for the quantity.
     *
     * @return - the quantity of the product
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * The setter for the quantity.
     *
     * @param quantity - the quantity of the product
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * The constructor for the product.
     *
     * @param productId - the product id
     * @param name      - the name
     * @param price     - the price
     * @param quantity  - the quantity
     */
    public Product(int productId, String name, int price, int quantity) {
        this.productId = productId;
        this.nameP = name;
        this.price = price;
        this.quantity = quantity;
    }

}
