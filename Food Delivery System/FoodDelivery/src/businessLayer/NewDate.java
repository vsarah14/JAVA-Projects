package businessLayer;

public class NewDate {

    private int day;
    private int month;
    private int year;

    /**
     * Getter for the day
     *
     * @return day
     */
    public int getDay() {
        return day;
    }

    /**
     * Getter for the month
     *
     * @return month
     */
    public int getMonth() {
        return month;
    }

    /**
     * Getter for the year
     *
     * @return year
     */
    public int getYear() {
        return year;
    }

    /**
     * Constructor for the date
     *
     * @param day   - day
     * @param month - month
     * @param year  - year
     */
    public NewDate(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }
}
