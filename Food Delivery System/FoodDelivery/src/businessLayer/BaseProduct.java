package businessLayer;

import java.util.ArrayList;

public class BaseProduct extends MenuItem {
    /**
     * The constructor for the base product
     *
     * @param title    - title of the product
     * @param rating   - rating
     * @param calories - number of calories
     * @param protein  - number of protein
     * @param fat      - fat
     * @param sodium   - sodium
     * @param price    - price of the product
     */
    public BaseProduct(String title, float rating, int calories, int protein, int fat, int sodium, int price) {
        super(title, rating, calories, protein, fat, sodium, price);
    }

    @Override
    public MenuItem computeValues(String title, ArrayList<MenuItem> items) {
        return null;
    }


}



