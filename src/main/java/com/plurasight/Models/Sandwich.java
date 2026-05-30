package com.plurasight.Models;

import com.plurasight.Enums.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents a custom sandwich item in the point-of-sale system.
 * This class encapsulates all sandwich structural components, handles complex
 * pricing calculations based on sizing tiers, and generates formatted structural receipts.
 *
 * @author Luis Vasquez
 */
public class Sandwich extends Item {

    private boolean isToasted;
    private Bread bread;
    private Size size;
    private Cheese cheese;
    private final boolean extraCheese;
    private Meat meat;
    private final boolean extraMeat;
    private Set<Topping> toppings;
    private Set<Sauce> sauces;
    private String sandwichName = "Custom Sandwich";

    /**
     * Constructs a complete, immutable instance of a Sandwich configuration.
     *
     * @param bread       The chosen bread type.
     * @param size        The chosen sandwich length profile.
     * @param meat        The base meat choice.
     * @param extraMeat   True if an extra portion of meat should be charged and added.
     * @param cheese      The base cheese choice.
     * @param extraCheese True if an extra portion of cheese should be charged and added.
     * @param toppings    A set of chosen garnishes.
     * @param sauces      A set of chosen sauces.
     */
    public Sandwich(Bread bread, Size size, boolean isToasted, Meat meat, boolean extraMeat, Cheese cheese, boolean extraCheese, HashSet<Topping> toppings, HashSet<Sauce> sauces) {
        this.bread = bread;
        this.size = size;
        this.meat = meat;
        this.extraMeat = extraMeat;
        this.cheese = cheese;
        this.extraCheese = extraCheese;
        this.toppings = toppings;
        this.sauces = sauces;
        this.isToasted = isToasted;
    }

    /**
     * Calculates the cumulative dollar value of the sandwich by combining the size base cost,
     * base premium ingredients, and any corresponding extra modifications.
     *
     * @return The complete calculated price of the entire sandwich structure.
     */
    @Override
    public double getValue() {
        return getSizeValue() + getMeatValue() + getExtraMeatValue() + getCheeseValue() + getExtraCheeseValue();
    }

    /**
     * Formats and compiles a detailed string representation of the sandwich itemized receipt,
     * including ingredient breakdowns, individual add-on calculations, and subtotal layouts.
     *
     * @return A tab-formatted text block suitable for terminal or ticket display output.
     */
    @Override
    public String getDetails() {
        return OrderReceiptManager.formatItemReceipt(this);
    }

    @Override
    public String getItemHeader() {
        return sandwichName + "{ " + getBreadSize() + " " + bread + "," + meat + "," + cheese + "," + toppings.toString() + "," + sauces + "," + "$" + getValue() + "}";
    }

    /**
     * Maps the assigned sandwich size enum profile to its physical inch representation text.
     *
     * @return A string literal indicating the length of the bread item (e.g. 4", 8", 12").
     */
    String getBreadSize() {
        return switch (size) {
            case SMALL -> "4\"";
            case MEDIUM -> "8\"";
            case LARGE -> "12\"";
        };
    }

    /**
     * Retrieves the value of the base sandwich size selection.
     *
     * @return a double representing The base size cost of the sandwich item.
     */
    double getSizeValue() {
        return switch (size) {
            case SMALL -> 5.5;
            case MEDIUM -> 7;
            case LARGE -> 8.5;
        };
    }

    /**
     * Calculates base meat cost according to the sandwich size.
     *
     * @return a double representing the total cost addition assigned to base meat provisions.
     */
    double getMeatValue() {
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

    /**
     * Calculates supplementary markup overheads required when adding a double meat package.
     *
     * @return a double representing The total extra meat cost.
     */
    double getExtraMeatValue() {
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

    /**
     * Calculates fundamental cheese premiums according to the sandwich size.
     *
     * @return a double representing the total cost of base cheese addition.
     */
    double getCheeseValue() {
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

    /**
     * Calculates supplementary markup overheads required when user wants extra cheese in their sandwich.
     *
     * @return a double representing the total extra cheese cost.
     */
    double getExtraCheeseValue() {
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

    public void setToast(boolean isToasted) {
        this.isToasted = isToasted;
    }

    public boolean isToasted() {
        return isToasted;
    }

    public Bread getBread() {
        return bread;
    }

    public void setBread(Bread bread) {
        this.bread = bread;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Cheese getCheese() {
        return cheese;
    }

    public void setCheese(Cheese cheese) {
        this.cheese = cheese;
    }

    public boolean isExtraCheese() {
        return extraCheese;
    }

    public Meat getMeat() {
        return meat;
    }

    public void setMeat(Meat meat) {
        this.meat = meat;
    }

    public boolean isExtraMeat() {
        return extraMeat;
    }

    public Set<Topping> getToppings() {
        return toppings;
    }

    public void setToppings(HashSet<Topping> toppings) {
        this.toppings = toppings;
    }

    public Set<Sauce> getSauces() {
        return sauces;
    }

    public void setSauces(HashSet<Sauce> sauces) {
        this.sauces = sauces;
    }

    public String getSandwichName() {
        return sandwichName;
    }

    /**
     * Updates the identifier name of the sandwich.
     *
     * @param name The new custom display name for this sandwich string line.
     */
    public void setSandwichName(String name) {
        this.sandwichName = name;
    }
}
