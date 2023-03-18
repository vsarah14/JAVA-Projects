package model;

import static java.lang.Integer.parseInt;

public class Monom {
    //a monom has only the power and the coefficient of an element
    private int power;
    private int coefficient;
    private Integer divider;

    //getters and setters
    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(int coefficient) {
        this.coefficient = coefficient;
    }

    //this is a method that helps us to get the power and the coefficient
    public static Monom getMonomFromString(String monoString) throws Exception {
        //check if input invalid and throw exception
        System.out.println(monoString);
        if (!monoString.matches("([+-]?(?:(?:\\d+x\\^(-?)\\d+)))")) {
            throw new Exception("Invalid input.");
        }
        //we pass a string argument and return an integer to get the coefficient and the power
        int coef = parseInt(monoString.split("(x\\^)")[0]);
        int degree = parseInt(monoString.split("(x\\^)")[1]);

        return new Monom(degree, coef);
    }

    //constructor
    public Monom(int pow, int co) {
        this.power = pow;
        this.coefficient = co;
    }

    public Monom(int power, int coefficient, Integer divider) {
        this.power = power;
        this.coefficient = coefficient;
        this.divider = divider;
    }

    public Integer getDivider() {
        return divider;
    }

    public void setDivider(int divider) {
        this.divider = divider;
    }
}
