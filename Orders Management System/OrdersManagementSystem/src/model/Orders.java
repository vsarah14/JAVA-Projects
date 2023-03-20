package model;

public class Orders {

    private int orderId;
    private int clientId;
    private int productId;
    private int quantity;

    /**
     * Getter for the order id.
     *
     * @return - the order id
     */
    public int getOrderId() {
        return orderId;
    }

    /**
     * Setter for the order id.
     *
     * @param orderId - the order id
     */
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    /**
     * Getter for the client id.
     *
     * @return - the client id
     */
    public int getClientId() {
        return clientId;
    }

    /**
     * Setter for the client id.
     *
     * @param clientId - the client id
     */
    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    /**
     * Getter for the product id.
     *
     * @return - the product id
     */
    public int getProductId() {
        return productId;
    }

    /**
     * Setter for the product id.
     *
     * @param productId - the prodct id
     */
    public void setProductId(int productId) {
        this.productId = productId;
    }

    /**
     * Getter for the quantity.
     *
     * @return - the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Setter for the quantity.
     *
     * @param quantity - the quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * The constructor for Orders.
     *
     * @param orderId   - order id
     * @param clientId  - client id
     * @param productId - product id
     * @param quantity  - quantity
     */
    public Orders(int orderId, int clientId, int productId, int quantity) {
        this.orderId = orderId;
        this.clientId = clientId;
        this.productId = productId;
        this.quantity = quantity;
    }

}
