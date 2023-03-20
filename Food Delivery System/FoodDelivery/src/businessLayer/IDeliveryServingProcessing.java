package businessLayer;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public interface IDeliveryServingProcessing {
    /**
     * Adds a new item to the menu
     *
     * @param item - the item that the administrator will add
     * @pre item != null
     * @post menu.size = menu.size@pre +1
     */
    public void addMenuItem(MenuItem item);

    /**
     * Deletes a selected item from the menu
     *
     * @param item - the item that the admin will delete
     * @post menu.size = menu.size@pre -1
     */
    public void deleteMenuItem(MenuItem item);

    /**
     * Modifies an item from the menu
     *
     * @param item    - the old item
     * @param newItem - the modified item
     * @pre item != null
     * @pre newItem != null
     * @post menu.size = menu.size@pre
     */
    public void modifyMenuItem(MenuItem item, MenuItem newItem);

    /**
     * Creates an order
     *
     * @param order    - order
     * @param products - products
     * @pre order != null
     * @pre products != null
     * @post orders != null
     */
    public void createOrder(Order order, ArrayList<MenuItem> products);

    /**
     * Generates a report regarding the orders that are made between an interval
     *
     * @param startHour - start hour for the interval
     * @param endHour   - end hour for the interval
     * @throws FileNotFoundException - exception
     * @pre startHour != 0
     * @pre endHour != 0
     */
    public void generateReport1(int startHour, int endHour) throws FileNotFoundException;

    /**
     * Generates a report regarding the products that have been ordered multiple times
     */
    public void generateReport2();

    /**
     * Generates a report regarding the orders that have a greater price than the parameter
     *
     * @param price - specified price
     * @pre price != 0
     */
    public void generateReport3(int price);

    /**
     * Generates a report regarding the orders that were ordered on a specific day
     *
     * @param day - specified day
     * @pre day != 0
     */
    public void generateReport4(int day);

}
