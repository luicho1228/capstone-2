package com.plurasight;

import com.plurasight.Enums.*;

import java.util.HashSet;
import java.util.List;

public class Sandwich extends Item {

    private final Bread bread;
    private final Size size;
    private final Cheese cheese;
    boolean extraCheese;
    private final Meat meat;
    private final boolean extraMeat;
    private HashSet<Topping> toppings;
    private HashSet<Sauce> sauces;
    private String sandwichName = "Custom Sandwich";

    public Sandwich(Bread bread, Size size, Meat meat, boolean extraMeat, Cheese cheese, boolean extraCheese,HashSet<Topping> toppings,HashSet<Sauce> sauces) {
        this.bread = bread;
        this.size = size;
        this.meat = meat;
        this.extraMeat = extraMeat;
        this.cheese = cheese;
        this.extraCheese = extraCheese;
        this.toppings = toppings;
        this.sauces = sauces;
    }

    public void setSandwichName(String name){
        this.sandwichName = name;
    }
    private double getExtraMeatValue() {
        double extraMeatValue = 0.0;
        switch (size) {
            case SMALL:
                if (extraMeat) {
                    extraMeatValue = 0.5;
                }
                break;
            case MEDIUM:
                if (extraMeat) {
                    extraMeatValue = 1;
                }
                break;
            case LARGE:
                if (extraMeat) {
                    extraMeatValue = 1.5;
                }
                break;
        }
        return extraMeatValue;
    }

    private double getExtraCheeseValue() {
        double extraCheeseValue = 0.0;
        switch (size) {
            case SMALL:
                if (extraCheese) {
                    extraCheeseValue = 0.3;
                }
                break;
            case MEDIUM:
                if (extraCheese) {
                    extraCheeseValue = 0.6;
                }
                break;
            case LARGE:
                if (extraCheese) {
                    extraCheeseValue = 0.9;
                }
                break;
        }
        return extraCheeseValue;
    }

    private double getMeatValue() {
        double meatValue = 0.0;
        switch (size) {
            case SMALL:
                if (meat != Meat.NO_MEAT) {
                    meatValue = 1;
                }
                break;
            case MEDIUM:
                if (meat != Meat.NO_MEAT) {
                    meatValue = 2;
                }
                break;
            case LARGE:
                if (meat != Meat.NO_MEAT) {
                    meatValue = 3;
                }
                break;
        }
        return meatValue;
    }

    private double getCheeseValue() {
        double cheeseValue = 0.0;
        switch (size) {
            case SMALL:
                if (cheese != Cheese.NO_CHEESE) {
                    cheeseValue = 0.75;
                }
                break;
            case MEDIUM:
                if (cheese != Cheese.NO_CHEESE) {
                    cheeseValue = 1.5;
                }
                break;
            case LARGE:
                if (cheese != Cheese.NO_CHEESE) {
                    cheeseValue = 2.25;
                }
                break;
        }
        return cheeseValue;
    }

    private String getBreadSize() {
        return switch (size) {
            case SMALL -> "4\"";
            case MEDIUM -> "8\"";
            case LARGE -> "12\"";
        };
    }

    private double getSizeValue() {
        return switch (size) {
            case SMALL -> 5.5;
            case MEDIUM -> 7;
            case LARGE -> 8.5;
        };
    }

    @Override
    public double getValue() {
        return getSizeValue() + getMeatValue() + getExtraMeatValue() + getCheeseValue() + getExtraCheeseValue();
    }

    @Override
    public String getDetails() {
        String xtraCheese = "No-Extra Cheese";
        String xtraMeat = "No-Extra Meat";
        if (extraMeat) {
            xtraMeat = "Extra Meat";
        }
        if (extraCheese) {
            xtraCheese = "extra cheese";
        }
        String taps = "\n\t* ";
        String subTaps ="\n\t\t";
        String breadString = getBreadSize()+ " " + bread + " Bread";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(sandwichName).append(taps).append(breadString).append(UIComponent.formatTaps(breadString)).append(getSizeValue());
        stringBuilder.append(taps).append(meat).append(UIComponent.formatTaps(meat.toString())).append(getMeatValue());
        stringBuilder.append(subTaps).append(xtraMeat).append(UIComponent.formatTaps("* " + xtraMeat)).append(getExtraMeatValue());
        stringBuilder.append(taps).append(cheese).append(UIComponent.formatTaps(cheese.toString())).append(getCheeseValue());
        stringBuilder.append(subTaps).append(xtraCheese).append(UIComponent.formatTaps("* " + xtraCheese)).append(getExtraCheeseValue());
        stringBuilder.append(taps).append("Toppings");
        if (!(toppings == null)) {
            String toppingString = "";
            int toppingCount = 1;
            for (Topping topping : toppings) {
                if (toppingCount % 3 == 0) {
                    toppingString += subTaps;
                }
                toppingString += topping.toString() + ", ";
                toppingCount++;
            }
            stringBuilder.append(subTaps).append(toppingString);
        }else {
            System.out.println("No Toppings");
        }
        stringBuilder.append(taps).append("Sauces");
        if (!(sauces == null)) {
            String sauceString = "";
            int sauceCount = 1;
            for (Sauce sauce : sauces) {
                if (sauceCount % 3 == 0) {
                    sauceString += subTaps;
                }
                sauceString += sauce.toString() + ", ";
                sauceCount++;
            }
            stringBuilder.append(subTaps).append(sauceString);
        } else {
            stringBuilder.append(taps).append("N0 Sauce");
        }
        String subTotal = "Subtotal:";
        stringBuilder.append("\n\n").append(subTotal).append(UIComponent.formatTaps("\t* " + subTotal)).append("$").append(getValue());
        return stringBuilder.toString();

    }
}
