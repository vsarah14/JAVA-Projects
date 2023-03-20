package businessLayer;

public class ProductBLL {

    /**
     * The constructor for product validation.
     */
    public ProductBLL() {

    }

    /**
     * A method that verifies if a product is valid.
     *
     * @param name     - name of the product
     * @param price    - price of the product
     * @param quantity - quantity of the product
     * @return - true or false
     */
    public boolean validProduct(String name, int price, int quantity) {
        return validateName(name) && validatePrice(price) && validateQuantity(quantity);
    }

    /**
     * A method that validates the name of a product.
     *
     * @param name - name of the product
     * @return - true or false
     */
    public boolean validateName(String name) {
        return name.matches("[ a-zA-Z]+");
    }

    /**
     * A method that validates the price of the product.
     *
     * @param price - price of the product
     * @return - true or false
     */
    public boolean validatePrice(int price) {
        return price > 0;
    }

    /**
     * A method that validates the quantity of the product
     *
     * @param quantity - the quantity of the product
     * @return - true or false
     */
    public boolean validateQuantity(int quantity) {
        return quantity > 0;
    }
}
