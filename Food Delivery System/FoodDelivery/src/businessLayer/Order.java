package businessLayer;

public class Order {

    private int orderId;
    private NewDate orderDate;
    private int orderTime;
    private static int id = 0;

    /**
     * Constructor for the order
     *
     * @param orderDate - the date of the order
     * @param orderTime - the time of the order
     */
    public Order(NewDate orderDate, int orderTime) {
        this.orderId = id++;
        this.orderDate = orderDate;
        this.orderTime = orderTime;
    }

    /**
     * Getter for the order id
     *
     * @return orderId
     */
    public int getOrderId() {
        return orderId;
    }

    /**
     * Getter for the date
     *
     * @return orderDate
     */
    public NewDate getOrderDate() {
        return orderDate;
    }

    /**
     * Getter for the time
     *
     * @return orderTime
     */
    public int getOrderTime() {
        return orderTime;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + orderId;
        hash = 31 * hash + orderDate.getDay();
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;
        if (this.getClass() != o.getClass())
            return false;
        Order order = (Order) o;
        return orderId == order.orderId
                && orderDate.equals(order.orderDate);
    }
}
