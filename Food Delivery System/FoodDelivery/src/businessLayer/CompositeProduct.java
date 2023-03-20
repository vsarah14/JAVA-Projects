package businessLayer;

import java.util.ArrayList;

public class CompositeProduct extends MenuItem {

    private ArrayList<MenuItem> items = new ArrayList<>();

    /**
     * The constructor for the composite product
     *
     * @param title    - title of the combination
     * @param rating   - rating
     * @param calories - total number of calories
     * @param protein  - total number of protein
     * @param fat      - total fat
     * @param sodium   - total sodium
     * @param price    - total calculated price
     */
    public CompositeProduct(String title, float rating, int calories, int protein, int fat, int sodium, int price) {
        super(title, rating, calories, protein, fat, sodium, price);
    }

    /**
     * The constructor for the composite product
     *
     * @param name  - name of the composite product
     * @param items - array of the products that are combined
     */
    public CompositeProduct(String name, ArrayList<MenuItem> items) {
        super(name, 0, 0, 0, 0, 0, 0);
        this.items = items;
    }

    @Override
    public MenuItem computeValues(String title, ArrayList<MenuItem> item) {
        int totalPrice = 0;
        int totalCalories = 0;
        int totalProtein = 0;
        int totalFat = 0;
        int totalSodium = 0;
        int totalRating = 0;
        for (MenuItem menuItem : item) {
            totalPrice += menuItem.getPrice();
            totalCalories += menuItem.getCalories();
            totalFat += menuItem.getFat();
            totalProtein += menuItem.getProtein();
            totalSodium += menuItem.getSodium();
            totalRating += menuItem.getRating();
        }
        totalRating = totalRating / item.size();
        CompositeProduct combination = new CompositeProduct(title, totalRating, totalCalories, totalProtein, totalFat, totalSodium, totalPrice);
        return combination;
    }
}
