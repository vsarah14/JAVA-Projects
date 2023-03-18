package model;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PolynomTest {
    @Test
    public void test() {
        Monom m1 = new Monom(6, 3);
        Monom m2 = new Monom(3, 2);
        Monom m3 = new Monom(2, 4);
        Monom m4 = new Monom(3, 2);
        Monom m5 = new Monom(2, 5);

        Polynom p1 = new Polynom();
        Polynom p2 = new Polynom();
        p1.addMonom(m1);
        p1.addMonom(m2);
        p1.addMonom(m3);
        p2.addMonom(m4);
        p2.addMonom(m5);
        System.out.println("Polynom 1:  " + p1 + "\n" + "Polynom 2:  " + p2);
        Polynom addition = Polynom.polynomialOperation(p1, p2, "Add");
        System.out.println("Addition:  " + addition + "\n");
        assertTrue(addition.toString().replaceAll("\s+", "").equals("+9x^2+4x^3+3x^6"));
        System.out.println("Polynom 1:  " + p1 + "\n" + "Polynom 2:  " + p2);
        Polynom subtraction = Polynom.polynomialOperation(p1, p2, "Subtract");
        System.out.println("Subtraction:  " + subtraction + "\n");
        assertTrue(subtraction.toString().replaceAll("\s+", "").equals("+4x^2+2x^3+3x^6"));
        System.out.println("Polynom 1:  " + p1 + "\n" + "Polynom 2:  " + p2);
        Polynom multiplication = Polynom.polynomialOperation(p1, p2, "Multiply");
        System.out.println("Multiply:  " + multiplication + "\n");
        assertTrue(multiplication.toString().replaceAll("\s+", "").equals("-20x^4-18x^5-4x^6-15x^8-6x^9"));
        System.out.println("Polynom 1:  " + p1);
        Polynom derivative = Polynom.polynomialOperation(p1, p2, "Derivative");
        System.out.println("Derivative:  " + derivative + "\n");
        assertTrue(derivative.toString().replaceAll("\s+", "").equals("+8x^1+6x^2+18x^5"));
        System.out.println("Polynom 1:  " + p1);
        Polynom integration = Polynom.polynomialOperation(p1, p2, "Integrate");
        System.out.println("Integrate:  " + integration + "+C" + "\n");
        assertTrue((integration + "+C").replaceAll("\s+", "").equals("+4/3x^3+2/4x^4+3/7x^7+C"));

    }
}
