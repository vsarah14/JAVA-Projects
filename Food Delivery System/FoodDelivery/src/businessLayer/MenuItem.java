package businessLayer;

import java.util.ArrayList;

public abstract class MenuItem implements java.io.Serializable {

    private String title;
    private float rating;
    private int calories;
    private int protein;
    private int fat;
    private int sodium;
    private int price;
    private int count = 1;

    /**
     * The constructor for the menu
     *
     * @param title    - title of the menu item
     * @param rating   - rating
     * @param calories - number of calories
     * @param protein  - number of protein
     * @param fat      - fat
     * @param sodium   - sodium
     * @param price    - price of a menu item
     */
    public MenuItem(String title, float rating, int calories, int protein, int fat, int sodium, int price) {
        this.title = title;
        this.rating = rating;
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.sodium = sodium;
        this.price = price;
    }

    /**
     * Getter for the title
     *
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Setter for the title
     *
     * @param title - title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Getter for rating
     *
     * @return rating
     */
    public float getRating() {
        return rating;
    }

    /**
     * Setter for rating
     *
     * @param rating - rating
     */
    public void setRating(float rating) {
        this.rating = rating;
    }

    /**
     * Getter for calories
     *
     * @return calories
     */
    public int getCalories() {
        return calories;
    }

    /**
     * Setter for calories
     *
     * @param calories - calories
     */
    public void setCalories(int calories) {
        this.calories = calories;
    }

    /**
     * Getter for protein
     *
     * @return protein
     */
    public int getProtein() {
        return protein;
    }

    /**
     * Setter for protein
     *
     * @param protein - protein
     */
    public void setProtein(int protein) {
        this.protein = protein;
    }

    /**
     * Getter for fat
     *
     * @return fat
     */
    public int getFat() {
        return fat;
    }

    /**
     * Setter for fat
     *
     * @param fat - fat
     */
    public void setFat(int fat) {
        this.fat = fat;
    }

    /**
     * Getter for sodium
     *
     * @return sodium
     */
    public int getSodium() {
        return sodium;
    }

    /**
     * Setter for sodium
     *
     * @param sodium - sodium
     */
    public void setSodium(int sodium) {
        this.sodium = sodium;
    }

    /**
     * Getter for price
     *
     * @return price
     */
    public int getPrice() {
        return price;
    }

    /**
     * Setter for price
     *
     * @param price - price
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * Getter for count
     *
     * @return count
     */
    public int getCount() {
        return count;
    }

    /**
     * Setter for count
     *
     * @param count - count
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * Abstract method to calculate the new values for the combination of base products
     *
     * @param title - title
     * @param items - the list of products
     */
    public abstract MenuItem computeValues(String title, ArrayList<MenuItem> items);

}
