package model;

import java.util.*;

public class Polynom {

    //a polynom has one or many monoms, so we ll add them to a list
    private List<Monom> monomList;

    //getter
    public List<Monom> getMonomList() {
        return monomList;
    }

    public void setMonomList(List<Monom> monoms) {
        this.monomList = monoms;
    }

    //constructor
    public Polynom() {
        this.monomList = new ArrayList<>();
    }

    public Polynom(List<Monom> monoms) {
        this.monomList = monoms;
    }

    //the function that adds a monom
    public void addMonom(Monom m) {
        Monom existingMonom = this.getMonoWithPower(m.getPower());

        if (existingMonom == null) {
            this.monomList.add(m);
        } else {
            int newTotal = existingMonom.getCoefficient() + m.getCoefficient();
            if (newTotal == 0) {
                this.getMonomList().remove(existingMonom);
            }
            existingMonom.setCoefficient(newTotal);

        }
        this.monomList.sort(Comparator.comparingInt(Monom::getPower));

    }

    public void multiplyPolynom(Polynom p1) {
        ArrayList<Monom> result = new ArrayList<>();
        for (Monom m1 : p1.getMonomList()) {
            for (Monom m2 : this.getMonomList()) {
                int coeff = m1.getCoefficient() * m2.getCoefficient();
                int pow = m1.getPower() + m2.getPower();
                result.add(new Monom(pow, coeff));
            }
        }
        this.setMonomList(result);
        this.reduceMonomList();
        this.monomList.sort(Comparator.comparingInt(Monom::getPower));
    }

    public void differentiation() {
        ArrayList<Monom> result = new ArrayList<>();
        for (Monom M : monomList) {
            int coeff = M.getCoefficient() * M.getPower();
            int pow = M.getPower() - 1;
            if (pow != -1) {
                result.add(new Monom(pow, coeff));
            }
        }
        this.setMonomList(result);
        this.monomList.sort(Comparator.comparingInt(Monom::getPower));
    }

    public void integration() {
        ArrayList<Monom> result = new ArrayList<>();
        for (Monom M : monomList) {
            int coeff = M.getCoefficient();
            int pow = M.getPower() + 1;
            int divider = M.getPower() + 1;
            result.add(new Monom(pow, coeff, divider));
        }
        this.setMonomList(result);
        this.monomList.sort(Comparator.comparingInt(Monom::getPower));
    }

    @Override
    public String toString() {
        String poly = "";
        for (Monom m : monomList) {
            poly += (m.getCoefficient() > 0 ? "+" : "") + m.getCoefficient() + (m.getDivider() != null ? "/" + m.getDivider() : "") + "x^" + m.getPower() + " ";
        }
        return poly;
    }

    //this is a function that verifies if we can find the power pow in the first polynomial
    private Monom getMonoWithPower(int pow) {
        try {
            return this.monomList.stream().filter(m -> m.getPower() == pow).findFirst().get();
        } catch (Exception e) {
            return null;
        }
    }

    //this is a function that chooses the operation that we want to execute and executes it
    public static Polynom polynomialOperation(Polynom p1, Polynom p2, String op) {
        Polynom p3 = new Polynom(p1.getMonomList());
        switch (op) {
            case "Add":
                addTwoPoly(p3, p2);
                break;
            case "Subtract":
                inversePoly(p2);
                addTwoPoly(p3, p2);
                break;
            case "Multiply":
                multiyplyTwoPoly(p3, p2);
                break;
            case "Derivative":
                derivative(p3);
                break;
            case "Integrate":
                integrate(p3);
                break;
            default:
                addTwoPoly(p3, p2);
        }

        return p3;
    }

    public static Polynom parseStringForPoly(String data) throws Exception {
        data = data.replaceAll("\\+", " +");
        data = data.replaceAll("\\-", " -");
        data = data.replaceAll("\\^ \\-", "^-");
//        data = data.replaceAll("(?:[^\\^])\\-", " -");
        Polynom poly1 = new Polynom();
        ArrayList<String> monomsStrings = new ArrayList<>(Arrays.asList(data.split("\\s+")));
        monomsStrings.removeAll(Arrays.asList("", null));
        for (String monomString :
                monomsStrings) {
            poly1.getMonomList().add(Monom.getMonomFromString(monomString));
        }

        return poly1;
    }

    private static void addTwoPoly(Polynom p1, Polynom p2) {
        for (Monom m : p2.getMonomList()) {
            p1.addMonom(m);
        }
    }

    private static void multiyplyTwoPoly(Polynom p1, Polynom p2) {
        p1.multiplyPolynom(p2);
    }

    private static void inversePoly(Polynom p) {
        for (Monom m : p.getMonomList()) {
            m.setCoefficient(m.getCoefficient() * -1);
        }
    }

    private static void derivative(Polynom p) {
        p.differentiation();
    }

    private static void integrate(Polynom p) {
        p.integration();
    }

    private void reduceMonomList() {
        ArrayList<Monom> finalMonoms = new ArrayList<>();
        for (Monom m : this.monomList) {
            int ok = 0;
            for (Monom fm : finalMonoms) {
                if (fm.getPower() == m.getPower()) {
                    ok = 1;
                    fm.setCoefficient(fm.getCoefficient() + m.getCoefficient());
                }
            }
            if (ok == 0) {
                finalMonoms.add((m));
            }
        }
        this.setMonomList(finalMonoms);
    }

}
